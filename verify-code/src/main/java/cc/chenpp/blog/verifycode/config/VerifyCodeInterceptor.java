package cc.chenpp.blog.verifycode.config;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import cc.chenpp.blog.verifycode.send.VerifyCodeService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyCodeInterceptor<T extends VerifyCode> implements HandlerInterceptor {

    private final VerifyCodeService<T> verifyCodeService;

    public VerifyCodeInterceptor(VerifyCodeService<T> verifyCodeService) {
        this.verifyCodeService = verifyCodeService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        this.verifyCodeService.checkCode();
        return true;
    }

}
