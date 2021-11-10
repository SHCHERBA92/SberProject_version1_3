package com.example.firstproject.controllers;

import com.example.firstproject.model.MessageFun;
import com.example.firstproject.model.Users;
import com.example.firstproject.repository.MessageFunRepository;
import com.example.firstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageFunController {

    @Autowired
    private MessageFunRepository messageFunRepository;

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/message/add")
    public String messageFun()
    {
        return "add_message_fun";
    }

    @PostMapping("/message/addMesFunPost")
    public String addMesFunPost(
//                                @AuthenticationPrincipal Users users,
                                @RequestParam String title,
                                @RequestParam String message)
    {
//        if (title == null || message == null)
//        {
//
//        }
        MessageFun messageFun = new MessageFun();
//        Users users = usersRepository.findAll().st
        messageFun.setMessage(message);
        messageFun.setTitle(title);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Users carrentUsers = usersRepository.findByEmail(name).get();
        messageFun.setAuthor(carrentUsers);

        messageFunRepository.save(messageFun);

        return "home";
    }
}
