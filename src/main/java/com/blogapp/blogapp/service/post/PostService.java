package com.blogapp.blogapp.service.post;

import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.web.dto.PostDTO;
import com.blogapp.blogapp.web.exceptions.NullPostException;
import com.blogapp.blogapp.web.exceptions.PostNotFoundException;

import java.util.List;

public interface PostService {
    Post savePost(PostDTO postDto) throws NullPostException;

    List<Post> findAllPost();

    Post updatePost(PostDTO postDto);
    Post findById(Integer id) throws PostNotFoundException;
    void deletedPostById(Integer id);
    void deleteAllPost();

    List<Post> findPostInDescOrder();

    Post addCommentToPost(Integer id, Comment comment);
}
