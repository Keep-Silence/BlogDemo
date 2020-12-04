package cc.chenpp.blog.verifycode;

import cc.chenpp.blog.verifycode.send.VerifyCodeSend;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
public class VerifyCodeApplicationTest {

    @Resource
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Map<String, VerifyCodeSend> beansOfType = applicationContext.getBeansOfType(VerifyCodeSend.class);
        System.out.println(beansOfType.size());
    }

}