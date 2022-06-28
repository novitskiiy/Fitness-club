package com.project.Fitness.club.controllers;

import com.project.Fitness.club.models.Client;
import com.project.Fitness.club.models.Trainer;
import com.project.Fitness.club.repo.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrainerController {
    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping("/trainer")
    public String trainer(Model model){
        Iterable<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);
        return "trainer";
    }
    @GetMapping("/trainer1")
    public String trainer1(Model model){
        Iterable<Trainer> trainers = trainerRepository.findAll();
        model.addAttribute("trainers", trainers);
        return "trainer1";
    }
    @PostMapping("/trainer/{id}/delete")
    public String trainerPostDelete(@PathVariable(value = "id") long id, Model model){
        Trainer trainer = trainerRepository.findById(id).orElseThrow();
        trainerRepository.delete(trainer);
        return "redirect:/trainer";
    }
    @GetMapping("/trainer/add")
    public String trainerAdd(Model model){
        return "trainer-add";
    }

    @PostMapping("/trainer/add")
    public String trainerPostAdd(@RequestParam String name, @RequestParam String surname, Model model){
        Trainer trainer = new Trainer(name, surname);
        trainerRepository.save(trainer);
        return "redirect:/trainer";
    }
}
