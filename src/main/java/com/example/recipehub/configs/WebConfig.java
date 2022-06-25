package com.example.recipehub.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String folderWithImageMeal = "File:///"+System.getProperty("user.home")+ File.separator+"imageMeals"+File.separator;
        registry.addResourceHandler("/recipes/image/**")
        .addResourceLocations(folderWithImageMeal);
    }
}
