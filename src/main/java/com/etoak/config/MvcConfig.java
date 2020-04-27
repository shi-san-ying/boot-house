package com.etoak.config;
import com.etoak.interceptor.LoginInerceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.mapping}")
    private String imgMapper;

    @Value("${upload.mapping}")
    private String imgMapping;

    // d:/upload/
    @Value("${upload.dir}")
    private String imgLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(imgMapper).addResourceLocations("file:"+imgLocation);

    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override//注册拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInerceptor())
                .addPathPatterns("/**")//拦截的请求
                .excludePathPatterns("/user/**")  //不能拦截登录和登录后跳转  注册的的请求
                .excludePathPatterns("/code")                        //不拦截验证码得请求
                .excludePathPatterns("/lib/**","/imgs/**");          //不拦截静态资源
    }
    //<mvc:View-Controller />
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
