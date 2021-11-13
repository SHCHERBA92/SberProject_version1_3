package com.example.firstproject.controllers;

import com.example.firstproject.model.MessageFun;
import com.example.firstproject.repository.MessageFunRepository;
import com.example.firstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/message")
public class FindMessController {

    boolean flagNotFound = false;
    List<MessageFun> currectListMessFuns;
    String findKey = null;

    @Autowired
    MessageFunRepository messageFunRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/find")
    public String findView(Model model)
    {
        model.addAttribute("flagNotFound", flagNotFound);
        flagNotFound = false;
        model.addAttribute("currectListMessFuns", currectListMessFuns);
        model.addAttribute("findKey", findKey);
        return "findByKeys";
    }

    @PostMapping("/find")
    public String find(@RequestParam String findKey, Model model)
    {
        this.findKey = findKey;

        List<MessageFun> messageFuns = messageFunRepository.findAll();
        currectListMessFuns = messageFuns.stream().filter(messageFun ->
            messageFun.getMessage().contains(findKey) |
            messageFun.getTitle().contains(findKey) |
            messageFun.getAuthor().getLastName().contains(findKey)
        ).collect(Collectors.toList());

        if (currectListMessFuns.isEmpty())
        {
            flagNotFound = true;
        }
        return "redirect:/message/find";
    }
}
