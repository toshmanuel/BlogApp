package com.blogapp.blogapp.service.post;

import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.web.dto.PostDTO;
import com.blogapp.blogapp.web.exceptions.NullPostException;

import java.util.List;

public interface PostService {
    Post savePost(PostDTO postDto) throws NullPostException;

    List<Post> findAllPost();

    Post updatePost(PostDTO postDto);
    Post findById(Integer id);
    void deletedPostById(Integer id);

    Post addCommentToPost(Integer id, Comment comment);
}
