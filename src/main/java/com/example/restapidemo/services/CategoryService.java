package com.example.restapidemo.services;

import com.example.restapidemo.api.model.CategoryAPI;

import java.util.List;

public interface CategoryService {

    List<CategoryAPI> getAllCategories();

    CategoryAPI getCategoryByName(String name);
}
