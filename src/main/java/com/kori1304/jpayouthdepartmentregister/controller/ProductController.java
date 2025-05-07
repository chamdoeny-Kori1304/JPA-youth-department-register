package com.kori1304.jpayouthdepartmentregister.controller;

import com.kori1304.jpayouthdepartmentregister.entity.Product;
import com.kori1304.jpayouthdepartmentregister.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductJpaRepository productRepository;
    @PostMapping("/product/save")
    public ResponseEntity<?> personSave(@RequestBody Product product) {
       Product res =  productRepository.save(product);
       try {
           return ResponseEntity.ok().body(res);
       }catch (Exception e) {
           return ResponseEntity.internalServerError().body("Failed to get all member data: " + e.getMessage());

       }

    }

}
