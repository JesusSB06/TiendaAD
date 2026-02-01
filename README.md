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
```

## Estructura de la base de datos
A continuación vamos a explicar las principales clases con sus atributos:
### Entidades
#### Empleado
Los empleados son quienes gestionan la tienda. Puede haber diferentes tipos:
- **Supervisor**: supervisa a los empleados.
- **Asistente**: ayuda a los empleados y gestiona la entrada de los productos.
-  **Técnico**: repara productos de la tienda.
   
Además tienen una serie de atributos:
| Atributo | Descripción |
|----------|-------------|
| **DNI** | Documento de identidad |
| **Dirección** | Domicilio personal |
| **Correo** | Email de contacto |
| **Teléfono** | Número de contacto |
| **Cargo** | Rol en la tienda |

#### Producto
Es lo que se vende en la tienda. Como cada tabla, contiene diferentes atributos:
| Atributo | Descripción |
|----------|-------------|
| **ID** | Identificador único |
| **Categoría** | Tipo de producto |
| **Precio** | Valor de venta |
| **Stock** | Cantidad disponible |
| **Estado** | Disponible/Agotado |
| **Nombre** | Nombre del producto |

#### Proveedor
Clasificación de los productos por tipo: 
 Atributo | Descripción |
|----------|-------------|
| **ID** | Identificador único |
| **Nombre** | Nombre de la categoría |

#### Categoría
Clasificación de los productos por tipo: 
 Atributo | Descripción |
|----------|-------------|
| **ID** | Identificador único |
| **Nombre** | Nombre de la categoría |

### Relaciones entre tablas
- Un **empleado técnico** puede **reparar** varios productos.
- Un **producto** pertenece a una **categoría**.
- Un **producto** puede ser **proveído** por un proveedor.
- Un **producto** puede estar en varias **ventas**, y la realiza un **cliente**.

### Diagrama entidad-relación
 ![Peticion1](/imagenes/DiagramaTienda.png)
 
## Manual de Usuario 
Para poder entrar en la aplicación, lo primero que tenenos que hacer es iniciar sesión. La ventana principal es la siguiente:

 ![Peticion1](/imagenes/ventanaPrincipal.png)

Una vez dentro, tenemos que introducir los repectivos datos según seas cliente, técnico, asistente o administrador.

 ![Peticion1](/imagenes/combox.png)

 Cuando el usuario introduzca los datos correctamente se mostrá un mensaje, y ya podrá acceder a la aplicación.

  ![Peticion1](/imagenes/inicioDeSesion.png)

En caso de introducir un DNI del cliente que no está registrado en la base de datos, nos mostrará un mensaje de si se quiere registrar:

  ![Peticion1](/imagenes/registro.png)

En caso de quere registrarse, tendrá que introducir los datos necesarios:

  ![Peticion1](/imagenes/datosRegistro.png)

### Vista Tecnico
Cuando iniciamos sesion con un usario que es tecnico tenemos acceso a una ventana la cual podemos ver los estados de los productos y la opcion de poder repararlos.

![Peticion1](/imagenes/principal_tecnico.png)

Si elegimos un producto y le damos al boton para repararlo, nos pregunta si queremos que el producto seleccionado sea reparado.

![Peticion1](/imagenes/confirmar_reparacion.png)
![Peticion1](/imagenes/producto_reparado.png)

Al confirmar el producto cambia su estado a "Disponible" para saber que se encuentra en condiciones para la venta de nuevo.

![Peticion1](/imagenes/producto_disponible.png)

Control de errores: si no hay producto seleccionado y cuando el producto ya se encuentra disponible.

![Peticion1](/imagenes/aviso_selecciona_producto_a_reparar.png)
![Peticion1](/imagenes/aviso_producto_ya_reparado.png)
