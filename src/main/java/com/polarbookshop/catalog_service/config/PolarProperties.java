package com.polarbookshop.catalog_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


//Beans annotated as @ConfigurationProperties listen RefreshScopeRefreshedEvent by default.
// This bean below will be refreshed by Spring Actuator, when we send POST request to /actuator/refresh

// The catalog-service will fetch new configs from config-server, and config server will fetch them from
//remote Git config-repo since we have configured config-service as force-pull: true
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
