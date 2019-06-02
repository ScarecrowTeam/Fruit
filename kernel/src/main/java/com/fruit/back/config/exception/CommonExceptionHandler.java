package com.fruit.back.config.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fruit.back.dto.ExceptionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一的异常处理类
 *
 * create by wzy
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private Logger logger = LogManager.getLogger(this.getClass());

    /**
     * 拦截Exception类异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public void exceptionHandler(HttpServletResponse response, ServiceException e) {
        ExceptionDTO result = new ExceptionDTO();
        result.setCode("500");
        result.setMessage(e.getMessage());
        logger.info("--------异常捕获 start--------");
        logger.info("异常:" + e.getMessage());
        if (e.getMessage() == null || "".equals(e.getMessage()))  result.setMessage("请求异常，稍后请重新尝试");
        logger.info("--------异常捕获 end--------");
        responseResult(response, result);
    }

    /**
     * 拦截指定类别异常
     * @param response
     * @param e
     */
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) {
        ExceptionDTO result = new ExceptionDTO();
        logger.info("--------异常捕获 start--------");
        logger.info("异常:" + e.getMessage());
        if (e instanceof JSONException || e instanceof HttpMessageNotReadableException){
            result.setCode("401");
            result.setMessage(e.getMessage());
        } else if (e instanceof AuthException) {
            result.setCode("402");
            result.setMessage(e.getMessage());
        } else {
            result.setCode("500");
            result.setMessage("先错着，后面修复看开发心情");
        }
        logger.info("--------异常捕获 end--------");
        responseResult(response, result);
    }

    /**
     * @param response
     * @param exception
     * @Title: responseResult
     * @Description: 响应结果
     * @Reutrn void
     */
    private void responseResult(HttpServletResponse response, ExceptionDTO exception) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(exception, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
