package com.etoak.config;

<<<<<<< HEAD

=======
>>>>>>> dev
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
<<<<<<< HEAD
    @Value("${upload.mapping}")
    private String imgMapper;

=======

    @Value("${upload.mapping}")
    private String imgMapping;

    // d:/upload/
>>>>>>> dev
    @Value("${upload.dir}")
    private String imgLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
<<<<<<< HEAD
        registry.addResourceHandler(imgMapper).addResourceLocations("file:"+imgLocation);
=======
        registry.addResourceHandler(imgMapping)
                .addResourceLocations("file:" + imgLocation);
>>>>>>> dev
    }
}
