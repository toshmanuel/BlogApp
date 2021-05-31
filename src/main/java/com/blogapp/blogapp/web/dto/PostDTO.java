package com.blogapp.blogapp.web.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class PostDTO {
    
    @NotNull(message = "Title Cannot be Null")
    private String title;

    @NotNull(message = "Content Cannot Be Null")
    private String content;

    private String coverImageUrl;
}
