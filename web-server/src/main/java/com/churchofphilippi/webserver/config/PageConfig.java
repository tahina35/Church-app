package com.churchofphilippi.webserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.page")
@Getter
@Setter
public class PageConfig {

    private int size;
    private int mobileSize;
}
