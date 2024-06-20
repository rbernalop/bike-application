# Bike application

## Implementación 🔨

### Organización de paquetes

- bike
    - application = Casos de uso con la lógica de negocio y mapper.
    - domain = Modelo de `Bike`, `Item` e interfaz del repositorio que sirve como puerto.
    - infrastructure = Controlador para endpoints `POST /api/v1/bike` y `GET /api/v1/bike`.
      Adaptador del repositorio con la implementación para PostgreSQL.
- shared = Lógica de excepciones de la aplicación, configuración de seguridad de los endpoints 
y de la caché.

---
### Excepciones

Están implementada para que estén centralizadas y se puedan añadir de manera sencilla en
un [enum de errores](src/main/java/org/rbernalop/bikeapplication/shared/domain/exception/BikeError.java).
Ejemplos de cómo se lanzan se pueden ver en la capa de aplicación:
```java
public void createBike(CreateBikeDto createBikeDto) {
  Bike bike = bikeMapper.toDomain(createBikeDto);

  if (bikeRepository.existsById(bike.id())) {
    throw new BikeException(BikeError.BIKE_ALREADY_EXISTS);
  }
}
```

### Criteria

Se ha utilizado Criteria para los filtros del endpoint `GET /api/v1/bike`. Sobre la 
implementación cabe resaltar su uso con arquitectura hexagonal.
- [BikeCriteria](src/main/java/org/rbernalop/bikeapplication/bike/domain/repository/BikeCriteria.java):
clase de dominio con los datos de búsqueda que se deben proporcionar. 
- [BikeSpecificationBuilder](src/main/java/org/rbernalop/bikeapplication/bike/infrastructure/persistence/jpa/BikeSpecificationBuilder.java):
en esta clase de infraestructura se adapta el BikeCriteria de dominio a
Specification<BikeEntity> que necesita JPA para realizar la búsqueda.

### Caché

La caché ha sido configurada usando `caffeine` en el fichero 
[CacheConfiguration](src/main/java/org/rbernalop/bikeapplication/shared/infrastructure/configuration/CacheConfiguration.java).
El endpoint `GET /api/v1/bike` ha sido anotado con `@Cacheable`.

### Seguridad

Se ha protegido los endpoints haciendo que requieran de un token JWT para
ser accedidos. Toda esta configuración se encuenta en 
[SecurityConfiguration](src/main/java/org/rbernalop/bikeapplication/shared/infrastructure/configuration/SecurityConfiguration.java)
y
[JwtTokenVerifierFilter](src/main/java/org/rbernalop/bikeapplication/shared/infrastructure/filter/JwtTokenVerifierFilter.java).
- Otra opción sería anotar cada endpoint con `@PreAuthorize`

Como no hay usuarios ni login en la aplicación, se ha creado 
[un endpoint](src/main/java/org/rbernalop/bikeapplication/shared/infrastructure/controller/JwtTokenPostController.java)
`GET /api/v1/token` que devuelve un token que se puede usar para probar
las peticiones. Sin el mismo todas devuelven 403.

## Testing 🧪

Para el C3 unitario y el escenario 2 de integración se han usado las 
funcionalidades que proporciona Spring Boot y Cucumber (respectivamente)
para crear tests parametrizados.

### Unitario

Se han realizado tests unitarios a la **capa de aplicación** para cubrir tanto los casos como los casos de error que se puedan dar.

[Crear bicicleta](src/test/java/org/rbernalop/bikeapplication/bike/application/create/BikeCreatorTest.java):
- C1: Se crea la bicicleta correctamente.
- C2: Se lanza una excepción porque la bicicleta ya existe.
- C3-1: Se lanza una excepción porque el Id de la bicicleta es nulo/vacío.
- C3-2: Se lanza una excepción porque el Name de la bicicleta es nulo/vacío.

[Obtener bicicletas](src/test/java/org/rbernalop/bikeapplication/bike/application/find/BikeFinderTest.java):
- C1: Se obtienen bicicletas correctamente.

### Integración

Se ha usado Cucumber para crear los tests a los [endpoints](src/test/resources/cucumber_tests/bike.feature).
- Escenario 1: se crea la bicicleta correctamente.
- Escenario 2: la búsqueda de la bicicleta funciona correctamente.
  - Se busca con los parámetros vacíos y se encuentra una.
  - Se busca con los parámetros no vacíos y se encuentra una. 
  - Se busca con los parámetros no vacíos y no se encuentra ninguna.

## Docker 🐳

Para crear una imagen de Docker la aplicación se usa el [Dockerfile](Dockerfile), que lo hace 
a partir del jar en target/.

Por otro lado, para arrancar el proyecto tenemos:
- [docker-compose.yml](docker-compose.yml) = para desarrollo en local, únicamente tiene la BD 
postgres.
- [docker-compose-dev.yml](docker-compose-dev.yml) = para levantar la aplicación y base de 
datos. Para ello, están conectados mediante una red y además, es necesario sobreescribir 
la variable de entorno que apunta a la BD indicando el nombre del contenedor (en vez de 
localhost).