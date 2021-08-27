package com.redistest.demo.model;

import lombok.Data;

@Data
public class NumbersGenerationRequest {
    private NumberGenerationType type;
    private int maxNumber;
}
