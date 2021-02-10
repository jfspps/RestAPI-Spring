package com.example.restapidemo.api.mapper;

import com.example.restapidemo.api.model.CategoryAPI;
import com.example.restapidemo.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryAPI categoryToCategoryAPI(Category category);
}
