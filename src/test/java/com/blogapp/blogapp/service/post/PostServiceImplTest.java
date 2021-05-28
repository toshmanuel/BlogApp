package com.blogapp.blogapp.service.post;

import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.data.repository.PostRepository;
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
    PostService postServiceImpl;
    @BeforeEach
    void setUp() {

        testPost = new Post();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenThe(){
        when(postServiceImpl.savePost(new PostDTO())).thenReturn(testPost);
        postServiceImpl.savePost(new PostDTO());

        verify(postRepository.save(testPost), times(1));
    }
}