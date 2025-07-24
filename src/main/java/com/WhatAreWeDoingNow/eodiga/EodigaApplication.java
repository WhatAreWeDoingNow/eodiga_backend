package com.WhatAreWeDoingNow.eodiga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EodigaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EodigaApplication.class, args);
	}

}
