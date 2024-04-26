package com.blog1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Blogger1Application {

	public static void main(String[] args) {

		SpringApplication.run(Blogger1Application.class, args);
	}

//	@Bean
//	public PasswordEncoder getEncodedPassword() {
//		return new BCryptPasswordEncoder();
//	}

	// we have taken these above code into --> SecurityConfig.java
}

// how this class created

// When you get stuck into such situation  Sprung IOC doesn’t know 
// which object to create  then you educate IOC  By going to your 
// root class and simply you apply  @Bean

// Now when you apply  @Bean  you are now telling to  Spring IOC 
// wherever passwordEncoder is being used  there create this object:
// return new BCryptPasswordEncoder();
// And inject its address into it

//Why did you define(above method where @Bean is there) here  because this is also a  Config file (@SpringBootApplication)
//Just keep the cursor on  @SpringBootApplication  you can see the configuration file
//This  BloggerApplication.java  also a default configuration file

								//Interview Question:


//What is the starting point of your application?
//@SpringBootApplication  defines the starting point of the project (of application)  project start from here
//This is the class where I can do configuration  I am not returning @Configure annotation and  inside the Configuration annotation only you can define  Bean Annotation
//Because I am now configuring IOC  and I am telling IOC  for PasswordEncoder  generate  this object
//Then in that case this:
		//@Bean
		//public PasswordEncoder getEncodedPassword(){
		//	return new BCryptPasswordEncoder();
		//}

//is also configuration file  why don’t you give it in  SecurityConfig.java
//but we are purposefully using bean in BloggerApplication.java to help you  to explore the things
//you can also use bean annotation in @Configuration class  Configuration class are nothing but  the classes that will execute before your project runs
//Because Configuration has to run first then the project
//Configuration has the basic details, so this configuration file IOC will read, It will understand that I have to create a bean and that bean it will inject here in SecurityConfig.java:
//private passwordEncoder passwordEncoder;

