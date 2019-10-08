package com.interview.test.photoproject;

import com.interview.test.service.SecurityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author  Chandra Bhushan Verma
 * Bootstrap class for photos upload app.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.interview.test.*")
@EnableJpaRepositories("com.interview.test.*")
@EntityScan("com.interview.test.*")
public class PhotoprojectApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(PhotoprojectApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PhotoprojectApplication.class);
	}

	public static void main(String[] args) {
		logger.info("Photo Application starting .......");
		SpringApplication.run(PhotoprojectApplication.class, args);
	}

}
