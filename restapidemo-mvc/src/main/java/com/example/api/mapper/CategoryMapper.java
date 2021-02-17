package com.example.api.mapper;

import com.example.api.model.CategoryAPI;
import com.example.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryAPI categoryToCategoryAPI(Category category);
}
