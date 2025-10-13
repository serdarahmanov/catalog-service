package com.polarbookshop.catalog_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing //if I used JPA it would be @EnableJpaAuditing
public class DataConfig {


}
