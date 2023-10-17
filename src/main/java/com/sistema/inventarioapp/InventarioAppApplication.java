package com.sistema.inventarioapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
public class InventarioAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioAppApplication.class, args);
	}

}
