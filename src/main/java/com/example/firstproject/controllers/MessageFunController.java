package com.example.firstproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessageFunController {

    @GetMapping("/message/add")
    public String messageFun()
    {
        return "add_message_fun";
    }
}
