package cc.chenpp.blog.verifycode.config;

import cc.chenpp.blog.verifycode.send.EmailVerifyCodeSend;
import cc.chenpp.blog.verifycode.send.ImgVerifyCodeSend;
import cc.chenpp.blog.verifycode.send.SmsVerifyCodeSend;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VerifyCodeConfig {

    @Bean
    @ConditionalOnMissingBean(ImgVerifyCodeSend.class)
    public ImgVerifyCodeSend imgVerifyCodeSend() {
        return new ImgVerifyCodeSend();
    }

    @Bean
    @ConditionalOnMissingBean(SmsVerifyCodeSend.class)
    public SmsVerifyCodeSend smsVerifyCodeSend() {
        return new SmsVerifyCodeSend();
    }

    @Bean
    @ConditionalOnMissingBean(EmailVerifyCodeSend.class)
    public EmailVerifyCodeSend emailVerifyCodeSend() {
        return new EmailVerifyCodeSend();
    }
}
