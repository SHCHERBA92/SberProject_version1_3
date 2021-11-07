package com.example.firstproject.controllers;

import com.example.firstproject.model.Role;
import com.example.firstproject.model.Status;
import com.example.firstproject.model.Users;
import com.example.firstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String  email,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String password, Model model)
    {
        Users users = new Users();
        users.setEmail(email);
        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setRole(Role.USER);
        users.setPassword(password);
        users.setStatus(Status.ACTIVE);
        usersRepository.save(users);

        return "succses";
    }
}
