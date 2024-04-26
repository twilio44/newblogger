package com.blog1.security;

// get all these code from gitHub no need to remember

import com.blog1.entity.User;
import com.blog1.entity.Role;
import com.blog1.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import javax.management.relation.Role;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

// Spring Security uses fix code so this program is fixed everywhere but also take the update from somewhere else

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // purpose of UserDetailsService --> UserDetailsService Interface gives a method -->
    // loadByUsername and this method helps to search the record in the database

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // to search the record in User table we require --> userRepository

    // below here --> loadUserByUsername(String usernameOrEmail) -> we can login in two ways -->
    // either give username and password OR give email and password
    // so here --> userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) --> username in left side and email in right side
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // This (CustomUserDetailsService) class has to be sub class of interface  UserDeailsService
        // This has a method  loadUserByUsername
        // Implement method  Alt +Enter on  CustomUserDetailService

        // Above , while the method loadUserByUsername itself only accepts one value (String usernameOrEmail),
        // the operations within the method (findByUsernameOrEmail) can involve multiple values ((usernameOrEmail, usernameOrEmail)), as demonstrated in the usage of findByUsernameOrEmail.
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) // username will be in left side and email in right side
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email :" + usernameOrEmail));

        // whatever data is coming from the database, now it is present in this object (user) see above
        // UsernameNotFoundException --> this is a built in class --> you need not develop this exception class  this exception class is given by your Spring Security --> we can see the import
        // user object consist of --> username, email and password

        // below if record not found then we have built in class --> User

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles())); // roles are multiple so this will call the below method, so it will call below method and replace it here (user.getRoles()) like --> admin, user whatever the role is there
        // Abobe User is a built in class which i am importing from here --> security.core.userdetails
        // so we have two User class so --> import keyword will not work  we need to give package name

    }
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){ // it will get all the role objects and then apply stream api
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}

// keep cursor on  UserDetailsService  Ctrl +Click:
// and we can see in this there is only one method (loadUserByUsername)   so this will become  a  Functional Interface
// Now how this method is designed  Automatically the username will go to this from where --> authenticationManager.authenticate (in AuthController.java)
// This method will take the username and based on the username it will search the record in database

                        // data entered by User and data from Dtabase

// So this method (In CustomUserDetailsService.java)
// @Override
// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    return null;
// }
//
// has record from the database and this (@RequestBody LoginDto loginDto) has the data entered by the user (In AuthController.java):
// @PostMapping("/signin")
// public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){

                //  Questions from ChatGpt for above program

//  In this line how it is getting username and password:

//  public UserDetails loadUserByUsername(String usernameOrEmail)

//  and how this below line is getting two arguments

//  User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)



//   Detail of above two return statement


//loadUserByUsername Method:
//
//This method is called when Spring Security needs to load user details based on the provided username or email.
//It retrieves the user from the database using the userRepository based on the provided usernameOrEmail.
//If the user is found, it constructs a UserDetails object using the user's email (used as the username), password, and authorities (roles).
//Authorities (roles) are obtained by calling the mapRolesToAuthorities method, passing the user's roles obtained from the database.
//mapRolesToAuthorities Method:
//
//This method takes a set of Role objects associated with the user.
//It uses Java 8 Stream API to convert each Role object into a SimpleGrantedAuthority object.
//Each Role object's name is extracted and used to create a SimpleGrantedAuthority.
//The Stream is collected into a list of GrantedAuthority objects.
//Finally, this list is returned.
//Construction of UserDetails Object:
//
//Inside the loadUserByUsername method, the UserDetails object is constructed using the retrieved user's email, password, and authorities.
//The email is used as the username.
//The password is fetched from the user object obtained from the database.
//Authorities are obtained by calling the mapRolesToAuthorities method with the user's roles.
//Return UserDetails:
//
//The constructed UserDetails object, containing the user's authentication details and authorities, is returned.
//This object is then used by Spring Security for authentication and authorization purposes during the login process.
//        In summary, the loadUserByUsername method retrieves user details from the database, including roles, and constructs a UserDetails object using the retrieved information. The mapRolesToAuthorities method is responsible for converting roles into authorities (permissions) and is called within the loadUserByUsername method to retrieve the user's authorities.