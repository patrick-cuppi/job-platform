package br.com.patickcuppi.job_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Job Platform API", version = "1.0", description = "API for Job Platform Application"))
public class JobPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPlatformApplication.class, args);
	}

}
