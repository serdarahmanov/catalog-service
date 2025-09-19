package com.polarbookshop.catalog_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    //Javadoc style serves both as a developer note and official documentation for the greeting field.

    /**
     * A message to welcome users
     */
    private String greeting;



    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
