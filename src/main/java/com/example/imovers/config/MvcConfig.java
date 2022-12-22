package com.example.imovers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");
        exposeDirectory("/static" , registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        registry.addResourceHandler(  dirName + "/**")
                .addResourceLocations("classpath:" + dirName + "/")
                .setCachePeriod(60*2)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
