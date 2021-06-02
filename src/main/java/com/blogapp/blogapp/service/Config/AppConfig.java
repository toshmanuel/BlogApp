package com.blogapp.blogapp.service.Config;

import com.blogapp.blogapp.service.utils.CloudinaryConfig;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    CloudinaryConfig cloudinary;

    @Bean
    public Cloudinary getCloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinary.getCloudName(),
                "api_key", cloudinary.getApiKey(),
                "api_secret", cloudinary.getApiSecret()
        ));
    }
}
