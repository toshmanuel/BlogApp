package com.blogapp.blogapp.web.controller;

import com.blogapp.blogapp.service.post.PostService;
import com.blogapp.blogapp.web.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping(value= "/create")
    public String create(Model model){
        model.addAttribute("post", new PostDTO());
        return "create";
    }
    @PostMapping("/save")
    public String savedPost(@ModelAttribute @Valid PostDTO postDto){
        return null;
    }
}
