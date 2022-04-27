package com.example.backendtest.presenter.api;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Schema(name = "PupilRequest")
public record PupilRequestResource(
        @Schema(example = "Manuel") @Pattern(regexp = "^[A-Za-z\\s]{1,100}$")
        String name,
        @Valid
        AddressResource address,
        @Schema(example = "{" +
                "\"master\": {\"university\":\"Politecnico Milano\", \"year\": 2004}," +
                "\"phd\": {\"university\":\"USCD\", \"year\": 2009}" +
                "}")
        @Valid
        Map<String,EducationItemResource> education) {
}
