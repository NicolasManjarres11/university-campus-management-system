# 🎓 API RESTful - Sistema de Gestión de Campus Universitario

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-red.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Descripción

API RESTful desarrollada con **Spring Boot** para la gestión integral de un campus universitario. Permite administrar estudiantes, profesores, cursos e inscripciones, integrando seguridad JWT, manejo global de excepciones, validaciones personalizadas y documentación interactiva con OpenAPI/Swagger.

---

## 🚀 Características principales

- **Autenticación y autorización JWT** con roles (`ROLE_ADMIN`, `ROLE_STUDENT`)
- **CRUD completo** para estudiantes, profesores, cursos e inscripciones
- **Validaciones personalizadas** y manejo global de excepciones
- **Documentación interactiva** con Swagger/OpenAPI
- **Modelo de datos robusto** y relaciones entre entidades
- **Buenas prácticas** de arquitectura y código

---

## 🏗️ Estructura del proyecto

```
src/
  main/
    java/
      com/devsenior/nmanja/university_campus_management_system/
        config/         # Configuración de seguridad y JWT
        controllers/    # Controladores REST
        exceptions/     # Excepciones personalizadas
        helper/         # Clases utilitarias
        mappers/        # Mapeadores entre entidades y DTOs
        model/
          dto/         # Data Transfer Objects (records)
          entities/     # Entidades JPA
          enums/        # Enums de dominio
          summaries/    # Resumen de DTOs 
        repositories/   # Repositorios JPA
        services/       # Interfaces de servicios
        services/impl/  # Implementaciones de servicios
        util/           # Utilidades (JWT, etc.)
    resources/
      application.properties
```

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- Spring Validation
- Spring Web
- PostgreSQL
- JWT (JSON Web Token)
- MapStruct (Java Records)
- OpenAPI/Swagger (springdoc-openapi)



---

## 🛡️ Seguridad

- **Autenticación JWT**: Los usuarios deben autenticarse para obtener un token JWT.
- **Roles**:
  - `ROLE_ADMIN`: Acceso total a todas las operaciones.
  - `ROLE_STUDENT`: Acceso solo a su propio perfil, cursos disponibles y gestión de sus propias inscripciones.
- **Protección de endpoints**: Los endpoints están protegidos según el rol del usuario autenticado.

---

## 📚 Entidades principales

- **Student**: id, name, email (único), studentNumber (único), user, enrollments
- **Professor**: id, name, department, email (único), courses
- **Course**: id, courseName, courseCode (único), description, maxStudents, studentsInCourse, professor, enrollments
- **Enrollment**: id, student, course, inscriptionDate, status (`ACTIVO`, `COMPLETADO`, `RETIRADO`)
- **User**: id, username (único), password, roles

---

## 📝 Validaciones personalizadas

- Email de estudiante debe pertenecer al dominio institucional (****@devsenior.edu.co).
- Código de curso debe seguir el patrón `ABC-123`.
- No se permite inscribir estudiantes en cursos sin cupo disponible.

---

## ⚠️ Manejo global de excepciones

- Todas las excepciones son gestionadas por un `@ControllerAdvice` global.
La API incluye manejo personalizado de excepciones:

- **400 Bad Request**: Datos de entrada inválidos
- **404 Not Found**: Recurso no encontrado
- **409 Conflict**: Conflicto de datos (duplicados, reglas de negocio)
- **403 Forbidden**: Acceso denegado por permisos
- **500 Internal Server Error**: Error interno inesperado

Ejemplo de respuesta de error:

  ```json
  {
    "status": 400,
    "message": "Los datos ingresados no son válidos.",
    "errors": [
      "email: El correo no tiene un formato valido 'usuario@devsenior.edu.co'"
    ],
    "timestamp": "2024-07-08T12:34:56"
  }
  ```

---

## 🔐 Autenticación y uso de la API

1. **Autenticación**:  
   Realiza un POST a `/authenticate` con tu username y password para obtener un JWT.
   ```json
   {
     "username": "usuario.ejemplo",
     "password": "tu_contraseña"
   }
   ```
   Respuesta:
   ```json
   {
     "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
   }
   ```

2. **Usa el token JWT** en el header `Authorization` para acceder a los endpoints protegidos:
   ```
   Authorization: Bearer {tu_token_jwt}
   ```

---

## 📖 Documentación interactiva

- Accede a la documentación Swagger/OpenAPI en:  
  [http://localhost:8080/swagger-ui/index.htm](http://localhost:8080/swagger-ui/index.htm)  


---

## 📦 Prerrequisitos

Antes de ejecutar este proyecto, asegúrate de tener instalado:

- **Java 17** o superior
- **Maven 3.8+**
- **PostgreSQL 15+**
- **Git**

---

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/university-campus-management-system.git
cd university-campus-management-system
```

### 2. Configurar Base de Datos

1. **Crear base de datos PostgreSQL:**
    ```sql
    CREATE DATABASE campus_management;
    ```

2. **Configurar credenciales** en `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/campus_management
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_CONTRASEÑA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```

### 3. Ejecutar la Aplicación

```bash
# Compilar y ejecutar con Maven Wrapper
./mvnw spring-boot:run

# O alternativamente
./mvnw clean install
java -jar target/university-campus-management-system-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: `http://localhost:8080`

---

### 4. **Accede a la API y documentación:**
   - API: [http://localhost:8080/api/campus](http://localhost:8080/api/campus)
   - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 📝 Notas finales

- Sigue las mejores prácticas de arquitectura y código limpio.
- Todos los DTOs y endpoints están documentados con anotaciones OpenAPI.
- El sistema es fácilmente extensible para nuevas reglas de negocio o entidades.

--- 

## 🙏 Agradecimientos

- Spring Boot Team por el excelente framework
- PostgreSQL por la base de datos robusta
- Comunidad de desarrolladores Dev Senior, por la asesoría y el conocimiento brindado para el desarrollo del proyecto.

---

<<<<<<< HEAD
⭐ Si este proyecto te ha sido útil, ¡no olvides darle una estrella!
=======
⭐ Si este proyecto te ha sido útil, ¡no olvides darle una estrella!
>>>>>>> 32143917b7c4ca60aebf3760c6280fd3a2126b71
