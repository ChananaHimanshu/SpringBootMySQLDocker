package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example" })
public class SpringBootMySQLDockerApplication extends SpringBootServletInitializer {

	private static ApplicationContext applicationContext;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		System.out.println("---------Here in Builder--------------------->" + builder);
		return super.configure(builder);
		// return builder.sources(JPALearningBootApplication.class);
	}

	public static void main(String[] args) {
		System.out.println("-------Main Runs --> " + args);
		applicationContext = SpringApplication.run(SpringBootMySQLDockerApplication.class, args);
		checkBeansPresence("dataSource", "employeeController", "employeeRepository", "employeeService",
				"jpaLearningBootApplication");
	}

	public static void checkBeansPresence(String... beans) {
		for (String beanName : beans) {
			System.out.println(
					"Is " + beanName + " in ApplicationContext : " + applicationContext.containsBean(beanName));
		}
	}

}
