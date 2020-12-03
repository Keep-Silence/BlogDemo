package cc.chenpp.blog.verifycode;

import cc.chenpp.blog.verifycode.enums.VerifyTypeEnum;
import cc.chenpp.blog.verifycode.model.VerifyCode;
import cc.chenpp.blog.verifycode.send.SmsVerifyCodeSend;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

@SpringBootTest
public class VerifyCodeApplicationTest {

    @Resource
    ApplicationContext applicationContext;


    @Test
    void contextLoads() {
        VerifyCode verifyCode = new VerifyCode(VerifyTypeEnum.SMS, "1234" );
        SmsVerifyCodeSend bean = applicationContext.getBean(SmsVerifyCodeSend.class);
        bean.send(verifyCode);
    }

}