package com.blogapp.blogapp.service.post;


import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.data.repository.PostRepository;
import com.blogapp.blogapp.web.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    @Autowired
    PostRepository postRepository;
    @Override
    public Post savePost(PostDTO postDto) {
        return postRepository.save(new Post());
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
