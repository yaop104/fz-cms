package com.fangzhi.yao.fzcms.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.dto.token.PrintAccessToken;
import com.fangzhi.yao.fzcms.dto.user.UserInfoDTO;
import com.fangzhi.yao.fzcms.entity.SendMessage;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import com.fangzhi.yao.fzcms.service.ISendMessageService;
import com.fangzhi.yao.fzcms.service.IUserService;
import com.fangzhi.yao.fzcms.util.JwtUtil;
import com.fangzhi.yao.fzcms.util.PasswordEncoder;
import com.fangzhi.yao.fzcms.validator.user.UserValidator;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ApiHomeController extends BaseController{

    @Reference(version = "1.0.0", check = false)
    private IUserService iUserService;
    @Reference(version = "1.0.0", check = false)
    private ISendMessageService iSendMessageService;

    @RequestMapping("/loginCode")
    public ResultInfo loginWithCode(@RequestBody UserInfoDTO userInfoDTO) {
        //验证
        UserValidator.userInfoDTOParamCode(userInfoDTO);
        //验证码
        SendMessage sendMessage = new SendMessage();
        sendMessage.setFzSendType("1");
        sendMessage.setFzCompanyCode("0001");
        sendMessage.setFzSendMobile(userInfoDTO.getUsername());
        sendMessage.setFzSendCode(userInfoDTO.getSendCode());
        String flag = iSendMessageService.selectLastCode(sendMessage);

        if(!"1".equals(flag)){
            return respMessage("1", "无效验证码，请重新输入！");
        }

        //账号密码
        UserInfo userInfo = iUserService.findUserInfo(userInfoDTO.getUsername());
        if(userInfo != null){
            String token = JwtUtil.sign(userInfoDTO.getUsername(), userInfo.getPassWord());
            Date expireTime = JwtUtil.getExpireTime(token);
            return respMessage("0", "登录成功", new PrintAccessToken(token, expireTime.getTime()));
        }else {
            throw new BusinessException("1", "账号不正确");
        }

    }

    @RequestMapping("/login")
    public ResultInfo loginWithPassword(@RequestBody UserInfoDTO userInfoDTO) {
        //验证
        UserValidator.userInfoDTOParam(userInfoDTO);
        //账号密码
        UserInfo userInfo = iUserService.findUserInfo(userInfoDTO.getUsername());
        if (PasswordEncoder.checkPassWord(userInfo.getPassWord(), userInfo.getCredentialsSalt(), userInfoDTO.getPassword())) {
            String token = JwtUtil.sign(userInfoDTO.getUsername(), userInfo.getPassWord());
            Date expireTime = JwtUtil.getExpireTime(token);
            return respMessage("0", "登录成功", new PrintAccessToken(token, expireTime.getTime()));
        } else {
            throw new BusinessException("1", "账号密码不正确");
        }
    }

    @RequestMapping("/getSimpleUploadToken")
    @RequiresAuthentication
    public ResultInfo getSimpleUploadToken(){
        String tokenString = iSendMessageService.getSimpleUploadToken();
        return respMessage("0", "获取成功", tokenString);
    }


    @GetMapping("/article")
    public ResultInfo article() {
        if (getUserLoginStatus()) {
            return respMessage("0", "You are already logged in", null);
        } else {
            return respMessage("0", "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResultInfo requireAuth() {
        return respMessage("0", "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResultInfo requireRole() {
        return respMessage("0", "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public ResultInfo requirePermission() {
        return respMessage("0", "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultInfo unauthorized() {
        return respMessage("401", "Unauthorized", null);
    }

}