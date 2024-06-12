package com.jsp.whm.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDoc 
{
	@Bean
	public OpenAPI openAPI()
	{
		return new OpenAPI().info(new Info()
				.title("The_Warehouse_Manager")
				.version("v1")
				.description("Spring Boot Project built using **RESTful** Architecture, covers all the basic CRUD Operations"
						+" `public class ApplicationDoc`\n"
						+"### Features:\n"
						+"- Covers all CRUD Operations\n "
						+"- Performed Field Validations\n"
						+"- Used DTOs to control inbound and outbound data"));
				
	}

}
