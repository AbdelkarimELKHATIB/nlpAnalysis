package com.sqli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.core","com.controller","com.model"})
public class NlpRestVer2Application {

	public static void main(String[] args) {
		SpringApplication.run(NlpRestVer2Application.class, args);
	}

}
