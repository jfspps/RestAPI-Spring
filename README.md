[![CircleCI](https://circleci.com/gh/jfspps/RestAPI-Spring.svg?style=svg)](https://circleci.com/gh/jfspps/RestAPI-Spring)

# RestAPI-Spring

A non-reactive demo (an endpoint) of a Spring Boot app with a RESTful API (without a web frontend). A client requests data with the URL "http://localhost:8080/api/categories/Dried", for example.

This project also introduces [MapStruct](https://mapstruct.org/) which maps client beans with the API at runtime (particularly useful for large projects with numerous properties)

The key aspect which implements a REST API is the return of a ResponseEntity<Class>, where Class is the DTO (data transfer object, which in this project suffixed with "API") is returned as a list of its properties. See [CategoryController](./src/main/java/com/example/restapidemo/controllers/CategoryController.java).

The data is mapped from entities on the endpoint's database (H2 in this case) via the Spring JPA repository. The property names of the DTO and POJO need not be the same. Such an approach also enables one to release part of the database entity (not all properties) as part of a GET request, for example. A POJO may list sensitive information for which the client is not permitted to view.

With RESTful API development, it is generally good practice to build packages by version number so that the client can decide which version to utilise, say, "http://localhost:8080/api/v1/categories/Dried" or "http://localhost:8080/api/v2/categories/Dried", for example. In this project, version labelled packages have been omitted, since there is only ever going to be one version of the interface.