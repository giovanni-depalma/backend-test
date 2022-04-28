package com.example.backendtest.util;

import com.example.backendtest.domain.Address;
import com.example.backendtest.domain.Education;
import com.example.backendtest.domain.Pupil;
import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FakePupilBuilder {

    public static Pupil fakePupilToSave() {
        return fakePupil(null);
    }

    public static Pupil fakePupil() {
        String id = UUID.randomUUID().toString();
        return fakePupil(id);
    }

    public static Pupil[] fakePupils() {
        return IntStream.range(0,10).mapToObj(i -> fakePupil()).toArray(i -> new Pupil[i]);
    }

    public static Pupil fakePupil(String id) {
        Faker faker = new Faker(new Random());
        String name = faker.name().firstName();
        return new Pupil(id, name, fakeAddress(), fakeEducation());
    }

    public static Address fakeAddress() {
        Faker faker = new Faker(new Random());
        return new Address(faker.address().streetAddress(), faker.number().digits(5), faker.address().country());
    }

    public static List<Education> fakeEducation() {
        Faker faker = new Faker(new Random());
        return IntStream.range(0, 10).mapToObj(i -> new Education(faker.bothify("??????"), faker.educator().campus(), faker.number().digits(4))).toList();
    }


}
