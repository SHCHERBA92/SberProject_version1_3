package com.example.firstproject.controllers;

import com.example.firstproject.model.MessageFun;
import com.example.firstproject.repository.MessageFunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteMessageFunController {

    @Autowired
    MessageFunRepository messageFunRepository;

    @GetMapping("/message/delete/{id}")
    public String viewDelete(@PathVariable Long id, Model model)
    {
        MessageFun messageFun = messageFunRepository.getById(id);
//        if (messageFun==null)
//        {
//            return ""
//        }
        model.addAttribute("messageForDelete", messageFun);
        return "delete_message_fun";
    }

    @PostMapping("/message/delete/{id}")
    public String deleteMess(@PathVariable Long id,
                             @RequestParam String actionBtn)
    {
        if(actionBtn.equals("Удалить"))
        {
            MessageFun messageFun = messageFunRepository.getById(id);
            messageFunRepository.delete(messageFun);
        }
        return "redirect:/";
    }
}
