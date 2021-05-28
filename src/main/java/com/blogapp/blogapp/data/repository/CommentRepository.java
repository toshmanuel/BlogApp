package com.blogapp.blogapp.data.repository;

import com.blogapp.blogapp.data.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
