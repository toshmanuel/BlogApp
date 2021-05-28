package com.blogapp.blogapp.data.repository;

import com.blogapp.blogapp.data.models.Author;
import com.blogapp.blogapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.Matchers.hasSize;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"}/*, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD*/)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void savePostToDBTest(){
        Post blogPost = new Post();
        blogPost.setTitle("What is fintech");
        blogPost.setContent("Lorem Ipsum is simply dummy text " +
                "of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's " +
                "standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of " +
                "type and scrambled it to make a type specimen boo");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();
    }

    @Test
    void duplicate(){
        Post blogPost = new Post();
        blogPost.setTitle("What is fintech");
        blogPost.setContent("Lorem Ipsum is simply dummy text " +
                "of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's " +
                "standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of " +
                "type and scrambled it to make a type specimen boo");

        log.info("Created a blog post --> {}", blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

        Post blogPost2 = new Post();
        blogPost2.setTitle("What is fintech");
        blogPost2.setContent("Lorem Ipsum is simply dummy text " +
                "of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's " +
                "standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of " +
                "type and scrambled it to make a type specimen boo");

        log.info("Created a blog post --> {}", blogPost2);
        assertThrows(DataIntegrityViolationException.class, ()->{
           postRepository.save(blogPost2);
        });

    }

    @Test
    @Rollback(value = false)
    void addAuthor(){
        Post blogPost = new Post();
        blogPost.setTitle("What is fintech");
        blogPost.setContent("Lorem Ipsum is simply dummy text " +
                "of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's " +
                "standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of " +
                "type and scrambled it to make a type specimen boo");


        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Wick");
        author.setEmail("john@gmail.com");
        author.setPhoneNumber("090123456667");

        //map relationships
        blogPost.setAuthor(author);
        author.addPost(blogPost);

        postRepository.save(blogPost);
        log.info("blog post after saving -->{}", author);
        log.info("blog post after saving -->{}", blogPost);
    }

    @Test

    void findAllPostInDB(){
        List<Post> existingPosts = postRepository.findAll();

        assertThat(existingPosts).isNotNull();
        assertThat(existingPosts).hasSize(5);
    }
}