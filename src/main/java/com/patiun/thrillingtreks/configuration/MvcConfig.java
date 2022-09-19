package com.patiun.thrillingtreks.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/campaigns").setViewName("campaigns");
        registry.addViewController("/sign-in-page").setViewName("signIn");
        registry.addViewController("/campaign-creator").setViewName("campaignCreator");
    }
}
