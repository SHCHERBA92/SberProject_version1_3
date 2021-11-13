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

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class EditMessageFunController {

    private boolean strBool = false;

    @Autowired
    private MessageFunRepository messageFunRepository;


    @GetMapping("/message/edit/{id}")
    public String getEditMess(@PathVariable Long id, Model model)
    {
        if (!messageFunRepository.existsById(id))
        {
            return "redirect:/";
        }


        MessageFun messageFun = messageFunRepository.getById(id);
        model.addAttribute("messageFromCurrentId",messageFun);
        this.strBool = false;
        return "edit_message_fun";
    }

    @PostMapping("/message/edit/{id}")
    public String editMess(@RequestParam String title,
                           @RequestParam String message,
                           @RequestParam String actionBtn,
                           @PathVariable Long id)
    {
        if ((title.equals("") || message.equals("") ) & (!actionBtn.equals("Отмена")))
        {
            this.strBool = true;

            return "redirect:/message/edit/{id}";
        }

        if (actionBtn.equals("Обновить"))
        {
            MessageFun messageFun = messageFunRepository.findById(id).orElseThrow();
            messageFun.setTitle(title);
            messageFun.setMessage(message);
//            messageFun.setLocalDate(LocalDate.now());
            messageFunRepository.save(messageFun);
        }
        return "redirect:/";
    }
}
