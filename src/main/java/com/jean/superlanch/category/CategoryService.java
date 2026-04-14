package com.jean.superlanch.category;

import com.jean.superlanch.category.dto.CategoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findByIdOrThrow(Long categoryId){
        return repository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
    }


    public CategoryResponse findById(Long categoryId){
        var category = findByIdOrThrow(categoryId);
        return CategoryResponse.from(category);
    }


    public List<CategoryResponse> findAll(){
        return repository.findAll()
                .stream()
                .map(CategoryResponse::from)
                .toList();
    }
}
