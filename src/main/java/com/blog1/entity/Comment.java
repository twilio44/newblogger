package com.blog1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments1")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private String email;
    private String name;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // now we will have one more column --> post_id --> in DB --> comments1

    // @JoinColumn --> it is used to join two tables, so above its(name = "post_id") a foreign
    // key for post
    // here we are joining Post.java and Comment.java tables
}

// Why we are using --> private Post post;

// In the Comment class, the field private Post post; is used to establish a many-to-one
// relationship between the Comment entity and the Post entity.

//Relationship Representation: This field represents the relationship between a comment and
// its corresponding post. It indicates that each comment belongs to a single post.
//Annotations:

// @ManyToOne: This annotation indicates a many-to-one relationship between entities. It signifies
// that many comments can be associated with one post.
// @JoinColumn(name = "post_id"): This annotation specifies the name of the foreign key column
// (post_id) in the comments1 table that references the id column of the posts1 table. It
// establishes the association between the Comment and Post entities at the database level.