package br.com.unidrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport
@EnableCaching
public class UnidriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnidriveApplication.class, args);
    }

}
