package com.example.demo.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *  file映射项目外文件夹
 * </p>
 *
 * @author anonymous
 * @since 2022-06-29
 */

@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String filePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("file/**").addResourceLocations("file:" + filePath+"/");
    }
}
