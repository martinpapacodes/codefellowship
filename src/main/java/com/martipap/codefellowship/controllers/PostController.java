package com.martipap.codefellowship.controllers;

import com.martipap.codefellowship.models.ApplicationUser;
import com.martipap.codefellowship.models.ApplicationUserRepository;
import com.martipap.codefellowship.models.Post;
import com.martipap.codefellowship.models.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/post")
    public RedirectView createNewPost(long id, String body) {

        Date time = Calendar.getInstance().getTime();
        DateFormat timeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String timeStamp = timeFormat.format(time);
        ApplicationUser theUser = applicationUserRepository.findById(id).get();
        Post newPost = new Post(theUser, body, timeStamp);
        postRepository.save(newPost);
        return new RedirectView("/myprofile");

    }
}
