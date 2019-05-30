package com.fangzhi.yao.fzcms.config;

import com.fangzhi.yao.fzcms.dto.ResultInfo;
import com.fangzhi.yao.fzcms.ex.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 全局异常处理，当异常被@ControllerAdvice时不会走到这个处理类，没被处理时会走到这里
 * 例如 @valid入参校验失败的是不会走ControllerAdvice的，但会走这个处理器
 * @author yao
 *
 */
@RestController
public class GolablExceptionEndpoint implements ErrorController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * 全局异常处理
     * 增加@ResponseStatus注解将非200的http状态码转成200
     * @return
     */
    @RequestMapping(value = PATH)
    @ResponseStatus(code=HttpStatus.OK)
    public ResultInfo<Object> error(HttpServletRequest request) {
        return handlerError(request, false);
    }

    /**
     * 具体的处理
     * @param request
     * @param includeStackTrace
     * @return
     */
    private ResultInfo<Object> handlerError(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest requestAttributes = new ServletWebRequest(request);
        Throwable e = errorAttributes.getError(requestAttributes);

        Map<String, Object> data = errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);

        String message = null;
        StringBuilder detailMessage = new StringBuilder("");

        HttpStatus status = getStatus(request);
        if(status == HttpStatus.BAD_REQUEST) {
            log.error("参数校验失败", e);
            message = "请检查数据填写是否合法";
        } else if(status == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.error("系统错误", e);
            message = "系统繁忙";
        } else if(status == HttpStatus.NOT_FOUND) {
            log.error("404错误");
            message = "服务或者页面不存在";
        } else {
            log.error("系统错误", e);
            message = "系统出错,未知错误";
        }

        if(e != null && e.getCause() instanceof BusinessException){
            BusinessException ex = (BusinessException) e.getCause();
            return new ResultInfo<>(ex.getCode(), message, ex.getMessage());
        }

        if(null != data.get("message")) {
            detailMessage.append(String.valueOf(data.get("message")));
        }

        return new ResultInfo(String.valueOf(data.get("status")), message, detailMessage.toString());
    }

    /**
     * 获取错误编码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}