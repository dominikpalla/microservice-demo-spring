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
    public Joke updateUser(@RequestBody Joke joke) {
        Joke originalJoke = jokeService.getJoke(joke.getId());
        if (originalJoke == null) {
            return null;
        }

        return jokeService.updateJoke(joke);
    }

    @DeleteMapping("/{id}")
    public Joke deleteUser(@PathVariable Long id) {
        Joke joke = jokeService.getJoke(id);
        if (joke == null) {
            return null;
        }

        return jokeService.deleteJoke(id);
    }

}

