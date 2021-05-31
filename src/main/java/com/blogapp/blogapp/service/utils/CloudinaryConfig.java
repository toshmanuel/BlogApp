package com.blogapp.blogapp.service.utils;

import org.springframework.beans.factory.annotation.Value;

public class CloudinaryConfig {
    @Value("{CLOUD_NAME}")
    private String cloudName;

    @Value("{API_KEY}")
    private String apiKey;

    @Value("{API_SECRET}")
    private String apiSecret;
}
