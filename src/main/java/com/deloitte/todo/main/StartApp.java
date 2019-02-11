package com.deloitte.todo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.deloitte.todo.dao.Task;
import com.deloitte.todo.dao.TaskRepository;

@SpringBootApplication(scanBasePackages = { "com.deloitte.todo.*" })
@EnableJpaRepositories(basePackageClasses=TaskRepository.class)
@EntityScan(basePackageClasses=Task.class)
public class StartApp extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartApp.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StartApp.class, args);
	}

}
