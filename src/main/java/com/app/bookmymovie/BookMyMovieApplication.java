package com.app.bookmymovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages="com.app.*")
public class BookMyMovieApplication { 

	public static void main(String[] args) {
		SpringApplication.run(BookMyMovieApplication.class, args);
		System.out.println("application booted");
	}
}
