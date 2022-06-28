package com.project.Fitness.club.controllers;


import com.project.Fitness.club.models.Admin;
import com.project.Fitness.club.models.User;
import com.project.Fitness.club.repo.AdminRepository;
import com.project.Fitness.club.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/")
    public String entry(Model model) {
        model.addAttribute("title", "Entry");
        return "entry";
    }
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Fitness Club");
        return "home";
    }
    @GetMapping("/user-to-client")
    public String userMain(Model model) {
        model.addAttribute("title", "Главная страница");
        return "user-to-client";
    }
    @GetMapping("/machine")
    public String machine(Model model) {
        model.addAttribute("title", "Тренажеры");
        return "machine";
    }
    @PostMapping("/userEntry")
    public String userEntry(@RequestParam String name, @RequestParam String surname, @RequestParam String type, Model model) {

        Iterable<User> objects = userRepository.findAll();

        for(User usr : objects){
            if(usr.getName().equals(name) && usr.getSurname().equals(surname) && usr.getType().equals(type))
                if(type.equals("Админ"))
                    return "redirect:/home";
                else if (type.equals("Клиент")) {
                    return "redirect:/user-to-client";
                }
        }
        return "entry";
    }

    @PostMapping("/userAdd")
    public String userAdd(@RequestParam String name, @RequestParam String surname, @RequestParam String type, Model model){
        User user = new User(name, surname, type);
        userRepository.save(user);
        if (type.equals("Админ")) {
            Admin admin = new Admin(user.getId());
            adminRepository.save(admin);
        }
        return "redirect:/home";
    }

}