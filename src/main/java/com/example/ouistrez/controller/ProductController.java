package com.example.ouistrez.controller;

import com.example.ouistrez.dto.request.ProductSaveRequest;
import com.example.ouistrez.dto.response.ProductResponse;
import com.example.ouistrez.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllActiveProducts")
    public List<ProductResponse> getAllActiveProducts() {

        return productService.getAllActiveProducts();

    }

//    @GetMapping("/getById/{id}")
//    public Optional<ProductClassResponse> getById(@PathVariable("id") String id) {
//
//        return productService.getById(id);
//
//    }

    @PostMapping("/save")
    public ProductResponse save(@RequestBody ProductSaveRequest productSaveRequest) {

        return productService.save(productSaveRequest);

    }

}
