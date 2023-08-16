package cn.com.pajk.uiatuomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan("cn.com.pajk")
public class UIAutomationApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(UIAutomationApplication.class,args);

    }
}
