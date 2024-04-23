package com.example.demo.controller;

import com.example.demo.model.Joke;
import com.example.demo.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {

    private JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService){
        this.jokeService = jokeService;
    }

    @PostMapping("/new")
    public Joke newJoke(@RequestBody Joke joke) {
        return jokeService.createJoke(joke);
    }

    @GetMapping("/{id}")
    public Joke getJoke(@PathVariable Long id) {
        return jokeService.getJoke(id);
    }

    @PutMapping("/update")
    public Joke updateJoke(@RequestBody Joke joke) {
        return jokeService.updateJoke(joke);
    }

    @DeleteMapping("/{id}")
    public Joke deleteJoke(@PathVariable Long id) {
        return jokeService.deleteJoke(id);
    }

}

