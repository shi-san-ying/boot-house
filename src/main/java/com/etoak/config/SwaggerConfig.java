package com.etoak.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        ApiInfo apiInfo=new  ApiInfoBuilder().title("使用swagger创建rest风格的接口文档")
                .description("描述：使用swagger创建接口文档")
                .termsOfServiceUrl("http://www.etoak.com")//标准网址
                .version("1.0")
                .build();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)//文档描述信息
                .select()
                .paths(PathSelectors.any())//任意地址
                .apis(RequestHandlerSelectors.basePackage("com.etoak.controller"))//为那个包生成文档
                .build();
    }
}
