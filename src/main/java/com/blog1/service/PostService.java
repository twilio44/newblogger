package com.blog1.service;

import com.blog1.payload.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService{

    public PostDto createPost(PostDto postDto);

    // 3rd way

//    public Post createPost(Post post);

    void deletePost(long id);


    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);


    PostDto updatePost(long postId, PostDto postDto);
}
