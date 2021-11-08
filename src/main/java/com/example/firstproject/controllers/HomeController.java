package com.example.firstproject.controllers;

import com.example.firstproject.model.MessageFun;
import com.example.firstproject.repository.MessageFunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    MessageFunRepository messageFunRepository;

    @GetMapping("/")
    public String home(Model model)
    {
        Iterable<MessageFun> messageFuns = messageFunRepository.findAll();

        return "home";
    }
}
