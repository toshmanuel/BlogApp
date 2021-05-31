package com.blogapp.blogapp.data.repository;

import com.blogapp.blogapp.data.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findByTitle(String title);
//    @Query("SELECT p FROM Post p WHERE p.title = ?1")
//    Post findByBlogTitle(String title);

//    @Query("SELECT p FROM Post p WHERE p.title = :title")
//    Post findByBlogTitle(@Param("title")String title);
}
