package com.blogapp.blogapp.service.cloud;

import com.blogapp.blogapp.web.dto.PostDTO;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Test
    void uploadMultipartImageTest() throws IOException {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Test");
        postDTO.setContent("Test");

        Path path = Paths.get("/home/toshmanuel/IdeaProjects/blogapp/src/main/resources/static/asset/images/amazon.png");
//        File file = new File("/home/toshmanuel/IdeaProjects/blogapp/src/main/resources/static/asset/images/amazon.png");
        MultipartFile multipartFile = new MockMultipartFile("amazon.png",
                "amazon.png", "img/png",
                Files.readAllBytes(path));
        log.info("Multipart Object created --> {}", multipartFile);
        assertThat(multipartFile).isNotNull();
        postDTO.setImageFile(multipartFile);


        log.info("File name --> {}", postDTO.getImageFile().getOriginalFilename());
        cloudStorageService.uploadImage(multipartFile, ObjectUtils.asMap(
                "public_id", "blogapp/"+extractFileName(Objects.requireNonNull(postDTO
                        .getImageFile()
                        .getOriginalFilename()))
        ));

        assertThat(postDTO.getImageFile().getOriginalFilename()).isEqualTo("amazon.png");
    }

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }
}