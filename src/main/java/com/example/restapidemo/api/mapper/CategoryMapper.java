package com.example.restapidemo.api.mapper;

import com.example.restapidemo.api.model.CategoryAPI;
import com.example.restapidemo.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryAPI categoryToCategoryAPI(Category category);
}
