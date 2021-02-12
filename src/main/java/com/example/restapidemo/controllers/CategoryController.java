package com.example.restapidemo.controllers;

import com.example.restapidemo.api.model.CategoryAPI;
import com.example.restapidemo.api.model.CategoryListAPI;
import com.example.restapidemo.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// @RestController does away with returning ResponseEntity<> and is a more modern and cleaner
// implementation cf. @Controller for REST APIs (refactored below)
@Tag(name = "category-controller", description = "This is the Category controller")
@RestController
@RequestMapping(CategoryController.ROOT_URL)
public class CategoryController {

    private final CategoryService categoryService;

    // reminder, this is also accessible to other controller and tests
    public static final String ROOT_URL = "/api/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns (currently) a JSON of all fruit categories with their properties
     * @return JSON formatted list
     */
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public CategoryListAPI getAllCategories(){
        return new CategoryListAPI(categoryService.getAllCategories());
    }

    /**
     * Returns (currently) a JSON of the fruit category, of the name given, with their properties
     * @param name name of the category (Dried, Exotix etc.)
     * @return JSON formatted list
     */
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryAPI getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }
}
