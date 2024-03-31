package io.zed.formforgebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FormForgeBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormForgeBeApplication.class, args);
    }

}
