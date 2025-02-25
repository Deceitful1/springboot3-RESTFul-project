package br.com.deceitful1.restfulwithspringboot3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.deceitful1")
@EnableJpaRepositories(basePackages = "br.com.deceitful1.repositories")
@EntityScan(basePackages = "br.com.deceitful1")
public class RestfulWithSpringboot3Application {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWithSpringboot3Application.class, args);
	}

}
