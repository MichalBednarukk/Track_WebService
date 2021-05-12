package com.bednaruk.Track_WebService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.annotation.WebInitParam;

@SpringBootApplication
@WebInitParam(name = "compatibilityMode", value = "true")
public class  TrackWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackWebServiceApplication.class, args);
	}

}
