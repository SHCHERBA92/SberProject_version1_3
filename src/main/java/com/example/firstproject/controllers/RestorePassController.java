package com.example.firstproject.controllers;

import com.example.firstproject.mail_service.MyMailService;
import com.example.firstproject.model.Users;
import com.example.firstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class RestorePassController {

    @Autowired
    UsersRepository usersRepository;

//    @Autowired
//    JavaMailSender javaMailSender;

    @Autowired
    MyMailService myMailService;

    @GetMapping("/restorePass")
    public String resorePasswordView()
    {
        return "/restore_password";
    }

    @PostMapping("/restorePass")
    public String restorePassPost(@RequestParam String email)
    {
        Optional<Users> usersOptional = usersRepository.findByEmail(email);
        if (!usersOptional.isPresent())
        {
            System.out.println("Такого нет");
            return "redirect:/restorePass";
        }

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(email);
//        simpleMailMessage.setSubject("Восстановление пароля");
//        simpleMailMessage.setText("Ваш пароль: " + usersOptional.get().getPassword());
//
//        javaMailSender.send(simpleMailMessage);

        myMailService.sendPassToMail(email,"Восстановление пароля", "Ваш пароль: " + usersOptional.get().getPassword());

        return "redirect:/";
    }
}
