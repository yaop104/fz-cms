package com.fangzhi.yao.fzcms.web;

import com.fangzhi.yao.fzcms.dto.DirectoryInfo;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.RoleInfo;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.entity.Org;
import com.fangzhi.yao.fzcms.entity.Permission;
import com.fangzhi.yao.fzcms.entity.User;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import com.fangzhi.yao.fzcms.service.IPermissionService;
import com.fangzhi.yao.fzcms.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController{

    @Reference(version = "1.0.0", check = false)
    private IPermissionService iPermissionService;

    @Reference(version = "1.0.0", check = false)
    private IUserService iUserService;

    @RequestMapping("/*")
    public void toHtml(){

    }

    @RequestMapping("/kickout")
    public ResultInfo<List<Org>> listData() {
        return new ResultInfo<>("kickout");
    }

    @RequestMapping({"/","/index"})
    public String index(Model model){

        List<DirectoryInfo> directoryList = new ArrayList<>();
        //获取当前用户角色信息
        UserInfo userInfo = this.getUserInfo();
        RoleInfo roleInfo = userInfo.getRoleInfo();
        List<Permission> permissionList = iPermissionService.getAllDirectoryPermissions();
        if(permissionList != null){
            for (Permission ps : permissionList) {
                if(roleInfo.getPermissionIds().contains(","+ps.getId()+",")){
                    directoryList.add(new DirectoryInfo(ps.getPermissionName(),ps.getPermissionCode()));
                }
            }
        }
        model.addAttribute("directoryList",directoryList);

        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws BusinessException {

        logger.info("HomeController.login()");

        // 判断是否已登录，如果已登录直接跳转到首页
        UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        if (userInfo != null){
            return "redirect:/";
        }

        // 登录失败从request中获取shiro处理的异常信息.
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");

        logger.info("exception=" + exception);
        Session session = SecurityUtils.getSubject().getSession();
        if (exception != null) {
            if (AccountException.class.getName().equals(exception)) {
                logger.info("AccountException ---> 账号或密码错误！");
                throw new BusinessException("1", "账号或密码错误！");
            }else if(IncorrectCredentialsException.class.getName().equals(exception)){
                //密码最多错误输入5次
                int loginErrorCount = session.getAttribute("loginErrorCount") == null ? 0 : Integer.parseInt(session.getAttribute("loginErrorCount") + "");
                if(++loginErrorCount == 5){
                    //锁定账号
                    User user = iUserService.selectById(Integer.parseInt(session.getAttribute("loginUserId")+""));
                    user.setState(2);
                    iUserService.updateById(user);
                }
                session.setAttribute("loginErrorCount", loginErrorCount);
                logger.info("AccountException ---> 密码错误！");
                throw new BusinessException("1", "密码错误，您还有" + (5-loginErrorCount) + "机会！");
            }
            else if(DisabledAccountException.class.getName().equals(exception)) {
                logger.info("DisabledAccountException ---> 账号已禁用！");
                throw new BusinessException("1", "账号已禁用！");
            } else if(LockedAccountException.class.getName().equals(exception)) {
                logger.info("LockedAccountException ---> 账号已锁定！");
                throw new BusinessException("1", "账号已锁定，请联系管理员解锁！");
            } else if ("kaptchaValidateFailed".equals(exception)) {
                logger.info("kaptchaValidateFailed ---> 验证码错误！");
                throw new BusinessException("1", "验证码错误！");
            } else {
                logger.info("else ---> "+ exception);
                throw new BusinessException("1", "未知错误！");
            }
        }
        //登录成功清零
        session.setAttribute("loginErrorCount", 0);

        // 此方法不处理登录成功,由shiro进行处理

        return "login";
    }

}