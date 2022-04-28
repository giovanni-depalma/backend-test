package com.example.backendtest.service;

import com.example.backendtest.domain.Pupil;
import com.example.backendtest.repository.PupilRepository;
import com.example.backendtest.util.FakePupilBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PupilServiceTest {

    @Mock
    PupilRepository repository;

    @InjectMocks
    private PupilService service;

    @Test
    public void shouldCreate(){
        Pupil expected = FakePupilBuilder.fakePupil();
        Pupil toSave = FakePupilBuilder.fakePupilToSave();
        when(repository.save(toSave)).thenReturn(Mono.just(expected));
        Mono<Pupil> actual = service.create(toSave);
        StepVerifier.create(actual).expectNext(expected).verifyComplete();
    }

    @Test
    public void shouldFindAll(){
        Pupil[] expected = FakePupilBuilder.fakePupils();
        Pageable pageable = Mockito.mock(Pageable.class);
        when(repository.findAllBy(pageable)).thenReturn(Flux.fromArray(expected));
        Flux<Pupil> actual = service.findAll(pageable);
        StepVerifier.create(actual).expectNext(expected).verifyComplete();
    }
}
