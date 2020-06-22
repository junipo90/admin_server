package com.example.study.controller;

import com.example.study.model.SearchParameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    // Localhost:8080/api/postMethod
    @PostMapping("/postMethod")
    public SearchParameter postMethod(@RequestBody SearchParameter searchParameter){
        return searchParameter;
    }
}
