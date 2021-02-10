package com.example.restapidemo.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListAPI {

    List<CategoryAPI> categories;
}
