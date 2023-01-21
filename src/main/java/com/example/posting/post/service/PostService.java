package com.example.posting.post.service;

import com.example.posting.post.dto.PostRequestDto;
import com.example.posting.post.entity.Post;
import com.example.posting.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void post(PostRequestDto postRequestDto){
        postRepository.save(new Post(postRequestDto.getTitle(), postRequestDto.getContent()));
    }
}
