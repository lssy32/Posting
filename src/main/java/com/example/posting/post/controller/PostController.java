package com.example.posting.post.controller;

import com.example.posting.post.dto.PostRequestDto;
import com.example.posting.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping()
    public ResponseEntity post(@RequestBody PostRequestDto postRequestDto){
        postService.post(postRequestDto);
        return new ResponseEntity("포스팅 성공", HttpStatus.OK);
    }
}
