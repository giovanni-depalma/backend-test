package com.example.backendtest.presenter.api;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(name = "EducationItem")
public record EducationItemResource(
        @Schema(example = "Politecnico Milano") @Size(min = 1, max = 100)  @NotBlank
        String university,
        @Schema(example = "2004")  @Pattern(regexp = "^\\d{4}$")  @NotBlank
        String year) {
}
