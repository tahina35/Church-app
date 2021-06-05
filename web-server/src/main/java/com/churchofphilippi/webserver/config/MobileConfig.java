package com.churchofphilippi.webserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.mobile.conf")
@Getter
@Setter
public class MobileConfig {

    private String notificationTopic;
    private String notificationIcon;
    private String fcmAdminSDK;
}
