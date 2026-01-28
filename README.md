# TiendaAD

## Descripción del Proyecto
El proyecto consiste en la creación de una aplicación basandonos en el modelo **MVC** (Modelo-Vista-Controlador), esta aplicación consistira en la gestión de una tienda informática en la cúal podran acceder tanto **clientes y personal de la tienda**, todos los datos, usuarios, productos, etc... seran manejados a traves de la conexión a una **base de datos MySQL** a traves de un controlador JDBC, el cliente podra comprar varios productos, el técnico podra reparar los productos en estado defectuoso, el asistente podra manejar el stock y el Administrador sera el unico con la capacidad de borrar, crear y modificar usuarios, incluyendo clientes y empleados.

## Características principales
 - **Manejo de base de datos a traves de JAVA**: el programa sera capaz de eliminar, insertar, modificar y actualizar la información de la base de datos sin problema y de forma fluida.
 - **Gestión de distintos tipos de usuarios**: se manejaran distintos tipos de usuarios sin problemas, proporcionandole a cada uno distintos privilegios en la base de datos.
 - **Integración gráfica**: el programa poseera una interfaz gráfica intuitiva que permitira que el usuario utilice las funciones del programa de forma sencilla.

## Estructura del proyecto
```
TiendaAD
├── src/
│   ├── controller/
│   │   ├── MainController.java
│   │   │
│   │   ├── loginRegister/
│   │   │   └── LoginRegisterController.java
│   │   ├── products/
│   │   │   └── ProductsController.java
│   │   ├── register/
│   │   │   └── RegisterController.java
│   │   └── tecnical/
│   │       └── TecnicalController.java
│   ├── model/
│   │   ├── TiendaInf.java
│   │   ├── OperationsDB.java
│   │   ├── Client.java
│   │   ├── Employee.java
│   │   ├── Technician.java
│   │   ├── Asistent.java
│   │   ├── Category.java
│   │   ├── Product.java
│   │   ├── Supplier.java
│   │   └── Sale.java
│   ├── view/
│   │   ├── MainJFrame.java
│   │   ├── LoginRegisterJDialog.java
│   │   ├── ProductsJDialog.java
│   │   ├── RegisterJDialog.java
│   │   └── TecnicalJDialog.java
│   ├── main/
│   │    └── Main.java
│   └── imagenes/
│       └── "Imagenes productos"
└── libs/
    ├── mysql-connector
    └── ScriptTienda.sql
``
