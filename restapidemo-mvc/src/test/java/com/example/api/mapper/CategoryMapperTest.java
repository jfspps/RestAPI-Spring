package com.example.api.mapper;

import com.example.api.model.CategoryAPI;
import com.example.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void categoryToCategoryAPI() {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryAPI categoryAPI = categoryMapper.categoryToCategoryAPI(category);

        //then
        assertEquals(Long.valueOf(ID), categoryAPI.getId());
        assertEquals(NAME, category.getName());
    }
}