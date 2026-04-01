# tarea4-login-java
Aplicación de escritorio en Java con Swing y MySQL para gestión de usuarios, incluyendo login, registro, actualización, eliminación y cierre de sesión, aplicando programación orientada a objetos.

# Tarea4Login

Aplicación de escritorio desarrollada en Java con Swing y MySQL para la gestión de usuarios.

## Funcionalidades
- Inicio de sesión con usuario y contraseña
- Contraseña oculta
- Registro de usuarios
- Validación de campos obligatorios
- Validación de confirmación de contraseña
- Listado de usuarios registrados
- Actualización de usuarios
- Eliminación de usuarios
- Cierre de sesión
- Refresco automático de la tabla después de registrar, actualizar o eliminar

## Tecnologías utilizadas
- Java
- Swing
- MySQL
- JDBC
- Git y GitHub

## Programación orientada a objetos aplicada
- Abstracción
- Encapsulamiento
- Herencia
- Polimorfismo

## Patrones utilizados
- Singleton
- Separación por capas con DAO y Servicio

## Estructura del proyecto
- `main`
- `modelo`
- `dao`
- `servicio`
- `vista`
- `util`
- `factory`

## Base de datos
Base de datos utilizada: `tarea4_login`

Tabla principal:
- `usuarios`

## Ejecución
1. Crear la base de datos en MySQL
2. Configurar la contraseña en la clase de conexión
3. Ejecutar la clase `Main`
