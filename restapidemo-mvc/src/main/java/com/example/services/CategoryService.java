package com.example.services;

import com.example.api.model.CategoryAPI;

import java.util.List;

public interface CategoryService {

    List<CategoryAPI> getAllCategories();

    CategoryAPI getCategoryByName(String name);
}
