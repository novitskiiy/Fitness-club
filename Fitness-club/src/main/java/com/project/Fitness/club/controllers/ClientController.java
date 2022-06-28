package com.project.Fitness.club.controllers;

import com.project.Fitness.club.models.Client;
import com.project.Fitness.club.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client")
    public String client(Model model){
        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "client";
    }

    @GetMapping("/client/add")
    public String clientAdd(Model model){
        return "client-add";
    }

    @PostMapping("/client/add")
    public String clientPostAdd(@RequestParam String name, @RequestParam String surname, @RequestParam Long id_trainer, @RequestParam Long id_subscription, Model model){
        Client client = new Client(name, surname, id_trainer, id_subscription, 13L);
        clientRepository.save(client);
        return "redirect:/client";
    }
    @GetMapping("/user-to-client-add")
    public String userToClientAdd(Model model){
        return "user-to-client-add";
    }

    @PostMapping("/user-to-client-add")
    public String userToClientPostAdd(@RequestParam String name, @RequestParam String surname, @RequestParam Long id_trainer, @RequestParam Long id_subscription, Model model){
        Client client = new Client(name, surname, id_trainer, id_subscription, 13L);
        clientRepository.save(client);
        return "redirect:/user-to-client";
    }
    @GetMapping("/client/{id}")
    public String clientPodrobnee(@PathVariable(value = "id") long id, Model model){
        if(!clientRepository.existsById(id)){
            return "redirect:/client";
        }

        Optional<Client> client = clientRepository.findById(id);
        ArrayList<Client> result = new ArrayList<>();
        client.ifPresent(result::add);
        model.addAttribute("client", result);
        return "client-podrobnee";
    }
    @GetMapping("/client/{id}/edit")
    public String clientEdit(@PathVariable(value = "id") long id, Model model){
        if(!clientRepository.existsById(id)){
            return "redirect:/client";
        }

        Optional<Client> client = clientRepository.findById(id);
        ArrayList<Client> result = new ArrayList<>();
        client.ifPresent(result::add);
        model.addAttribute("client", result);
        return "client-edit";
    }
    @PostMapping("/client/{id}/edit")
    public String clientPostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String surname, @RequestParam Long id_trainer, @RequestParam Long id_subscription, Model model){
        Client client = clientRepository.findById(id).orElseThrow();
        client.setName(name);
        client.setSurname(surname);
        client.setId_trainer(id_trainer);
        client.setId_subscription(id_subscription);
        clientRepository.save(client);
        return "redirect:/client";
    }
    @PostMapping("/client/{id}/delete")
    public String clientPostDelete(@PathVariable(value = "id") long id, Model model){
        Client client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        return "redirect:/client";
    }
}
