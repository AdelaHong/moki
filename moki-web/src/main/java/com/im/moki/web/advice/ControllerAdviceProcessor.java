package com.im.moki.web.advice;

import com.im.moki.common.expection.BaseException;
import com.im.moki.common.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class ControllerAdviceProcessor {

        @Autowired
        protected MessageSource messageSource;

        @ExceptionHandler(Exception.class)
        @ResponseBody
        public Result<?> handleException (HttpServletRequest request, Exception ex){

        /*String code = BaseResultCode.SYSTEM_ERROR;
        if (ex instanceof HttpMessageNotReadableException) {
            code = BaseResultCode.PARAM_ERROR;
        } else if (ex instanceof HttpRequestMethodNotSupportedException) {
            code = BaseResultCode.HTTP_METHOD_NOT_SURPPORT;
        }*/

        String code = null;
        String msg = null;


        if (ex instanceof BaseException) {
            BaseException bizException = (BaseException) ex;
            msg = bizException.getMessage();
            code = bizException.getErrorCode().getCode();

        } else if (ex instanceof AccessDeniedException) {
            msg = "无权限访问";
            code = "403";
        }
        if (msg == null) {
            msg = ex.getMessage();
        }
        Result<?> resp = new Result<>();
        resp.setErrorCode(Integer.parseInt(code));
        resp.setErrorMsg(msg);
        log.error("code: " + code + "  msg: " + msg, ex);
        return resp;
    }
}
