package com.example.restapidemo.controllers;

import com.example.restapidemo.api.model.CategoryAPI;
import com.example.restapidemo.api.model.CategoryListAPI;
import com.example.restapidemo.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns (currently) a JSON of all fruit categories with their properties
     * @return JSON formatted list
     */
    @GetMapping("/")
    public ResponseEntity<CategoryListAPI> getAllCategories(){
        return new ResponseEntity<>(new CategoryListAPI(categoryService.getAllCategories()), HttpStatus.OK);
    }

    /**
     * Returns (currently) a JSON of the fruit category, of the name given, with their properties
     * @param name name of the category (Dried, Exotix etc.)
     * @return JSON formatted list
     */
    @GetMapping("/{name}")
    public ResponseEntity<CategoryAPI> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
    }
}
