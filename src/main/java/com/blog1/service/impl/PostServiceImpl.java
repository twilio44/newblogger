package com.blog1.service.impl;

import com.blog1.entity.Post;
import com.blog1.exception.ResourceNotFoundException;
import com.blog1.payload.PostDto;
import com.blog1.repository.PostRepository;
import com.blog1.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }


    //    Here we are getting that content (savedPost) which we are saving
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepo.save(post);

        PostDto dto = new PostDto();

        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());

        //dto.setMessage("Post is Created");
        // above line if we want this message in POSTMAN

//        here we can see the benefit of dto concept because we have created one new variable message in dto but if we
//        will create this variable in Entity class then it will effect our table and will create new column in the table
        return dto;
    }

    // 3rd way

//    Here we are getting that content (savedPost) which we are saving
//    @Override
//    public Post createPost(Post post) {
//        Post savedPost = postRepo.save(post);
//
//        post.setId(savedPost.getId());
//        post.setTitle(savedPost.getTitle());
//        post.setDescription(savedPost.getDescription());
//        post.setContent(savedPost.getContent());
//        return post;
//    }

    // But Above we are unable to send a message that record is saved



    @Override
    public void deletePost(long id) {

//        in below coding throwing Exception is Successful but handling is not done

//        Optional<Post> byId = postRepo.findById(id);
//        if(byId.isPresent()){
//            postRepo.deleteById(id);
//        }else{
//            throw new ResourceNotFoundException("Post not found with id: "+id);
//        }

//        in below coding also throwing Exception is Successful but handling is not done for that we will create another class global exception
//        below code acts like try block
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id: " + id)
        );

        // Above is just like a supplier interface see below how
//        Supplier<A> mysuppliers = () -> new A();
//        A a = mysuppliers.get();
//        System.out.println(a);

        // orElseThrow is using supplier functional interface and syntax for supplier is - ()


        postRepo.deleteById(id);

// Above exception is being caught here in  GlobalExceptionHandler.java:

    }

    //                           Without Pagination

//    @Override
//    public List<PostDto> getAllPosts() {
//        List<Post> allPosts = postRepo.findAll();
//
//        List<PostDto> dtos = allPosts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
//
//        return dtos;
//
//    }

    //                              With Pagination

    // http://localhost:8080/api/posts?pageNo=0&pageSize=3
    // it will give 3 records of first page
    // http://localhost:8080/api/posts?pageNo=0&pageSize=6&sortBy=content --> it will sort content
    // http://localhost:8080/api/posts?pageNo=0&pageSize=6&sortBy=title  --> it will sort title
    // if you don’t give anything  sorting will happen based on  id :
    // http://localhost:8080/api/posts?pageNo=0&pageSize=5
    // Above all sorting are ascending order

    // For Descending --> so in URL  Add one more thing:        sortDir = desc      --> We can give any name  sortDir OR sortOrder
    // http://localhost:8080/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=asc

    // below we will use -->                      Turnary operator --> see in PracticMainUtil.java
    // turnary operator for ascending and descending order

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        //Sort sort = (sortDir.equalsIgnoreCase("asc")) ?Sort.by(sortDir).ascending():Sort.by(sortDir).descending();
        // Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ?Sort.by(sortDir).ascending() : Sort.by(sortDir).descending();
        Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // we are getting this error --> No property 'desc' found for type 'Post']
        // so apply breakpoints and run in a debug mode
        // Use  sortBy  instead of  sortDir --> to remove error
        // Sort.by(sortBy).ascending() --> it means --> sortBy means --> title --> because in
        // URL are sorting according to title --> Sort by - title - in ascending order --> sortBy  content  ascending order, sortBy  description  ascending order, sortBy  id  ascending order

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        // Above we have replaced Sort.by(sortBy) with sort object

        // Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        // above we are using --> Sort.by() because there are two parameter which are
        // integer but sortBy is a String so  we need to convert it into sort object

        Page<Post> pagePosts = postRepo.findAll(pageable);

        // above we have selected that findAll() which takes Pageable object
        List<Post> posts = pagePosts.getContent();

        // to convert page to list apply getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        // System.out.println(dtos);  it will also give all posts
        // Now convert Entity object (posts) to dto
        return dtos;

    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {

        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with id :" +postId)
        );

        // Above in post object we have database content, now we have to transfer JSON
        // content which is in postDto to Entity content

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post savedPost = postRepo.save(post);

        PostDto dto = mapToDto(savedPost);

        return dto;
    }


    PostDto mapToDto(Post post) {

        PostDto dto = new PostDto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());

        return dto;
    }


}
