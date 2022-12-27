package com.ct.shop.utils.exception;

import com.ct.shop.utils.constants.HttpCode;
import com.ct.shop.utils.resp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Tao
 * @descrption 通用异常处理类
 */
@RestControllerAdvice
public class RestCtrlExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception e){
        logger.error("服务器抛出了异常：{}",e);
        return new ResponseResult<String>(HttpCode.FAIL,"执行失败",e.getMessage());
    }
}
