package com.fangzhi.yao.fzcms.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.dto.token.PrintAccessToken;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import com.fangzhi.yao.fzcms.service.IUserService;
import com.fangzhi.yao.fzcms.util.JwtUtil;
import com.fangzhi.yao.fzcms.util.PasswordEncoder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ApiHomeController extends BaseController{

    @Reference(version = "1.0.0")
    private IUserService iUserService;

    @RequestMapping("/login")
    public ResultInfo login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        UserInfo userInfo = iUserService.findUserInfo(username);
        if (PasswordEncoder.checkPassWord(userInfo.getPassWord(), userInfo.getCredentialsSalt(), password)) {
            String token = JwtUtil.sign(username, userInfo.getPassWord());
            Date expireTime = JwtUtil.getExpireTime(token);
            return new ResultInfo("0", "登录成功", new PrintAccessToken(token, expireTime.getTime()));
        } else {
            throw new BusinessException("-1", "账号密码不正确");
        }
    }

    @GetMapping("/article")
    public ResultInfo article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResultInfo("0", "You are already logged in", null);
        } else {
            return new ResultInfo("0", "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResultInfo requireAuth() {
        return new ResultInfo("0", "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResultInfo requireRole() {
        return new ResultInfo("0", "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"user:view", "user:edit"})
    public ResultInfo requirePermission() {
        return new ResultInfo("0", "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultInfo unauthorized() {
        return new ResultInfo("401", "Unauthorized", null);
    }

}