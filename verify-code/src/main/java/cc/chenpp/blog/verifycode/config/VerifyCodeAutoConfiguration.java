package cc.chenpp.blog.verifycode.config;

import cc.chenpp.blog.verifycode.model.VerifyCode;
import cc.chenpp.blog.verifycode.send.EmailVerifyCodeSend;
import cc.chenpp.blog.verifycode.send.ImgVerifyCodeSend;
import cc.chenpp.blog.verifycode.send.SmsVerifyCodeSend;
import cc.chenpp.blog.verifycode.send.VerifyCodeService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class VerifyCodeAutoConfiguration {

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

    @Bean
    @ConditionalOnMissingBean(VerifyCodeService.class)
    public <T extends VerifyCode> VerifyCodeService<T> verifyCodeSendService() {
        return new VerifyCodeService<>();
    }

    @Bean
    public DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 验证码是否带边框 No
        properties.setProperty("kaptcha.border", "no");
        // 验证码字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        // 验证码整体宽度
        properties.setProperty("kaptcha.image.width", "200");
        // 验证码整体高度
        properties.setProperty("kaptcha.image.height", "50");
        // 文字个数
        properties.setProperty("kaptcha.textproducer.char.length", "5");
        // 文字大小
        properties.setProperty("kaptcha.textproducer.font.size", "40");
        // 文字随机字体
        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
        // 文字距离
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        // 干扰线颜色
        properties.setProperty("kaptcha.noise.color", "black");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
