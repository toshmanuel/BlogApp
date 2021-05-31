package com.blogapp.blogapp.service.post;

import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.data.repository.*;
import com.blogapp.blogapp.web.dto.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import static org.mockito.Mockito.*;

class PostServiceImplTest {


    @Mock
    PostRepository postRepository;

    Post testPost;

    @InjectMocks
    PostServiceImpl postServiceImpl;
    @BeforeEach
    void setUp() {

        testPost = new Post();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenThe(){
        when(postServiceImpl.savePost(new PostDTO())).thenReturn(testPost);
        postServiceImpl.savePost(new PostDTO());

        verify(postRepository, times(1)).save(testPost);
    }
}