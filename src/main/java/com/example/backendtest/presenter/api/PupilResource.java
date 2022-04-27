package com.example.backendtest.presenter.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(name = "Pupil")
public record PupilResource(
        String id,
        String name,
        AddressResource address,
        Map<String,EducationItemResource> education) {
}
