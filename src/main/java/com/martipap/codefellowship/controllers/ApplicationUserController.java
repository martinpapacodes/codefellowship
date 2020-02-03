package com.martipap.codefellowship.controllers;

import com.martipap.codefellowship.models.ApplicationUser;
import com.martipap.codefellowship.models.ApplicationUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

import java.util.List;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView createNewApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        ApplicationUser newUser = new ApplicationUser(username, passwordEncoder.encode(password), firstName, lastName, dateOfBirth, bio);
        System.out.println("newUser676666666666666 = " + newUser);
        applicationUserRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null , new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/users/{id}")
    public String showUserDetails(@PathVariable long id, Principal p, Model m) {

        if(p !=null) {
            m.addAttribute("principal", p.getName());
        } else {
            m.addAttribute("principal", "User");
        }


        ApplicationUser theUser = applicationUserRepository.findById(id).get();

        m.addAttribute("username", theUser.getUsername());
        m.addAttribute("firstName", theUser.getFirstName());
        m.addAttribute("lastName", theUser.getLastName());
        m.addAttribute("dateOfBirth", theUser.getDateOfBirth());
        m.addAttribute("bio", theUser.getBio());
        m.addAttribute("theUser", theUser);

        return "userDetails";
    }

    @GetMapping("/myprofile")
    public String showProfile(Principal p, Model m) {
        if(p != null) {
            m.addAttribute("principal", p.getName());
        } else {
            m.addAttribute("principal", "User");
        }

        ApplicationUser theUser = applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("username", theUser.getUsername());
        m.addAttribute("firstName", theUser.getFirstName());
        m.addAttribute("lastName", theUser.getLastName());
        m.addAttribute("dateOfBirth", theUser.getDateOfBirth());
        m.addAttribute("bio", theUser.getBio());
        m.addAttribute("id", theUser);
        m.addAttribute("theUser", theUser);

        return "profile";
    }

    @GetMapping("/allUsers")
    public String showAllUsers(Principal p, Model m) {
        if(p != null) {
            m.addAttribute("principal", p.getName());
        } else {
            m.addAttribute("principal", "User");
        }

        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        m.addAttribute("allUsers", allUsers);

        return "allUsers";
    }

    @PostMapping("userDetails/follow")
    public RedirectView createFollowers(long id, Principal p) {
        ApplicationUser theUser = applicationUserRepository.findByUsername(p.getName());
        ApplicationUser userToFollow = applicationUserRepository.findById(id).get();
        theUser.usersIFollow.add(userToFollow);
        applicationUserRepository.save(userToFollow);

        return new RedirectView("/allUsers");
    }

    @GetMapping("/feed")
        public String showFeed(Principal p, Model m) {
            m.addAttribute("principal", p.getName());
            ApplicationUser theUser = applicationUserRepository.findByUsername(p.getName());
            m.addAttribute("usersIFollow", theUser.usersIFollow);
            return "feed";
        }
    }




