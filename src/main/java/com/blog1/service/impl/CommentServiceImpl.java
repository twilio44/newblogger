package com.blog1.service.impl;

import com.blog1.entity.Comment;
import com.blog1.entity.Post;
import com.blog1.exception.ResourceNotFoundException;
import com.blog1.payload.CommentDto;
import com.blog1.repository.CommentRepository;
import com.blog1.repository.PostRepository;
import com.blog1.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    // Above we have Constructor based dependency injection not @Autowired

    @Override
    // public ResponseEntity<CommentDto> createComment(long postId, CommentDto commentDto)
    // so it will return back CommentDto not Response
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post not found with id: " + postId)
        );

        // After orElseThrow  We have supplied supplier

        Comment comment = new Comment();

        // Add @Data in Comment.java to get getters and setters
        // now copy from dto to Entity because CRUD operation can be done only on Entity
        // id is Auto generated so no need to set id, see below

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        // Above Now this comment  I need to set it for this post
        // (comment entity have  setPost(post) object  which means that  I am setting up the comment (above) for this  post(below)
        // and this post we are getting from the database based on id which we are giving in URL
        // All these mapping happening at object level

        comment.setPost(post);
        // this comment(above) i will set for this post (Post post = postRepository.findById(postId).orElseThrow()

        Comment savedComment = commentRepository.save(comment);
        // Above we have saved the  comment  once you save the comment  we will get  savedComment
        // Now convert savedComment into Dto

        CommentDto dto = new CommentDto();

        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());

        // savedComment also consist id because it is coming from DB, so here also set the id, because we have to return dto to POSTMAN

        return dto;
    }
    @Override
    public void deleteComment(long commentId) {

        commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found with id: " + commentId)
        );
        commentRepository.deleteById(commentId);

    }
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        List<CommentDto> commentDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        //List<CommentDto> commentDtos = comments.stream().map(CommentServiceImpl::mapToDto).collect(Collectors.toList());

        // Above we are using map(CommentServiceImpl::mapToDto) --> it will call mapToDto method and ond one by one comments object will go to comment which is in maptoDto
        // But for that we have to make the mapToDto method static
        return commentDtos;

    }

    @Override
    public List<CommentDto> getAllComments() {

        List<Comment> comments = commentRepository.findAll();

        List<CommentDto> dtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dtos;
    }

    CommentDto mapToDto(Comment comment) {

        //static CommentDto mapToDto(Comment comment) --> if we are using --> map(CommentServiceImpl::mapToDto)

        CommentDto dto = new CommentDto();

        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

        return dto;
    }

}

//Flow: MVC Architecture
//From POSTMAN  I am calling  RestController  RestController is calling  Service layer  Service layer back to  RestController  RestController back to  POSTMAN
