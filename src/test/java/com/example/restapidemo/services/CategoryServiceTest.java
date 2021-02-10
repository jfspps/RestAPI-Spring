package com.example.restapidemo.services;

import com.example.restapidemo.api.mapper.CategoryMapper;
import com.example.restapidemo.api.model.CategoryAPI;
import com.example.restapidemo.domain.Category;
import com.example.restapidemo.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";

    // recall that CategoryService defines the DB operations, through CategoryRepository, so we call from the service
    // and check if this calls the repository
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() throws Exception {
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception {

        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryAPI> categoryAPIS = categoryService.getAllCategories();

        //then
        assertEquals(3, categoryAPIS.size());

    }

    @Test
    public void getCategoryByName() throws Exception {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryAPI categoryAPI = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryAPI.getId());
        assertEquals(NAME, categoryAPI.getName());

    }
}