package com.blog1.service;

import com.blog1.payload.CommentDto;

import java.util.List;

public interface CommentService {
    //public ResponseEntity<CommentDto> createComment(long postId, CommentDto commentDto);
    // Because I am returning only one object dto (fromCommentServiceImpl.java) so  why to use list

    public CommentDto createComment(long postId, CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

    List<CommentDto> getAllComments();

    // And in this we are going to build a method  Response Entity which will
    // return back a  CommentDto  and we will supply two things to it
}
