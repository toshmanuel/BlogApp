package com.blogapp.blogapp.service.post;

import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.data.repository.*;
import com.blogapp.blogapp.web.dto.PostDTO;
import com.blogapp.blogapp.web.exceptions.NullPostException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.ArrayList;
import java.util.List;

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
        try {
            when(postServiceImpl.savePost(new PostDTO())).thenReturn(testPost);
        } catch (NullPostException e) {
            e.printStackTrace();
        }
        try {
            postServiceImpl.savePost(new PostDTO());
        } catch (NullPostException e) {
            e.printStackTrace();
        }

        verify(postRepository, times(1)).save(testPost);
    }

    @Test
    void whenTheFindAllMethodIsCalled_thenReturnAListOfPosts(){

        List<Post> postList = new ArrayList<>();
        when(postServiceImpl.findAllPost()).thenReturn(postList);
        postServiceImpl.findAllPost();

        verify(postRepository, times(1)).findAll();

    }
}