package com.example.demo.service;

import com.example.demo.model.Joke;
import com.example.demo.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class JokeServiceImpl implements JokeService {

    private JokeRepository jokeRepository;

    @Autowired
    public JokeServiceImpl(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public Joke getJoke(Long id) {
        return jokeRepository.findById(id).orElse(null);
    }

    @Override
    public Joke createJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    @Override
    public Joke updateJoke(Joke joke) {
        if(jokeRepository.findById(joke.getId()).isPresent()) {
            return jokeRepository.save(joke);
        }else{
            return null;
        }
    }

    @Override
    public Joke deleteJoke(Long id) {
        Optional<Joke> joke = jokeRepository.findById(id);
        if(joke.isPresent()) {
            jokeRepository.delete(joke.get());
            return joke.get();
        }else{
            return null;
        }
    }
}
