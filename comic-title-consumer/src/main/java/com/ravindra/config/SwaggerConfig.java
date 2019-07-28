package com.ravindra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig to get the API documentation for Comic service consumer API's.
 * 
 * @author Veera Shankara Ravindra Reddy Kakarla
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket swaggerAPI() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ravindra.controller")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Comic Consumer API").description("\"API to consume the Comic service API\"")
				.version("1.0.0").license("Trending Technologies").licenseUrl("www.trendingtechnologies.net")
				.contact(
						new Contact("Ravindra Kakarla", "www.trendingtechnologies.net", "info@trendingtechnologies.in"))
				.build();
	}
}