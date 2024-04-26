package com.blog1.controller;

import com.blog1.entity.Post;
import com.blog1.payload.PostDto;
import com.blog1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// @RestController is used to create controllers for REST API's which can return JSON
// http://localhost:8080/api/posts     --> to get all posts

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    But here below status code is 201 in POSTMAN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
//    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){

//        @Valid for doing Validation in the Dto for title, description and content but checking is happening here
        // ResponseEntity<?> -> here we have question mark (?) before it was PostDto --> but here
        // we have two data is getting return If a method has different types of value being
        // return  use a Question mark (?)  and that tackle a situation
        // two data types are --> String(getDefaultMessage()) & dto see below

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

//            if any error then it will send an error message (which is inside PostDto) to POSTMAN
//            we are using this to find wheather size are proper or not
        }

        PostDto dto = postService.createPost(postDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }


//    2ND WAY
//    Another way of saving json content into database and again return the content to POSTMAN
//    But here status code is 200

//    @PostMapping
//    public PostDto createPost(@RequestBody PostDto postDto){
//
//        PostDto dto = postService.createPost(postDto);
//
//        return dto;
//
//    }


//    3RD WAY
//    Below one is without Dto i am saving the data and return back the content to POSTMAN
//    But here status code is 200

//    http://localhost:8080/api/posts

//    @PostMapping
//    public Post createPost(@RequestBody Post post){
//
//        Post posts = postService.createPost(post);
//
//        return posts;
//
//    }


//    below method it is necessary to check whether id is present or not in the database
//    exception we will throw in service layer and handle in GlobalExceptionHandler


    // http://localhost:8080/api/posts/30
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {

        postService.deletePost(id);

        return new ResponseEntity<>("post is deleted", HttpStatus.OK);
    }

    //                Without Pagination  --> it will give all the records

//    @GetMapping
//    public ResponseEntity<List<PostDto>> getAllPosts() {
//
//        List<PostDto> postDtos = postService.getAllPosts();
//        // return postDtos;
//        //   OR
//        return new ResponseEntity<>(postDtos, HttpStatus.OK);
//
//    }

    //                    With Pagination  --> it will give limited records

    // http://localhost:8080/api/posts?pageNo=0&pageSize=3
    // it will give 3 records of first page
    // http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name="pageNo", defaultValue="0", required=false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir)   {

        // above in pageSize default value is 3 means if we will not give page size in URL
        // then by default our page size will be 3 means only 3 records
        // Above Here we are giving default value =”id”  which means if I don’t give anything  sorting will
        // happen based on  id  and we will take this into the variable string sortBy
        List<PostDto> postDtos = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        // Do Alt+Enter on  sortBy  Add String as 3rd parameter to abstract
        // method “getAllPosts”  refactor
        // return postDtos;
        //   OR
        // System.out.println(postDtos); It will give all posts in console
        return new ResponseEntity<>(postDtos, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PostDto> updatePost(@RequestParam long postId, @RequestBody PostDto postDto) {
        PostDto dto = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);

        // Above RequestParam will take data from URL and RequestBody will take JSON content
    }
//    @PutMapping
//    public ResponseEntity<PostDto> updatePost(
//            @RequestParam long postId,
//            @RequestBody PostDto postDto
//    ){
//        PostDto dto = postService.updatePost(postId, postDto);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }



}
