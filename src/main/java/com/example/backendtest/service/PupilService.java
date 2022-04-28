package com.example.backendtest.service;

import com.example.backendtest.domain.Pupil;
import com.example.backendtest.repository.PupilRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class PupilService {
    private PupilRepository repository;

    public Mono<Pupil> create(Pupil pupil){
        log.debug("create pupil: {}", pupil);
        return Mono.just(pupil).flatMap(repository::save);
    }

    public Flux<Pupil> findAll(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    public Flux<Pupil> findByExample(Pupil example) {
        log.debug("findByExample {}", example);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        return repository.findAll(Example.of(example, matcher));
    }

    public Flux<Pupil> findByEducationLevel(String level) {
        log.debug("findByLevel{}", level);
        return repository.findByEducation_Level(level);
    }

    public Mono<Long> count() {
        return repository.count();
    }
}
