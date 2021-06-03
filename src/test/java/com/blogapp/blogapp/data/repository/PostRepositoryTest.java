package com.blogapp.blogapp.data.repository;

import com.blogapp.blogapp.data.models.Author;
import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.Matchers.hasSize;

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

    @Test
    @Transactional
    @Rollback(value = false)
    void deletePostById(){
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Post fetched the database --{}",savedPost);

        postRepository.deleteById(savedPost.getId());
        Post deletedPost = postRepository.findById(savedPost.getId()).orElse(null);

        assertThat(deletedPost).isNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updatedSavedPostTest(){
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("Title post 1");
        log.info("Post fetched the database --{}",savedPost);

        savedPost.setTitle("This is a good title");
        postRepository.save(savedPost);

        Post updatedPost = postRepository.findById(41).orElse(null);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo("This is a good title");

        log.info("Post title updated in the database --{}",updatedPost.getTitle());
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void updatedSavedPostAuthorTest() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getAuthor()).isNull();

        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Wick");
        author.setEmail("john@gmail.com");
        author.setPhoneNumber("090123456667");

        //map relationships
        savedPost.setAuthor(author);
        postRepository.save(savedPost);

        Post updatedPost = postRepository.findByTitle("Title post 1");
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getAuthor()).isNotNull();
        assertThat(updatedPost.getAuthor().getFirstName()).isEqualTo("John");

        log.info("Updated post --> {}", updatedPost);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void updatedSavedPostCommentTest() {
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getComments()).isNotNull();

        Comment comment1 = new Comment("");
        comment1.setContent("Hello everyone");
        comment1.setCommentator("James Bond");


        Comment comment2 = new Comment("");
        comment2.setContent("Good post");
        comment2.setCommentator("Lara Smith");

        savedPost.addComment(comment2, comment1);

        //map relationships
        postRepository.save(savedPost);

        Post updatedPost = postRepository.findById(41).orElse(null);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getComments()).isNotNull();
        assertThat(updatedPost.getComments().size()).isEqualTo(2);

        log.info("Updated post --> {}", updatedPost);
    }

    @Test
    void findAllPostInDescendingOrderTest(){
        List<Post> allPost = postRepository.findByOrderByDateCreatedDesc();
        allPost.get(0).getDateCreated()
                .isAfter(allPost.get(1)
                        .getDateCreated());

        allPost.forEach(post -> log.info("Post Index {}", post.getDateCreated()));
    }
}