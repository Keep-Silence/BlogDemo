package cc.chenpp.blog.verifycode.exception;

public class CodeVerifyFailException extends RuntimeException {

    public CodeVerifyFailException() {
        super("验证失败");
    }

}
