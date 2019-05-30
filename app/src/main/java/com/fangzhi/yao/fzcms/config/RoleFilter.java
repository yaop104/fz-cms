package com.fangzhi.yao.fzcms.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author yaoping
 * @date 2019/5/30 14:07
 */
public class RoleFilter extends RolesAuthorizationFilter {

    @SuppressWarnings("unchecked")
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        String[] rolesArray = (String[]) mappedValue;
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String role : rolesArray) {
            List<String> roleList = Arrays.asList(role.split(":"));
            for (String o : roleList) {
                if (subject.hasRole(o)) {
                    return true;
                }
            }
        }
        return false;

    }

}
