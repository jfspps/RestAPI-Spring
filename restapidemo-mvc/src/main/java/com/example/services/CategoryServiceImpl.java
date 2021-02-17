package com.example.services;

import com.example.api.mapper.CategoryMapper;
import com.example.api.model.CategoryAPI;
import com.example.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryAPI> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryAPI)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryAPI getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryAPI(categoryRepository.findByName(name));
    }
}
