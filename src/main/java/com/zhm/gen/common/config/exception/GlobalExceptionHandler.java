package com.zhm.gen.common.config.exception;

import com.zhm.gen.common.config.exception.base.BusinessException;
import com.zhm.gen.common.util.LogUtil;
import com.zhm.gen.common.util.ServletUtil;
import com.zhm.gen.common.web.domain.response.Result;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Describe: 全局异常处理类
 * Author: zhm
 * CreateTime: 2019/10/23
 * */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 不 支 持 的 请 求 类 型
     * */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e){
        LogUtil.error("请求不支持：",e);
        return Result.failure("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦 截 未 知 的 运 行 时 异 常
     */
    @ExceptionHandler({RuntimeException.class})
    public Result notFount(RuntimeException e)
    {
        LogUtil.error("运行时异常:", e);
        return Result.failure("运行时异常:" + e.getMessage());
    }


    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e)
    {
        LogUtil.error(e.getMessage(), e);
        return Result.failure("服务器错误，请联系管理员");
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessException(HttpServletRequest request, BusinessException e)
    {
        LogUtil.error(e.getMessage(), e);
        if (ServletUtil.isAjax(request))
        {
            return Result.failure(e.getMessage());
        }
        else
        {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/500");
            return modelAndView;
        }
    }

}
