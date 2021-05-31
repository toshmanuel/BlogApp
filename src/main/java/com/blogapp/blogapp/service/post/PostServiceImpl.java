package com.blogapp.blogapp.service.post;


import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.data.repository.PostRepository;
import com.blogapp.blogapp.web.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class PostServiceImpl implements PostService {


    @Autowired
    PostRepository postRepository;
    @Override
    public Post savePost(PostDTO postDto) {
        Post post = new Post();
        ModelMapper mapper = new ModelMapper();
        mapper.map(postDto, post);

        log.info("Post object after mapping --> {}", post);
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAllPost() {
        return null;
    }

    @Override
    public Post updatePost(PostDTO postDto) {
        return null;
    }

    @Override
    public Post findById(Integer id) {
        return null;
    }

    @Override
    public void deletedPostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
        return null;
    }
}
