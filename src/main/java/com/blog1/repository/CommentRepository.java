package com.blog1.repository;

import com.blog1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);

//    Find by column name   PostId  it is a column name
//    Remember the template  findByPostId (template column name)


}
