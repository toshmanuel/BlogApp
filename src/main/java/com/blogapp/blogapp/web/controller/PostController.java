package com.blogapp.blogapp.web.controller;

import com.blogapp.blogapp.data.models.Post;
import com.blogapp.blogapp.service.post.PostService;
import com.blogapp.blogapp.web.dto.PostDTO;
import com.blogapp.blogapp.web.exceptions.NullPostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = "/posts")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public String getIndex(Model model) {
        List<Post> postList = postService.findAllPost();
        model.addAttribute("postList", postList);
        return "index";
    }

    @GetMapping(value= "/create")
    public String create(Model model){
//        model.addAttribute("post", new PostDTO());
        model.addAttribute("error", false);
        return "create";
    }
    @PostMapping("/save")
    public String savedPost(@ModelAttribute("post") @Valid PostDTO postDto,
                            BindingResult result, Model model){

        if (result.hasErrors()){
            return "create";
        }
        try{
            postService.savePost(postDto);
        } catch (NullPostException e) {
            log.error("Exception occurred --> {}", e.getMessage());
        }catch(DataIntegrityViolationException ex){
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Title Not Accepted, Already Exists");
//            model.addAttribute("post", new PostDTO());
            return "create";
        }
        return "redirect:/posts";
    }
/*
*Add the model attribute globally without
*having to specify the model to each methods
* */
    @ModelAttribute
    public void createPostModel(Model model){
        model.addAttribute("post", new PostDTO());
    }
}
