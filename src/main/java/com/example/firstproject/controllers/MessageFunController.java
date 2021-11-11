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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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

//    @PostMapping("/message/cancel")
//    public String cancel()
//    {
//        return "redirect:/";
//    }

    @PostMapping("message/add")
    public String addMesFunPost(
//                                @AuthenticationPrincipal Users users,
                                @RequestParam String title,
                                @RequestParam String message,
                                @RequestParam String actionBtn)
    {
//        if (title == null || message == null)
//        {
//
//        }
        if (actionBtn.equals("Добавить")) {

            MessageFun messageFun = new MessageFun();
//        Users users = usersRepository.findAll().st
            messageFun.setMessage(message);
            messageFun.setTitle(title);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String name = authentication.getName();
            Users carrentUsers = usersRepository.findByEmail(name).get();
            messageFun.setAuthor(carrentUsers);

            LocalDate localDate = LocalDate.now();
            messageFun.setLocalDate(localDate);

            messageFunRepository.save(messageFun);
        }
        return "redirect:/";
    }

    //// Для редактирования сообщения

    @GetMapping("/message/edit/{id}")
    public String editMess(@PathVariable Long id, Model model)
    {
        if (!messageFunRepository.existsById(id))
        {
            return "redirect:/";
        }
//        Optional<MessageFun> messageFun = messageFunRepository.findById(id);
//        ArrayList<MessageFun> messageFuns = new ArrayList<>();
//        messageFun.ifPresent(messageFuns::add);

        MessageFun messageFun = messageFunRepository.getById(id);
        model.addAttribute("messageFromCurrentId",messageFun);
        return "edit_message_fun";
    }
}
