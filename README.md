# mongo-rest-app Project

Este es un proyecto que tiene como primera finalidad aprender el uso de quarkus con una BD Mongo, implementando funcionalidad tal cual un CRUD, tambien la generacion de un token, y la validacion de creacion de contraseña.

---
# Requisito de sistema

- Java Open jdk 11
- GraalVM

---
# Extensiones

- quarkus-resteasy-reactive-jsonb
- quarkus-smallrye-openapi
- quarkus-mongodb-panache
- log4j2-jboss-logmanager
- quarkus-resteasy-reactive
- quarkus-smallrye-jwt-build
- quarkus-smallrye-jwt

---
# Ejecutar proyecto

- Windows cmd

    `mvnw compile quarkus:dev`

- Linux bash

    `./mvnw compile quarkus:dev`

---

# Pruebas  
Primero es necesario crear nuevos registros dentro de los catalosgos  "Estatus"  y "Role" para generar id's que nos serviran para probar la herramienta.

Para el catalogo Estatus podemos realizarlo con la plantilla que viene por defecto

- Agregar estatus -> `http://localhost:8080/estatus.html`
   Nota: Se recomienda agregar dos registros como minimo 1.- Primer ingreso   2.- Activo

![image text](https://github.com/OmarArzate/quarkus-mongoDB-example/blob/main/src/main/resources/img/estatus.png)

- Una vez agregados los estatus, entrar en la clase EConstante y asignarle el valor del id a las variables para su buen funcionamiento, esto debido a que el proyecto 
  requiere estos dos id para la buena ejecucion de validaciones e insercciones posteriores 

![image text](https://github.com/OmarArzate/quarkus-mongoDB-example/blob/main/src/main/resources/img/constantes.png)

- Agregar roles -> `http://localhost:8080/roles`
![image text](https://github.com/OmarArzate/quarkus-mongoDB-example/blob/main/src/main/resources/img/roles.png)


Con lo anterior realizado podemos realizar la creacion de un usuario tanto desde los swagger, postman o desde la siguiente interfaz

- Crear un usuario -> `http://localhost:8080/usuario.html`
![image text](https://github.com/OmarArzate/quarkus-mongoDB-example/blob/main/src/main/resources/img/usuarios.png)

Podemos realizar la generacion de un token desde el swagger o postman con el username y contraseña

![image text](https://github.com/OmarArzate/quarkus-mongoDB-example/blob/main/src/main/resources/img/generartoken.png)

-podemos ver la validacion de tiempo de expiracion de una contraseña cambiando el parametro en la clase Usuario por ejemplo poniendole 1L al valor.
 y despues volver a probar al generar un token
![image text](https://github.com/OmarArzate/quarkus-mongoDB-example/blob/main/src/main/resources/img/expiracioncontrase.png)

-Una vez creado todo lo anterior, podemos habilitar el acceso a ciertos perfiles, descomentando en los controller //@RolesAllowed({ "ADMIN","CNOC"}) para dar acceso  solo a roles del tipo que utilicemos