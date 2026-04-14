package com.jean.superlanch.category;

import com.jean.superlanch.category.dto.CategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryResponse> findAll(){
        return service.findAll();
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse findById(@PathVariable Long categoryId){
        return service.findById(categoryId);
    }
}
