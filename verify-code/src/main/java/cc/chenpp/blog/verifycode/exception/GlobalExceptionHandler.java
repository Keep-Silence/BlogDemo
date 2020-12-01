package cc.chenpp.blog.verifycode.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodeVerifyFailException.class)
    @ResponseBody
    public String codeVerifyFailExceptionHandler(CodeVerifyFailException e) {
        return e.getMessage();
    }

}
