[![CircleCI](https://circleci.com/gh/jfspps/RestAPI-Spring.svg?style=svg)](https://circleci.com/gh/jfspps/RestAPI-Spring)

# RestAPI-Spring

A non-reactive demo (an endpoint) of a Spring Boot app with a RESTful API (without a web frontend). A client requests data with the URL "http://localhost:8080/api/categories/Dried", for example.

This project also introduces [MapStruct](https://mapstruct.org/) which maps client beans with the API at runtime (particularly useful for large projects with numerous properties)

One part which implements a REST API response is the return of a `ResponseEntity<Class>`, where Class is the DTO (data transfer object, which in this project suffixed with "API") is returned as a list of its properties. See [CategoryController](restapidemo-mvc/src/main/java/com/example/restapidemo/controllers/CategoryController.java).

The data is mapped from entities on the endpoint's database (H2 in this case) via the Spring JPA repository. The property names of the DTO and POJO need not be the same. Such an approach also enables one to release part of the database entity (not all properties) as part of a GET request, for example. A POJO may list sensitive information for which the client is not permitted to view.

With RESTful API development, it is generally good practice to build packages by version number so that the client can decide which version to utilise, say, "http://localhost:8080/api/v1/categories/Dried" or "http://localhost:8080/api/v2/categories/Dried", for example. In this project, version labelled packages have been omitted, since there is only ever going to be one version of the interface.

The API's documentation (and can be improved) with OpenAPI (formerly, Swagger). Running Spring Boot and then opening [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) provides the user with a web interface, running all the Controller's GET, POST, PUT, PATCH and DELETE requests, where available, with customised instructions and commentary.

## XML instead of JSON

By default, Spring returns JSON on request. To return XML, the following dependencies would need to be added:

`
<dependency>
<groupId>com.fasterxml.jackson.dataformat</groupId>
<artifactId>jackson-dataformat-xml</artifactId>
</dependency>
`

`
<dependency>
<groupId>javax.xml.bind</groupId>
<artifactId>jaxb-api</artifactId>
<version>2.3.1</version>
</dependency>
`

Indeed, adding these dependencies allows the client to receive JSON or XML based on their `Accept` key value of the header (the value should be set to `application/json` or `appliction/xml` as desired). Be aware
that unit tests will be looking for JSON or XML, usually not both (unless they have already been written).

## XSD to Java Classes with JAXB

One can use XSD (XML Schema) files to build Java classes using JAXB (Java Architecture for XML Binding). This project was refactored as two modules, followed by Maven clean and package.

When importing the JAXB class, run Maven package first to ensure the /target directories are built before changing the imports relevant to the generated JAXB class (in this case, CustomerListAPI).