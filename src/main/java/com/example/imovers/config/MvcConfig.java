package com.example.imovers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path dir = Paths.get("src/main/resources/static/");
        String dirPath = "file:" + dir.toFile().getAbsolutePath();
        //System.out.println(dirPath);
        registry
                .addResourceHandler("/assets/**")
                .addResourceLocations(dirPath + "/");

    }
   /* private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        registry.addResourceHandler(  dirName + "/**")
                .addResourceLocations("classpath:" + dirName + "/")
                .setCachePeriod(60*2)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }*/
}
