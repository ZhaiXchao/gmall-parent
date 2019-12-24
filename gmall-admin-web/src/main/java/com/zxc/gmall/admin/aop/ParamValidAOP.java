package com.zxc.gmall.admin.aop;

import com.zxc.gmall.to.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.validation.BindingResult;

/**
 * 有环绕通知时一定要手动抛出异常
 */
@Aspect
public class ParamValidAOP {

    @Around("execution(* com.zxc.gmall.admin..*Controller.*(..))")
    public Object validAround(ProceedingJoinPoint point) throws Throwable {

        Object[] args = point.getArgs();
        for (Object obj: args ) {
            if (obj instanceof BindingResult){
                BindingResult b = (BindingResult) obj;
                if (b.getErrorCount()>0)
                    return new CommonResult().validateFailed(b);
            }
        }
        Object result = null;
        try {
            //前置通知
            result = point.proceed(point.getArgs());
            //返回通知
        } catch (Throwable throwable) {
            //异常通知
            throwable.printStackTrace();
            throw throwable;
            //return new CommonResult().failed();
        } finally {
            //后置通知
        }
        return result;
    }

}
