package com.jean.superlanch.product;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody @Valid CreateProductRequest request){
        return service.create(request);
    }

    @GetMapping("/{productId}")
    public ProductFullResponse findById(@PathVariable Long productId){
        return service.getProductDetails(productId);
    }

    @GetMapping
    public Page<ProductResponse> listAll(ProductFilter filter, Pageable pageable){
        return service.listAll(filter, pageable);
    }

    @PutMapping("/{productId}")
    public ProductResponse update(@PathVariable Long productId, @RequestBody @Valid UpdateProductRequest request){
        return service.update(productId, request);
    }
}
