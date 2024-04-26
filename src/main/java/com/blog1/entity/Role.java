package com.blog1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

// here we are not using @Data why? here we are using Getter and Setter

    @Entity
    @Table(name = "roles1")
    @Setter
    @Getter
    public class Role {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 60)
        private String name;

//        @Column(unique = true, nullable = false)   // not used code
//        private String roleName;

//        @ManyToMany(mappedBy = "roles")   // not used code
//        private Set<User> users;

        // Constructors, getters, and setters

        // Above we can see role table also have many to many mapping with user table
        // set --> duplicate element are not allowed in set --> so that you can sign up to the
        // application only once
        // so we used set because user object and role object should be unique

    }

                            // @ManyToMany(mappedBy = "roles")

//    In the Role entity class, the mappedBy = "roles" attribute is used within the @ManyToMany
//    annotation. This attribute is used to indicate the owning side of the relationship in a
//    bidirectional many-to-many mapping.

//    Here's what it means in this context:
//
//    @ManyToMany(mappedBy = "roles"): This annotation establishes a many-to-many relationship
//    between the Role entity and the User entity. However, this attribute specifically indicates
//    that the User entity is the owning side of the relationship, and the mapping information for
//    this relationship is maintained in the roles field of the User entity.
//    When you use mappedBy, it means that the relationship between Role and User is defined on the
//    opposite side (the User entity in this case) and that the roles field in the User entity
//    manages the association.
//
//    So, mappedBy = "roles" indicates that the association between Role and User is managed by the
//    roles field in the User entity. This means that changes to the association (such as adding or
//    removing roles for a user) should be made through the roles field in the User entity, and
//    Hibernate will automatically update the association in the database accordingly.