package com.example.ouistrez.service.impl;

import com.example.ouistrez.dto.response.ProductClassResponse;
import com.example.ouistrez.dto.request.ProductClassSaveRequest;
import com.example.ouistrez.entity.ProductClass;
import com.example.ouistrez.enums.Deleted;
import com.example.ouistrez.repository.ProductClassRepository;
import com.example.ouistrez.service.ProductClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductClassServiceImpl implements ProductClassService {

    @Autowired
    private ProductClassRepository productClassRepository;

    @Override
    public List<ProductClassResponse> getAllActiveProductClasses() {

        return productClassRepository.findByIsDeleted(Deleted.NO).stream().map(ProductClassServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Optional<ProductClassResponse> getById(String id) {

        return productClassRepository.findById(id).map(ProductClassServiceImpl::convert);

    }

    @Override
    public ProductClassResponse save(ProductClassSaveRequest productClassSaveRequest) {

        ProductClass productClass = new ProductClass();

        productClass.setName(productClassSaveRequest.getName());
        productClass.setDescription(productClassSaveRequest.getDescription());
        productClass.setIsDeleted(Deleted.NO);

        ProductClass save = productClassRepository.save(productClass);

        return convert(save);

    }

    private static ProductClassResponse convert(ProductClass productClass) {

        ProductClassResponse productClassResponse = new ProductClassResponse();

        productClassResponse.setId(productClass.getId());
        productClassResponse.setName(productClass.getName());
        productClassResponse.setDescription(productClass.getDescription());
        productClassResponse.setCreatedBy(productClass.getCreatedBy());
        productClassResponse.setCreatedDateTime(productClass.getCreatedDateTime());
        productClassResponse.setModifiedBy(productClass.getModifiedBy());
        productClassResponse.setModifiedDateTime(productClass.getModifiedDateTime());
        productClassResponse.setIsDeleted(productClass.getIsDeleted());

        return productClassResponse;

    }

}
