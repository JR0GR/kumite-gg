package gg.kumite.app;

import gg.kumite.app.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class KumiteGgApplication {

    public static void main(String[] args) {
        SpringApplication.run(KumiteGgApplication.class, args);
    }

}
