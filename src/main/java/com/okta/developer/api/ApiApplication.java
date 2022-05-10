package com.okta.developer.api;

import com.okta.developer.dao.SketchDao;
import com.okta.developer.dto.SketchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication(scanBasePackages = "com.okta.developer")
@EntityScan("com.okta.developer.entities")
@EnableJpaRepositories("com.okta.developer.dao")
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}



