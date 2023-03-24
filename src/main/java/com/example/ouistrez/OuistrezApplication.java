package com.example.ouistrez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@EnableSwagger2
@SpringBootApplication
public class OuistrezApplication {

	public static void main(String[] args) {
		SpringApplication.run(OuistrezApplication.class, args);
	}

}
