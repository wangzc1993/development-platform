package org.wangzc.development.platform;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.wangzc.development.platform.util.AlertException;
import org.wangzc.development.platform.util.Result;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = AlertException.class)
    @ResponseBody
    public Result alertExceptionHandler(AlertException e) {
        return Result.fail(e.getMessage(), e);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    public Result maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return Result.fail("上传文件大小超过限制；", e);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        return Result.fail("未知异常", e);
    }

}
