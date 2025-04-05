package jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 启动类
 */
@EnableJpaAuditing
//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class SpringContextApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringContextApplication.class, args);
    }
}
