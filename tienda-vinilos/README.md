# Tienda de Vinilos - Taller Practico 2

Aplicacion web con **Spring Boot + JPA** que integra una **base de datos relacional (MySQL)**,
aplica las **relaciones entre entidades** (uno a uno, uno a muchos y muchos a muchos) e
implementa **CRUD completo** sobre todas ellas, con validaciones y mensajes claros.

## 1. Requisitos

- **JDK 21** (recomendado). Nota: con JDK 26 Spring Boot 3.2.5 puede fallar al arrancar;
  usa el JDK 21 para este proyecto desde `File > Project Structure > Project`.
- **MySQL** instalado y en ejecucion.
- IntelliJ IDEA.

## 2. Configurar la base de datos

1. En MySQL crea la base de datos (o deja que se cree sola con la URL):
   ```sql
   CREATE DATABASE tiendadb;
   ```
2. En `src/main/resources/application.properties` ajusta tu usuario y contrasena:
   ```
   spring.datasource.username=root
   spring.datasource.password=TU_CONTRASENA
   ```
3. Ejecuta `TiendaVinilosApplication`. Hibernate crea automaticamente las tablas.
4. Abre el navegador en **http://localhost:8080/**

> Datos de ejemplo: pon `spring.sql.init.mode=always` la primera vez para cargar `data.sql`,
> luego regresalo a `never` para no duplicar registros.
>
> Si no tienes MySQL, en `application.properties` hay un bloque comentado para usar **H2**
> en memoria; solo comenta el bloque de MySQL y descomenta el de H2.

## 3. Modelo de datos y relaciones

| Relacion          | Tipo            | Implementacion                                   |
|-------------------|-----------------|--------------------------------------------------|
| Cliente - Direccion | **Uno a uno**   | `@OneToOne` (Cliente es dueño, cascade = ALL)    |
| Artista - Disco   | **Uno a muchos** | `@OneToMany` en Artista / `@ManyToOne` en Disco  |
| Cliente - Venta   | **Uno a muchos** | `@OneToMany` en Cliente / `@ManyToOne` en Venta  |
| Disco - Genero    | **Muchos a muchos** | `@ManyToMany` con tabla intermedia `disco_genero` |
| Venta - Disco     | **Muchos a uno** | `@ManyToOne` en Venta                            |

## 4. CRUD y navegacion entre entidades

Cada entidad tiene su listado (READ), formulario para crear/editar (CREATE/UPDATE) y
boton eliminar (DELETE), todo conectado a la base de datos.

- Al guardar un disco se eligen su **artista** y sus **generos** (seleccion multiple).
- Al registrar una venta se eligen el **cliente** y el **disco**.
- **Navegacion entre entidades relacionadas:**
  - `Artistas > Ver discos`: muestra todos los discos de ese artista.
  - `Clientes > Ver ventas`: muestra la direccion y todas las ventas del cliente.

## 5. Validaciones y mensajes

- Validaciones en el backend con Bean Validation (`@NotBlank`, `@Email`, `@Positive`,
  `@Min`, `@Max`, `@Pattern`), mostradas bajo cada campo del formulario.
- Mensajes de confirmacion al crear/editar/eliminar.
- Mensajes de error claros, por ejemplo al intentar eliminar un artista o disco que
  tiene registros asociados (se controla la integridad referencial).

## 6. Estructura del proyecto (MVC)

```
modelo/        -> Entidades JPA (Artista, Genero, Disco, Cliente, Direccion, Venta)
repositorio/   -> Interfaces JpaRepository (acceso a datos / CRUD)
controlador/   -> Controladores que reciben las peticiones y devuelven las vistas
resources/templates -> Vistas Thymeleaf (HTML)
resources/static/css -> Estilos
```

## 7. Repositorio GitHub

Enlace al repositorio: _COLOCAR_AQUI_EL_ENLACE_
