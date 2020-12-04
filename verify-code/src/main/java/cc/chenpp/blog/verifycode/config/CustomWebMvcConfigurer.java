package cc.chenpp.blog.verifycode.config;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import cc.chenpp.blog.verifycode.send.VerifyCodeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private VerifyCodeService<VerifyCode> verifyCodeService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VerifyCodeInterceptor<>(verifyCodeService)).addPathPatterns("/testVerifyCode");
    }

}
