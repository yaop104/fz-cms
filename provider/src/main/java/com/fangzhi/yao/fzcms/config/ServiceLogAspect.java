package com.fangzhi.yao.fzcms.config;

import com.fangzhi.yao.fzcms.log.Log;
import com.fangzhi.yao.fzcms.log.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLogAspect {

    private Log logger = LogFactory.getLog(getClass());

	ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.fangzhi.yao.fzcms.service..*.*(..))")
	public void serviceLog() {
	}

    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
		startTime.set(System.currentTimeMillis());

        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "serviceLog()")
    public void doAfterReturning(Object ret) throws Throwable {
	    logger.info("RESPONSE : " + ret);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

}