package com.jean.superlanch.product;

import com.jean.superlanch.category.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final CategoryService categoryService;
    private final ProductRepository repository;

    public ProductService(CategoryService categoryService, ProductRepository repository) {
        this.categoryService = categoryService;
        this.repository = repository;
    }

    public ProductResponse create(CreateProductRequest request){
        var category = categoryService.findByIdOrThrow(request.categoryId());
        var product = Product.create(request.name(), request.price(), category, request.addonAllowed());
        product = repository.save(product);
        return ProductResponse.toProductResponse(product);
    }

    @Transactional(readOnly = true)
    public Product findByIdOrThrow(Long id){
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public ProductFullResponse getProductDetails(Long id){
        var product = repository.findByIdWithCategory(id).orElseThrow(ProductNotFoundException::new);
        return ProductFullResponse.toProductFullResponse(product);
    }
}
