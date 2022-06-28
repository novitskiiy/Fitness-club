package com.project.Fitness.club.controllers;

import com.project.Fitness.club.models.Subscription;
import com.project.Fitness.club.models.Trainer;
import com.project.Fitness.club.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscriptionController {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping("/subscription")
    public String subscription(Model model){
        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();
        model.addAttribute("subscriptions", subscriptions);
        return "subscription";
    }
    @GetMapping("/subscription1")
    public String subscription1(Model model){
        Iterable<Subscription> subscriptions = subscriptionRepository.findAll();
        model.addAttribute("subscriptions", subscriptions);
        return "subscription1";
    }
    @PostMapping("/subscription/{id}/delete")
    public String clientPostDelete(@PathVariable(value = "id") long id, Model model){
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow();
        subscriptionRepository.delete(subscription);
        return "redirect:/subscription";
    }
    @GetMapping("/subscription/add")
    public String subscriptionAdd(Model model){
        return "subscription-add";
    }

    @PostMapping("/subscription/add")
    public String subscriptionPostAdd(@RequestParam String name, @RequestParam String description, @RequestParam int price, Model model){
        Subscription subscription = new Subscription(name, description, price);
        subscriptionRepository.save(subscription);
        return "redirect:/subscription";
    }
}