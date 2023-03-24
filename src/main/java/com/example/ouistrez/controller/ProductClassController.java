package com.example.ouistrez.controller;

import com.example.ouistrez.dto.response.ProductClassResponse;
import com.example.ouistrez.dto.request.ProductClassSaveRequest;
import com.example.ouistrez.service.ProductClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productClass")
@CrossOrigin(origins = "*")
public class ProductClassController {

    @Autowired
    private ProductClassService productClassService;

    @GetMapping("/getAllActiveProductClasses")
    public List<ProductClassResponse> getAllActiveProductClasses() {

        return productClassService.getAllActiveProductClasses();

    }

    @GetMapping("/getById/{id}")
    public Optional<ProductClassResponse> getById(@PathVariable("id") String id) {

        return productClassService.getById(id);

    }

    @PostMapping("/save")
    public ProductClassResponse save(@RequestBody ProductClassSaveRequest productClassSaveRequest) {

        return productClassService.save(productClassSaveRequest);

    }


}
