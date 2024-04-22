package com.example.demo.service;

import com.example.demo.model.Joke;
import org.springframework.stereotype.Service;

@Service
public interface JokeService {
    Joke getJoke(Long id);
    Joke createJoke(Joke joke);
    Joke updateJoke(Joke joke);
    Joke deleteJoke(Long id);
}
