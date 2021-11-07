/**
 * Это больше как рудиментальный контроллер для отладки
*/
package com.example.firstproject.controllers;

import com.example.firstproject.model.People;
import com.example.firstproject.model.Users;
import com.example.firstproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    List<People> peopleList = new ArrayList<>(Arrays.asList(
            new People(1L, "Ivan", "Ivanov"),
            new People(2L, "Kirill", "Kirillov"),
            new People(3L, "Sergey", "Sergeev")
    ));

//    List<Users> users = new ArrayList<>();

    @Autowired
    UsersRepository usersRepository;

    @GetMapping()
    public List<People> allPeople()
    {
        return peopleList;
    }

    @GetMapping("/{id}")
    People getPeopleById(@PathVariable Long id)
    {
        return peopleList.stream().filter(people -> people.getId().equals(id)).findFirst().get();
    }

    @GetMapping("/users")
    List<Users> getUsers(Model model)
    {
//        = usersRepository.findAll();
//        users.add()

//        model.addAttribute("AllUsers",usersRepository.findAll());
        return usersRepository.findAll();
    }
}
