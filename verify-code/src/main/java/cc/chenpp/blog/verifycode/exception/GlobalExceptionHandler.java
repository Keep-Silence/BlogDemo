package cc.chenpp.blog.verifycode.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodeVerifyFailException.class)
    @ResponseBody
    public String codeVerifyFailExceptionHandler(CodeVerifyFailException e) {
        return e.getMessage();
    }

}
