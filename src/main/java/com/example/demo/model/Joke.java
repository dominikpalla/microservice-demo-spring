package com.example.demo.model;

import jakarta.persistence.*;
@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int humourRatio;

    public Joke() {
    }

    public Joke(String text, int humourRatio) {
        this.text = text;
        this.humourRatio = humourRatio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getHumourRatio() {
        return humourRatio;
    }

    public void setHumourRatio(int humourRatio) {
        this.humourRatio = humourRatio;
    }
}