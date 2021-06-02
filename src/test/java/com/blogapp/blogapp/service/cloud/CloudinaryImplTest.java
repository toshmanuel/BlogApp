package com.blogapp.blogapp.service.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CloudinaryImplTest {

    @Autowired
    CloudStorageService cloudStorageService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void upload(){
        File file = new File("/home/toshmanuel/IdeaProjects/blogapp/src/main/resources/static/asset/images/amazon.png");
        Map<Object, Object> params = new HashMap<>();

        assertThat(file.exists()).isTrue();
//        params.put("public_id", "blogapp");
//        params.put("folder", "blogapp");
        params.put("public_id", "blogapp/post_file1");
        params.put("overwrite", "true");

        try {
            log.info("cloudinary response --> {}", cloudStorageService.uploadImage(file, params));
        } catch (IOException e) {
            log.info("--> {}", params);
            e.printStackTrace();
        }

    }@Test
    void uploadMultiPart(){
        File file = new File("/home/toshmanuel/IdeaProjects/blogapp/src/main/resources/static/asset/images/amazon.png");
        Map<Object, Object> params = new HashMap<>();

        assertThat(file.exists()).isTrue();
//        params.put("public_id", "blogapp");
//        params.put("folder", "blogapp");
        params.put("public_id", "blogapp/post_file1");
        params.put("overwrite", "true");

        try {
            log.info("cloudinary response --> {}", cloudStorageService.uploadImage(file, params));
        } catch (IOException e) {
            log.info("--> {}", params);
            e.printStackTrace();
        }

    }
}