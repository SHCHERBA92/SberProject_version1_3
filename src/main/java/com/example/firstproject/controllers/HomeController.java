package com.example.firstproject.controllers;

import com.example.firstproject.model.MessageFun;
import com.example.firstproject.repository.MessageFunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    MessageFunRepository messageFunRepository;

    @GetMapping("/")
    public String home(Model model)
    {
        List<MessageFun> messageFuns = messageFunRepository.findAll();
        Collections.sort(messageFuns, (o1, o2) -> o2.getLocalDate().compareTo(o1.getLocalDate()));
//        List<MessageFun> messageFuns = messageFunRepository.findAll().stream().collect(Collectors.toList());
//        Collections.sort(messageFuns,Collections.reverseOrder());

        model.addAttribute("messageFuns", messageFuns);

        return "home";
    }
}
