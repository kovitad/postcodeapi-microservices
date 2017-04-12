package com.demo.api.postcode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The configuration class
 * Method Validation Configuration 
 * Swagger 2 Documentation Configuration
 * 
 * @author kovitadjanlakhon
 *
 */

@Configuration
@EnableSwagger2
public class ApplicationConfiguration {
	@Bean
	 public MethodValidationPostProcessor methodValidationPostProcessor() {
	      return new MethodValidationPostProcessor();
	 }
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select()                       
                .apis(RequestHandlerSelectors.basePackage("com.demo.api.postcode.controller"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
				
	}
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Post Code API Micro Services")
                .description("This is AUSPOST Excercise to determine developer who can develop restful API")
                .contact("Kovitad Janlakhon")
                .version("0.1")
                .build();
    }
}
