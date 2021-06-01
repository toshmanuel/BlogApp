package com.blogapp.blogapp;

import com.blogapp.blogapp.service.utils.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApplication {

	@Autowired
	CloudinaryConfig cloudinary;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public Cloudinary getCloudinary(){
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudinary.getCloudName(),
				"api_key", cloudinary.getApiKey(),
				"api_secret", cloudinary.getApiSecret()
		));
	}
}
