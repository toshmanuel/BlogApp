package com.blogapp.blogapp.web.dto;

//import javax.validation.constraints.NotNull;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDTO {
    
//    @NotNull(message = "Title Cannot be Null")
    private String title;

//    @NotNull(message = "Content Cannot Be Null")
    private String content;

    private MultipartFile imageFile;
}
