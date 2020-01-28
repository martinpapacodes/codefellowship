package com.martipap.codefellowship.controllers;

import com.martipap.codefellowship.models.ApplicationUser;
import com.martipap.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.Positive;
import java.util.Date;

@Controller
public class ApplicationUserController {


//     this.userName = userName;
//        this.passWord = passWord;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.bio = bio;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String userName, String passWord, String firstName, String lastName, Date dateOfBirth, String bio) {
        System.out.println("You are adding a user--------------");
        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(passWord), firstName, lastName, dateOfBirth, bio);

        applicationUserRepository.save(newUser);

        return new RedirectView("/");
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
