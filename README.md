<a id="readme-top"></a>
 
[![Java][java-shield]][java-url]
[![Spring Boot][spring-shield]][spring-url]
[![Maven][maven-shield]][maven-url]
[![H2][h2-shield]][h2-url]
# API RESTful de Registro de Cursos en Línea
 
API REST segura para gestionar cursos, categorías e instructores, desarrollada con Spring Boot 3.x, Spring Security (HTTP Basic), Spring Data JPA y base de datos H2 en memoria.
 
<details>
  <summary>Tabla de contenidos</summary>
  <ol>
    <li><a href="#tecnologías-utilizadas">Tecnologías utilizadas</a></li>
    <li><a href="#cómo-ejecutar">Cómo ejecutar</a></li>
    <li><a href="#credenciales-de-prueba">Credenciales de prueba</a></li>
    <li><a href="#urls-importantes">URLs importantes</a></li>
    <li><a href="#endpoints-disponibles">Endpoints disponibles</a></li>
    <li><a href="#ejemplos-de-peticiones-curl">Ejemplos cURL</a></li>
    <li><a href="#arquitectura-del-proyecto">Arquitectura</a></li>
    <li><a href="#códigos-de-respuesta-http">Códigos HTTP</a></li>
  </ol>
</details>

 
## Tecnologías utilizadas
 
- Java 21
- Spring Boot 3.2.0
- Spring Security 6.x (HTTP Basic Authentication)
- Spring Data JPA + Hibernate
- H2 Database (en memoria)
- SpringDoc OpenAPI 2.x (Swagger UI)
- Lombok
- Maven
---
 
## Cómo ejecutar
 
### Prerrequisitos
- Java 21 instalado
- Maven instalado
### Pasos
 
```bash
# Clonar el repositorio
git clone <URL_DEL_REPOSITORIO>
cd registro-cursos
 
# Compilar y ejecutar
mvn spring-boot:run
```
 
La aplicación iniciará en `http://localhost:8080`
 
---
 
## Credenciales de prueba
 
| Campo    | Valor         |
|----------|---------------|
| Username | `admin`       |
| Password | `password123` |
| Rol      | `ADMIN`       |
 
---
 
## URLs importantes
 
| Recurso       | URL                                      |
|---------------|------------------------------------------|
| Swagger UI    | http://localhost:8080/swagger-ui.html    |
| API Docs      | http://localhost:8080/v3/api-docs        |
| H2 Console    | http://localhost:8080/h2-console         |
 
### Configuración H2 Console
 
| Campo      | Valor                    |
|------------|--------------------------|
| JDBC URL   | `jdbc:h2:mem:cursos_db`  |
| User Name  | `sa`                     |
| Password   | *(vacío)*                |
 
---
 
## Endpoints disponibles
 
### Categorías `/api/categorias`
 
| Método | Endpoint                  | Acceso      | Descripción                  |
|--------|---------------------------|-------------|------------------------------|
| GET    | `/api/categorias`         | Público     | Listar todas las categorías  |
| GET    | `/api/categorias/{id}`    | Público     | Obtener categoría por ID     |
| POST   | `/api/categorias`         | Autenticado | Crear nueva categoría        |
| PUT    | `/api/categorias/{id}`    | Autenticado | Actualizar categoría         |
| DELETE | `/api/categorias/{id}`    | Autenticado | Eliminar categoría           |
 
### Instructores `/api/instructores`
 
| Método | Endpoint                   | Acceso      | Descripción                   |
|--------|----------------------------|-------------|-------------------------------|
| GET    | `/api/instructores`        | Público     | Listar todos los instructores |
| GET    | `/api/instructores/{id}`   | Público     | Obtener instructor por ID     |
| POST   | `/api/instructores`        | Autenticado | Crear nuevo instructor        |
| PUT    | `/api/instructores/{id}`   | Autenticado | Actualizar instructor         |
| DELETE | `/api/instructores/{id}`   | Autenticado | Eliminar instructor           |
 
### Cursos `/api/cursos`
 
| Método | Endpoint                          | Acceso      | Descripción                        |
|--------|-----------------------------------|-------------|------------------------------------|
| GET    | `/api/cursos`                     | Público     | Listar todos los cursos            |
| GET    | `/api/cursos/{id}`                | Público     | Obtener curso por ID               |
| GET    | `/api/cursos/nivel/{nivel}`       | Público     | Filtrar cursos por nivel           |
| GET    | `/api/cursos/categoria/{id}`      | Público     | Filtrar cursos por categoría       |
| GET    | `/api/cursos/instructor/{id}`     | Público     | Filtrar cursos por instructor      |
| GET    | `/api/cursos/buscar?nombre=`      | Público     | Buscar cursos por nombre           |
| POST   | `/api/cursos`                     | Autenticado | Crear nuevo curso                  |
| PUT    | `/api/cursos/{id}`                | Autenticado | Actualizar curso                   |
| DELETE | `/api/cursos/{id}`                | Autenticado | Eliminar curso                     |
 
---
 
## Ejemplos de peticiones cURL
 
### Categorías
 
```bash
# Listar categorías (público)
curl http://localhost:8080/api/categorias
 
# Crear categoría (requiere autenticación)
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -u admin:password123 \
  -d '{"name":"Programacion","descripcion":"Cursos de programacion"}'
 
# Actualizar categoría
curl -X PUT http://localhost:8080/api/categorias/1 \
  -H "Content-Type: application/json" \
  -u admin:password123 \
  -d '{"name":"Programacion Web","descripcion":"Actualizado"}'
 
# Eliminar categoría
curl -X DELETE http://localhost:8080/api/categorias/1 \
  -u admin:password123
```
 
