package com.zxc.gmall.admin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.context.properties.bind.BindResult;

@Aspect
public class ParamValidAOP {

    @Around("execution(* com.zxc.gmall.admin..*Controller.*(..))")
    public Object validAround(ProceedingJoinPoint point) throws Throwable {

        Object[] args = point.getArgs();
        for (Object obj: args ) {
            if (obj instanceof BindResult){
                BindResult b = (BindResult) obj;
                //if (b.)
            }

        }

        try {
            Object result = point.proceed(point.getArgs());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

}
