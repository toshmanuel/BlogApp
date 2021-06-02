package com.blogapp.blogapp.service.post;


import com.blogapp.blogapp.data.models.Comment;
import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.service.cloud.CloudStorageService;
import com.blogapp.blogapp.web.dto.PostDTO;
import com.blogapp.blogapp.data.repository.PostRepository;
import com.blogapp.blogapp.web.exceptions.NullPostException;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CloudStorageService configurationService;
    @Override
    public Post savePost(PostDTO postDto) throws NullPostException {
        if (postDto == null) throw new NullPostException();

        Post post = new Post();

        if(postDto.getImageFile() != null){
//            Map<?, ?> params = new HashMap<>();

//            params.put("public_id", "blogapp/"+postDto.getImageFile().getName());
//            params.put("overwrite", true);
//            log.info("params --> {}", params);
            try {
                Map<?, ?> uploadResult =configurationService.uploadImage(postDto.getImageFile(),
                        ObjectUtils.asMap("public_id",
                        "blogapp/"+postDto.getImageFile().getOriginalFilename(), "overwrite", true));
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
