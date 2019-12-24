package com.zxc.gmall.admin.aop;

import com.zxc.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一的异常处理类;给前端返回500的json
 * 出现异常一定要日志记录
 */

@Slf4j
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    //数学运算异常
    @ExceptionHandler(ArithmeticException.class)
    public Object handlerArithmeticException(Exception e){

        log.error("系统全局异常感知:错误信息{};错误堆栈信息:{}",
                e.getMessage(),e.getStackTrace());
        return new CommonResult().failed("数学运算异常");
    }
    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Object handlerNullPointerException(Exception e){
        log.error("系统全局异常感知:错误信息{};错误堆栈信息:{}",
                e.getMessage(),e.getStackTrace());
        return new CommonResult().failed("空指针异常");
    }



}
