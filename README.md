# Hexagonal

![Drag Racing](local-resources/img/hexagon_schema.png)

## Project
* **Description:** Hexagonal DDD archetype project oriented.
* **Version:** 0.0.1-SNAPSHOT

## How to run it?
IDE Main start
```
infrastructure/src/main/java/es/orquidea/hexagonal/bootstrap/SpringBootService.java
```

Command line plugin starter
```
mvn spring-boot:run
```


## REST services

```
Get - http://localhost:8080/users/user/{userId}
Get - http://localhost:8080/users

Post - http://localhost:8080/users

Delete - http://localhost:8080/users/user/{userId}
```

### Documentation
* [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)
* [Hexagonal Architecture (jmgarridopaz)](https://jmgarridopaz.github.io/content/hexagonalarchitecture.html#tc2-6)
* [Validation Spring Boot](https://www.baeldung.com/spring-boot-bean-validation)
* [Slf4j Config](https://mkyong.com/spring-boot/spring-boot-slf4j-logging-example/)
