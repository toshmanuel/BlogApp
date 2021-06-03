package com.blogapp.blogapp.service.post;


import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.data.repository.PostRepository;
import com.blogapp.blogapp.service.cloud.CloudStorageService;
import com.blogapp.blogapp.web.dto.PostDTO;
import com.blogapp.blogapp.web.exceptions.NullPostException;
import com.blogapp.blogapp.web.exceptions.PostNotFoundException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CloudStorageService configurationService;

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }

    @Override
    public Post savePost(PostDTO postDto) throws NullPostException {
        if (postDto == null) throw new NullPostException();

        Post post = new Post();

        if(postDto.getCoverImageUrl() != null && !postDto.getCoverImageUrl().isEmpty()){
//            Map<?, ?> params = new HashMap<>();

//            params.put("public_id", "blogapp/"+postDto.getImageFile().getName());
//            params.put("overwrite", true);
//            log.info("params --> {}", params);
            try {
                Map<?, ?> uploadResult =configurationService.uploadImage(postDto.getCoverImageUrl(),
                        ObjectUtils.asMap("public_id",
                        "blogapp/"+ extractFileName(Objects.requireNonNull(postDto.getCoverImageUrl().getOriginalFilename()))
                        ));
                post.setCoverImageUrl((String) uploadResult.get("url"));
                log.info("params --> {}", uploadResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("params --> {}", postDto);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
//        Post post = new Post();
//        ModelMapper mapper = new ModelMapper();
//        mapper.map(postDto, post);
//
//        log.info("Post object after mapping --> {}", post);
        try{
            return postRepository.save(post);
        }
        catch(DataIntegrityViolationException ex){
            log.info("Exception occurred --> {}", ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }
    @Override
    public void deleteAllPost() {
        postRepository.deleteAll();
    }

    @Override
    public List<Post> findPostInDescOrder() {
        return postRepository.findByOrderByDateCreatedDesc();
    }

    @Override
    public Post updatePost(PostDTO postDto) {
        return null;
    }

    @Override
    public Post findById(Integer id) throws PostNotFoundException {
        if(id == null){
            throw new NullPointerException("Post Cannot be Null");
        }
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            return post.get();
        }else{
            throw new PostNotFoundException("Post with Id --> {}");
        }
    }

    @Override
    public void deletedPostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
        return null;
    }
}
