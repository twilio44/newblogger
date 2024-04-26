package com.blog1.repository;

import com.blog1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // above we are doing --> findByEmail(String email); --> and this will return
    // back an --> Optional class --> which is called as --> User --> Optional<User>
    Optional<User> findByUsernameOrEmail(String username, String email);
    // HQL Query for above line --> Select *from User where Username = ….  Or Email = … ;
    // above Username ( in findByUsernameOrEmail) should match with the username(private String username;) in User.java
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    // Above method is called from AuthController --> userRepository.existsByEmail(signUpDto.getEmail()))
}

// Now in UserRepository we have all the custom method present here
