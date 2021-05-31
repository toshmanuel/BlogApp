package com.blogapp.blogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public Cloudinary getCloudinary(){
		Cloudinary cloud = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "",
				"api_key", "my_api_key",
				"api_secret", "my_api_secret"
		))
	}
}
