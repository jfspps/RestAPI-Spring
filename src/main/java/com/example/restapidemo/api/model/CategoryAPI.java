package com.example.restapidemo.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// this comprises the DTO (data transfer object) and is mapped to Category (/domain) bean with MapStruct
// MapStruct is a mapping plugin that relates DTOs with client beans, and in most cases maps the properties by name,
// if possible; see CategoryMapper (don't forget to add @Data here to get Lombok to build getters and setters in Mapper)
@Setter
@Getter
@Data
public class CategoryAPI {

    private Long id;
    private String name;
    private String category_url;
}
