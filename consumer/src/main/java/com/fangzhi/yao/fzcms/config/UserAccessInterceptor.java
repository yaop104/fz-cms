package com.fangzhi.yao.fzcms.config;


import com.fangzhi.yao.fzcms.context.DJContext;
import com.fangzhi.yao.fzcms.dto.UserInfo;
import com.fangzhi.yao.fzcms.entity.User;
import com.fangzhi.yao.fzcms.generator.SpanIdGenerator;
import com.fangzhi.yao.fzcms.generator.UniqueIdGenerator;
import com.fangzhi.yao.fzcms.log.Log;
import com.fangzhi.yao.fzcms.log.LogFactory;
import com.fangzhi.yao.fzcms.util.GsonUtil;
import com.fangzhi.yao.fzcms.util.Utils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

import static com.fangzhi.yao.fzcms.code.ConstantYao.ADMIN_ID;


/**
 * springmvc 过滤器
 * modified by yao
 */
public class UserAccessInterceptor extends HandlerInterceptorAdapter {
    private static final Log interfaceLogger = LogFactory.getLog(UserAccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.initRequestContext(request);
        String path = request.getPathInfo();
        Method method = parseHandlerMethod(handler);

        Map<String, String> paramMap = Utils.convertMap(request.getParameterMap());

        // 初始化DJContext
        this.initDjLogger();
        this.initDjContext(request);

        // 记录用户操作日志
        String param;
        if (!Utils.isEmpty(paramMap)) {
            param = GsonUtil.GSON.toJson(paramMap);
        } else {
            param = "";
        }

        // 添加日志记录请求
        addInterfaceLog(request, param, method);

        return true;
    }
    public static Method parseHandlerMethod(Object hander) {
        return ((HandlerMethod) hander).getMethod();
    }

    //处理接口到达日志
    public static void addInterfaceLog(HttpServletRequest request, String param, Method method) {
        StringBuilder logRecMsg = new StringBuilder().append("Receive ")
                .append(method.getDeclaringClass().getSimpleName())
                .append(".").append(method.getName()).append(" request ");
        logRecMsg.append(request.getRequestURI()).append(", params: ").append(param);

        interfaceLogger.info(logRecMsg.toString(), DJContext.getUniqueID());
    }

    private void initRequestContext(HttpServletRequest request) {
        // 登录时 sessionId 由 uuid 生成并写入 cookie，之后都来自 kong 传入的 Header
        UserInfo userInfo = (UserInfo)SecurityUtils.getSubject().getPrincipal();
        Integer adminId = Optional.ofNullable(userInfo).map(User::getId).orElse(0);

        // 无法从 header 中获取 adminId，就从尝试通过 sessionId 获取 redis 中的 admin 信息
        request.setAttribute(ADMIN_ID, Long.valueOf(adminId));
    }

    private void initDjContext(HttpServletRequest request) {
        Long adminId = (Long) request.getAttribute(ADMIN_ID);
        // 设置请求上下文
        DJContext context = DJContext.getContext();
        context.setAf(DJContext.Af.ADMIN.get());
        context.setUid(adminId);
    }

    private void initDjLogger() {
        DJContext djContext = DJContext.getContext();
        String spanId = SpanIdGenerator.initSpanId();
        djContext.setSpanId(spanId);
        djContext.setInitialSpanId(spanId);
        djContext.setUniqueId(UniqueIdGenerator.getInstance().generate());
    }
}