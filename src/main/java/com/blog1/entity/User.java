package com.blog1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

//Search on ChatGpt:
// Create User Entity having primary key as id, email, name, password, username
// with many to many Mapping with Role Entity having id as primary key & role Name in Spring Boot
// and it will give User and Role entity class

// user class is map to role class so I am making as  set

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)  //  It signifies that a user can have multiple roles, and a role can be assigned to multiple users.
    @JoinTable(
            name = "user_roles",  // here we have created third table
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),   // It is referencing to id Column of User So add the reference column  referencedColumnName = "id"
            // above id column ("user_id") of user_roles table and id column ("id") of User Entity both are linked
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
            // joinColumns is for Parent Table , Here User is parent and Role is child
            // inverseJoinColumns refers to the columns in the join table that reference the primary key of the non-owning entity (Role in this case).
    )
    private Set<Role> roles;

    // see the detail of above line and ManyToMany mapping at last

    // Constructors, getters, and setters

    // above we have third table and name is user_roles, user_roles table has two foreign key column in his table one is user_id another is role_id

}

                                // FetchType.EAGER

//What is  FetchType.EAGER ?  so there is EAGER and LAZY  what is the difference
//In layman term EAGER  which student study everything before interview whether questions will come or not, LAZY  which student study after interview and then he prepare means lazy fellow will prepare when questions are being asked
//LAZY  Will load only those tables into your memory which is required at that point of time
//In my project I am using only these two tables (User & Role)  only those two tables and the related tables will be loaded
//But if I go with the type  EAGER
//EAGER  All your database Entity class tables are loaded to your memory
//        Which is loaded into the hibernate cache memory
//        EAGER will load all your database table information into Cache because when you write hibernate query language  It is interacting with a information firstly present in Cache  Because if information is present in cache it will be fast (It is like temporary memory)
//But if the information is missing there then it will take more time for you to load the results from the database
//So hibernate cache has the temporary information which improves the performance of your hibernate, once a table is loaded into hibernate cache for the next subsequent time it will automatically refer the cache, it will not do the task of again taking the table and loading it into the cache memory. Once loaded that information is in the temporary file
//
//LAZY Fetch Type  When the association is marked as “FetchType.LAZY”,  it means that the associated data should be loaded only when it is explicitly requested.
//When it is required LAZY will be loaded. Entity which means that I was telling Table But ultimately Entity is nothing but the table that we are dealing with
//But the right way to correct this information we are actually loading the objects only because tables are not loaded, tables are converted into Entity and Entity is loaded into the temporary memory, So LAZY will load only those Entity which is  required at that given  point of time
//An EAGER will load all the Entities without even having the requirement of its usage currently in the program
//Imagine if we have got 500 Entities  All 500 Entities to be loaded in EAGER FetchType
//If you do LAZY  It will load only that  so loading time will reduce, the performance is better

                            // Detail of JoinTable

//What is @JoinTable
//How do you join two table
//If you will see the diagram  I am joining the table based on third table (user_roles)
//So we need third table to join two tables
//So we are using  @JoinTable  to join table and the name is given here (name = "user_roles")
        //User.java:

            //@ManyToMany(fetch = FetchType.EAGER)
            //@JoinTable(
            //        name = "user_roles",
            //        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            //        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
            //)


//In @JoinTable and name = "user_roles" will automatically create a  third table which join  User Table and Roles table
//In joinColumns this becomes foreign key ("user_id"):
//joinColumns = @JoinColumn(name = "user_id")
//in your third table but the primary key is in the User table
//So User Table id is Primary key, user_id becomes foreign key
//Inverse join column is the other way  roles id is a  primary key
//role_id will become foreign key because third table has only foreign keys entry

//Note:
//User was a set because that was many, why role was set because it was many, On the
// Users table I am writing  @JoinTable  because I want User table to join with a
// Role table with a third table  What is the third table I am using here ?  that
// happens to be user_roles

// referencedColumnName = "id" --> In this case, it's specifying that the foreign key
// column in the user_roles table that references the User entity should be linked to
// the id column of the User entity.

// This is necessary because the user_id column in the user_roles table is acting as a
// foreign key referencing the primary key of the User entity, which is the id column.
// By specifying referencedColumnName = "id", you're explicitly stating that the id column
// in the User entity is the one being referenced.

//The line private Set<Role> roles; in the User entity class represents a collection of roles
// associated with a user. Here's why it's used:

//Many-to-Many Relationship: This field is used to establish a many-to-many relationship between
// the User entity and the Role entity. It signifies that a user can have multiple roles, and a
// role can be assigned to multiple users.