### Instructores
 
```bash
# Listar instructores (público)
curl http://localhost:8080/api/instructores
 
# Crear instructor (requiere autenticación)
curl -X POST http://localhost:8080/api/instructores \
  -H "Content-Type: application/json" \
  -u admin:password123 \
  -d '{"name":"Juan Perez","especialidad":"Java","email":"juan@example.com"}'
```
 
### Cursos
 
```bash
# Listar cursos (público)
curl http://localhost:8080/api/cursos
 
# Crear curso (requiere autenticación)
curl -X POST http://localhost:8080/api/cursos \
  -H "Content-Type: application/json" \
  -u admin:password123 \
  -d '{
    "name": "Spring Boot Basico",
    "descripcion": "Aprende Spring Boot desde cero",
    "duracionHoras": 40,
    "nivel": "BASICO",
    "categoria": {"id": 1},
    "instructor": {"id": 1}
  }'
 
# Filtrar por nivel
curl http://localhost:8080/api/cursos/nivel/BASICO
 
# Buscar por nombre
curl "http://localhost:8080/api/cursos/buscar?nombre=Spring"
 
# Eliminar curso (requiere autenticación)
curl -X DELETE http://localhost:8080/api/cursos/1 \
  -u admin:password123
```
 
### Probar seguridad (sin credenciales → 401)
 
```bash
curl -v -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"name":"Test","descripcion":"Test"}'
# Respuesta esperada: HTTP 401 Unauthorized
```
 
---
 
## Arquitectura del proyecto
 
```
registro-cursos/
├── src/main/java/com/ids/cursos/
│   ├── RegistroCursosApplication.java   # Clase principal
│   ├── config/
│   │   ├── SecurityConfig.java          # Configuración Spring Security
│   │   └── SwaggerConfig.java           # Configuración Swagger/OpenAPI
│   ├── controller/
│   │   ├── CursoController.java         # Endpoints de cursos
│   │   ├── CategoriaController.java     # Endpoints de categorías
│   │   └── InstructorController.java    # Endpoints de instructores
│   ├── service/
│   │   ├── CursoService.java            # Lógica de negocio cursos
│   │   ├── CategoriaService.java        # Lógica de negocio categorías
│   │   └── InstructorService.java       # Lógica de negocio instructores
│   ├── repository/
│   │   ├── CursoRepository.java         # Acceso a datos cursos
│   │   ├── CategoriaRepository.java     # Acceso a datos categorías
│   │   └── InstructorRepository.java    # Acceso a datos instructores
│   ├── model/
│   │   ├── Curso.java                   # Entidad Curso
│   │   ├── Categoria.java               # Entidad Categoría
│   │   ├── Instructor.java              # Entidad Instructor
│   │   └── NivelCurso.java              # Enum niveles (BASICO, INTERMEDIO, AVANZADO)
│   └── exception/
│       ├── ResourceNotFoundException.java   # Excepción 404
│       └── BadRequestException.java         # Excepción 400
└── src/main/resources/
    └── application.properties           # Configuración de la aplicación
```
 
### Capas de la aplicación
 
**Controller** → Recibe las peticiones HTTP y delega al servicio correspondiente.
 
**Service** → Contiene la lógica de negocio, validaciones y orquesta las operaciones.
 
**Repository** → Extiende `JpaRepository` para acceso a datos con Spring Data JPA.
 
**Model** → Entidades JPA que mapean a las tablas de la base de datos H2.
 
### Relaciones entre entidades
 
- `Curso` → `Categoria`: ManyToOne (varios cursos pueden pertenecer a una categoría)
- `Curso` → `Instructor`: ManyToOne (varios cursos pueden ser impartidos por un instructor)
### Seguridad
 
- Los endpoints `GET /api/**` son de acceso público.
- Los endpoints `POST`, `PUT` y `DELETE` requieren autenticación HTTP Basic.
- Sin credenciales válidas se retorna `HTTP 401 Unauthorized`.
---
 
## Códigos de respuesta HTTP
 
| Código | Descripción                              |
|--------|------------------------------------------|
| 200    | OK - Operación exitosa                   |
| 201    | Created - Recurso creado correctamente   |
| 204    | No Content - Recurso eliminado           |
| 400    | Bad Request - Datos inválidos            |
| 401    | Unauthorized - Credenciales requeridas   |
| 404    | Not Found - Recurso no encontrado        |
| 500    | Internal Server Error - Error del servidor |
 
---
 
## Contacto
 
[Tu Nombre] - [tu@email.com]
 
Project Link: [https://github.com/your_username/repo_name](https://github.com/your_username/repo_name)
 
<p align="right">(<a href="#readme-top">Regresar al Inicio</a>)</p>
---
 
<!-- MARKDOWN LINKS & BADGES -->
[java-shield]: https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]: https://www.oracle.com/java/
[spring-shield]: https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white
[spring-url]: https://spring.io/projects/spring-boot
[maven-shield]: https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white
[maven-url]: https://maven.apache.org/
[h2-shield]: https://img.shields.io/badge/H2-Database-blue?style=for-the-badge
[h2-url]: https://www.h2database.com/

