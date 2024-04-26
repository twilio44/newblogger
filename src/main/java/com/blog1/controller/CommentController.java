package com.blog1.controller;

import com.blog1.payload.CommentDto;
import com.blog1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
// Above This will have a handler method  which will return back a  Resposnse entity  which is a commentDto
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Handler method

    // http://localhost:8080/api/comments/1
    // @PostMapping("/{postId}")
    // http://localhost:8080/api/comments?postId=1
    @PostMapping
        public ResponseEntity<CommentDto> createComment(
                @RequestParam("postId") long postId, @RequestBody CommentDto commentDto) {

                // @PathVariable("postId") long postId, @RequestBody CommentDto commentDto)
                // if we use path parameter then we will use -> @PostMapping("/{postId}") and @PathVariable

            // So in the URL I will give the Id (in @RequestParam)  and as a JSON object(in commentDto) I will give the comment
            // id is of Post.java not of Comment.java which we are getting from URL
            // In JSON we will not give id because id is auto generated

            CommentDto dto = commentService.createComment(postId, commentDto);

            return new ResponseEntity<>(dto, HttpStatus.CREATED);

        }

    // http://localhost:8080/api/comments/1
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId) {

        commentService.deleteComment(commentId);

        return new ResponseEntity<>("Comment is deleted", HttpStatus.OK);
    }

    // http://localhost:8080/api/comments/1
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable long postId){

        // here we are getting comments according to post Id, we have one more column post_id in DB in comments1 table
        List<CommentDto> commentDto = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/posts
    // enter above URL in POSTMAN and in Browser
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){

        List<CommentDto> commentDtos = commentService.getAllComments();

        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }

    //After running the program password will come in the console

    //                           Spring Security

    // Add one jar file in pom.xml

//    <dependency>
//            <groupId>org.springframework.boot</groupId>
//            <artifactId>spring-boot-starter-security</artifactId>
//    </dependency>

    // above dependency is for Spring security
    // Now after spring security we cannot get the data without login password
    // http://localhost:8080/logout --> for logout from the page

    // Authorization  controlling the access  which feature   who can access

    // Which URL of your application  who can access I am controlling because
    // every URL of your  API is an access to the feature  (GetMapping, DeleteMapping they all are feature)


}


//                                          Complete flow:

//enter the URL:
//http://localhost:8080/api/comeents?postId=1
//Now click on   send  this JSON content will go to the  Dto (commentDto) and  postId (postId):

//CommentController.java:

//@PostMapping
//public ResponseEntity<CommentDto> createComment(@RequestParam("postId") long postId, @RequestBody CommentDto commentDto){
//    CommentDto dto = commentService.createComment(postId, commentDto);
//    return new ResponseEntity<>(dto, HttpStatus.CREATED);
//
//    That will intern call  createComment method in the  service Impl
//    The Service Impl based on post Id will search the post:
//    @Override
//    public CommentDto createComment(long postId, CommentDto commentDto) {
//        Post post = postRepository.findById(postId).orElseThrow(
//                () -> new ResourceNotFoundException("Post not found wih id: " + postId)
//
//                and the comment detail (which is coming from JSON) I will copy in  Comment Dto:
//        Comment comment = new Comment();
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
//
//        And then this comment I will set for  this post (below in the bracket)
//        comment.setPost(post);
//
//        Post post = postRepository.findById(postId).orElseThrow(
//                comment.setPost(post);  This line there is a comment object  I am setting the comment for a particular post
//
//        Remember all these  mapping happening at  object level
//        Though we say this is all database related things but hibernate deals with object
//        So there is one post object  Many comments
//        Once comment is saved I should return back  what is saved in the form of  Dto


//                             Authenntication:

//Authentication is to check whether the user name and the password is valid or not  we will check
// that with the help of Authentication

//                              Authorization

//Means which feature who is authorised to access, as a customer which feature he can
// access, as bank manager what feature you are capable of accessing in the application, as a cashier what feature you can access and what you cannot access  that I can control that with the help of Authorization.
