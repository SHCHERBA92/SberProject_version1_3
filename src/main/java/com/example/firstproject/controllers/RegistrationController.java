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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    boolean flagUserExist = false;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/registration")
    public String registration(Model model)
    {
        model.addAttribute("flagUserExist", flagUserExist);
        flagUserExist = false;
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String email,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String password, Model model)
    {
        Optional<Users> userFromDb = usersRepository.findByEmail(email);
        if (flagUserExist = userFromDb.isPresent())
        {
            return "redirect:/auth/registration";
        }

            Users users = new Users();
            users.setEmail(email);
            users.setFirstName(firstName);
            users.setLastName(lastName);
            users.setRole(Role.USER);
            users.setPassword(password);
            users.setStatus(Status.ACTIVE);

            usersRepository.save(users);
            model.addAttribute("currentUser", users);

            return "succses";

    }
}
