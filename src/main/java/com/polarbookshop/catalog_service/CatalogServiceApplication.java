package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.config.PolarProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan //this tells Spring to scan classes annotated as @ConfigurationProperties

public class CatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);


		System.out.println("Java version: " + System.getProperty("java.version"));
		System.out.println("Runtime: " + System.getProperty("java.runtime.name"));
		System.out.println("Vendor: " + System.getProperty("java.vendor"));
	}

}
