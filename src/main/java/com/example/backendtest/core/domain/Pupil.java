package com.example.backendtest.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public record Pupil(
        @Id
        String id,
        String name,
        Address address,
        List<Education> education) {
}
