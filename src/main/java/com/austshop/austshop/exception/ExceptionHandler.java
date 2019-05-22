package com.austshop.austshop.exception;


import com.austshop.austshop.entity.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用@ControllerAdvice来进行统一异常处理
 * zpwan
 * 2019/3/16
 */
@ControllerAdvice
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse handler(Exception e) {
        if (e instanceof DictionaryCheckKeyException) {
            return new ResultResponse(200, "SUCCESS", true);
        } else if (e instanceof OperationFailException) {
            return new ResultResponse(301, "ERROR", "FALSE");
        } else if (e instanceof PasswordNotMatchException) {
            return new ResultResponse(302, "密码不匹配", "FALSE");
        } else if (e instanceof UserExistException) {
            return new ResultResponse(303, "用户名已被占用", "FALSE");
        } else if (e instanceof AdminNotFoundException) {
            return new ResultResponse(303, "用户不存在", "FALSE");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return new ResultResponse(400, "请求方法校验失败", e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return new ResultResponse(401, e.getMessage(), e.getMessage());
        } else if (e instanceof MissingServletRequestParameterException) {
            return new ResultResponse(401, e.getMessage(), "FALSE");
        } else if (e instanceof NumberFormatException) {
            return new ResultResponse(401, e.getMessage(), "FALSE");
        } else if (e instanceof TokenCheckException) {
            return new ResultResponse(403, "token不存在或已失效", "FALSE");
        } else {//其他未捕获异常
            LOGGER.error("exception:{}", e.getMessage(), e);
            return new ResultResponse(500, "服务器内部错误,请联系系统管理员!","FALSE");
        }
    }

}
