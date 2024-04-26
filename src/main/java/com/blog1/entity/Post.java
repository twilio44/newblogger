package com.blog1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    //@Data - Both Getters and Setters are combined in @Data annotation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    // List<Comment> --> it can hold the instances of Comment class

    // Above comments (which is Comment's object address) is mapped to the post object (mappedBy = "post")
    // Above this "comments" is connected to post via third variable which is
    // foreign key (name = "post_id") which is in Comments.java
    // cascade --> for doing Composition, orphanRemoval --> if video delete comments are orphan, comments will be removed automatically because it is true, that doesn’t mean  remove from the database  I am removing the object
    // CascadeType.ALL --> If you make any change in the parent  Automatically all the changes you will see in the child  this is the meaning of Cascade
    // don't remember code - search in Chat Gpt - first put Comment.java and Post.java in Chat Gpt then type - code to perform one to many mapping between Post and comment
    // ArrayList<>() --> it can hold multiple comments

}

// Aggregation --> has a relation --> weak ownership --> Hotel and furniture
// Association --> Both are linked with each other But there is no ownership --> BF - GF relation
// is a Relation --> which is Inheritance --> Strongly coupled --> Ex.- cat "is a" animal, car
// "is a" a vehicle, Advantage - reusability, Disadvantage - strongly coupled,
// Compostion : destroy parent class object child class object should be destroy automatically