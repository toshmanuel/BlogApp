package com.blogapp.blogapp.web.dto;

//import javax.validation.constraints.NotNull;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostDTO {
    
    @NotNull(message = "Title Cannot be Null")
    @NotBlank
    private String title;

    @NotNull(message = "Content Cannot Be Null")
    @NotBlank
    private String content;

    private MultipartFile coverImageUrl;
}
