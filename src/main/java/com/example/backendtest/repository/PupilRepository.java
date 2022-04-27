package com.example.backendtest.repository;

import com.example.backendtest.core.domain.Pupil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PupilRepository extends ReactiveCrudRepository<Pupil, String>, ReactiveQueryByExampleExecutor<Pupil> {
    Flux<Pupil> findAllBy(Pageable pageable);

    Flux<Pupil> findByEducation_Level(String level);
}
