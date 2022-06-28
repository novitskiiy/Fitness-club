package com.project.Fitness.club.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private Long id_trainer;
    private Long id_subscription;
    private Long id_user;
    public Client() {
    }

    public Client(String name, String surname,  Long id_trainer, Long id_subscription, Long id_user) {
        this.name = name;
        this.surname = surname;
        this.id_trainer = id_trainer;
        this.id_subscription = id_subscription;
        this.id_user=id_user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId_trainer() {
        return id_trainer;
    }

    public void setId_trainer(Long id_trainer) {
        this.id_trainer = id_trainer;
    }

    public Long getId_subscription() {
        return id_subscription;
    }

    public void setId_subscription(Long id_subscription) {
        this.id_subscription = id_subscription;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
