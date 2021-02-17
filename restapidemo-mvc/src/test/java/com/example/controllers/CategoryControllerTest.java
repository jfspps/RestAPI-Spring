package com.example.controllers;

import com.example.api.model.CategoryAPI;
import com.example.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    public static final String NAME = "Jim";

    @Mock
    CategoryService categoryService;

    // this test checks if the GET request URL for the API works;
    // this applies to getAllCategories() and getCategoryByName()
    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RESTResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListCategories() throws Exception {
        CategoryAPI category1 = new CategoryAPI();
        category1.setId(1L);
        category1.setName(NAME);

        CategoryAPI category2 = new CategoryAPI();
        category2.setId(2L);
        category2.setName("Bob");

        List<CategoryAPI> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        // set the header of the request to accept JSON (as given by accept())
        mockMvc.perform(get("/api/categories/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2))); // $ is root
    }

    @Test
    public void testGetByNameCategories() throws Exception {
        CategoryAPI category1 = new CategoryAPI();
        category1.setId(1L);
        category1.setName(NAME);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        // set the header of the request to accept JSON (as given by accept())
        mockMvc.perform(get("/api/categories/Jim")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }
}