package com.redistest.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redistest.demo.model.NumbersGenerationRequest;
import com.redistest.demo.service.NumberGenerationService;

@RestController
@RequestMapping("/nums")
public class NumbersGeneratorController {
    @Autowired
    NumberGenerationService numberGenerationService;

    /**
     * Adds the product into the Cart. If active cart doesn't exist it creates it and then adds the product.
     *
     * @return Whole Cart object.
     */
    @PostMapping("/generate")
    public void generator(@RequestBody NumbersGenerationRequest request) {
        numberGenerationService.saveListOfNumbers(request.getMaxNumber());
    }

    @GetMapping("/get-numbers-asc")
    public List<Object> getNumbersAsc() {
        return numberGenerationService.getNumbersAsc();
    }

    @GetMapping("/get-numbers-desc")
    public List<Object> getNumbersDesc() {
        return numberGenerationService.getNumbersDesc();
    }

    @PostMapping("/cleanup")
    public void deleteAll() {
        numberGenerationService.deleteNumbers();
    }

}
