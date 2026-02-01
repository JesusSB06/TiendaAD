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
│   │   ├── loginRegister/
│   │   │   └── LoginRegisterController.java
│   │   ├── asistant/
│   │   │   └── AsistantController.java
│   │   ├── addProduct/
│   │   │   └── AddProductController.java
│   │   ├── client/
│   │   │   └── ClientController.java
│   │   ├── cart/
│   │   │   └── CartClientController.java
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
│   │   ├── ClientsJDialog.java
│   │   ├── RegisterJDialog.java
│   │   ├── CartClientJDialog.java
│   │   ├── AsistantJDialog.java
│   │   ├── AddProductJDialog.java
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

 ### Explicación del código
Teniendo en cuenta que el inicio de sesión para cada tabla es el mismo pero cambiando la sentencia SQL, tomamos la decisión de mostrar solo el código si eres un cliente. Está dentro del paquete **Model**, en una clase llamada **OperationsBD** que se dedicar exclusivamente a la conexión con la base de datos, y hacer las consultas necesarias con sus respectivos métodos:

```java
 public static String usuarioInicioSesion(String dni, String contrasenha_introducida) throws SQLException {
        boolean usuarioExiste = false;
        String select = "SELECT dni, contrasenha FROM cliente";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
        while (rs.next()) {
            String nombre_cliente = rs.getString("dni");
            String password = rs.getString("contrasenha");
            if (nombre_cliente.equals(dni)) {
                usuarioExiste = true;

                if (password.equals(contrasenha_introducida)) {
                    return "inicio";
                } else {
                    return "noContrasenha";
                }
            }
        }
        if (!usuarioExiste) {
            return "registrarse";
        }
        return "no inicio";
    }
```
A continuación, mostramos cómo enlazamos el método anterior con el método que le da acción al botón de **Iniciar** en la vista de inicio de sesión. Indicar que la clase **LoginRegisterController** está dentro del paquete **Controller-LoginRegister**:
```java
 public ActionListener addSaveJButtonActionListener() {
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String usuario = view.getUserNameJTextField();
                    String contrasenha_introducida = view.getPasswordJPasswordField();
                    if (view.getSelectionComboBox().equalsIgnoreCase("cliente")) {
                        String inicioSesion = OperationsDB.usuarioInicioSesion(usuario, contrasenha_introducida);
                        if (inicioSesion.equals("inicio")) {
                            JOptionPane.showMessageDialog(view, "Inicio de sesión realizado", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                            model.setClient(OperationsDB.ObtenerCliente(usuario));
                            view.dispose();
                            mainView.setVisibleStartJButton(true);
                        } else if (inicioSesion.equals("registrarse")) {
                            int opcion = JOptionPane.showConfirmDialog(view, "El usuario no está en la base de datos \n ¿Desea Registrarse?", "¿Registrarse?", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION);
                            System.out.println(opcion);
                            if (opcion == 0) {
                                RegistrarseJDialog rjd = new RegistrarseJDialog(mainView, true);
                                RegisterController rgc = new RegisterController(rjd, model);
                                rjd.setVisible(true);
                                view.setUserNameJTextField("");
                                view.setPasswordJPasswordFiel("");
                            }
                        } else if (inicioSesion.equals("noContrasenha")) {
                            JOptionPane.showMessageDialog(view, "La contraseña introducida no es válida", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        try {
                            int id = Integer.parseInt(usuario);
                            if (OperationsDB.empleadoInicioSesion(id, contrasenha_introducida, view.getSelectionComboBox())) {
                                if (view.getSelectionComboBox().equalsIgnoreCase("técnico")) {
                                    model.setEmployee(OperationsDB.getEmployee(id, "técnico"));
                                } else if (view.getSelectionComboBox().equalsIgnoreCase("asistente")) {
                                    model.setEmployee(OperationsDB.getEmployee(id, "asistente"));
                                }else if(view.getSelectionComboBox().equalsIgnoreCase("supervisor")){
                                    model.setEmployee(OperationsDB.getEmployee(id, "supervisor"));
                                }
                                JOptionPane.showMessageDialog(view, "Inicio de sesión realizado", "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                                view.dispose();
                                mainView.setVisibleStartJButton(true);
                            }else{
                                JOptionPane.showMessageDialog(view, "Error: contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(view, "Error: el id es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (SQLException ex) {
                    System.getLogger(LoginRegisterController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

            }
        };
        return al;
    }
 ```
 Otra parte tambien a destacar es la interfaz **interfaceView**, las clases JDialog, JFrame, etc... siempre son heredadas por elementos de **Swing**, en otras palabras no se les puede heredar una clase, para evitar código duplicado en las vistas, se ha decidido crear esta interfaz la cual a traves del atributio **default** en los metodos, permitira acceder a estos:
 ```java
public interface interfaceView {
    void applyStylesButton();
    default void addTableRenderer(JTable table, int column) {
        table.setRowHeight(80);
        table.getColumnModel().getColumn(column).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);

                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                } else {
                    label.setText(value != null ? value.toString() : "No Image");
                }
                return label;
            }
        });
        DefaultTableCellRenderer textCentrado = new DefaultTableCellRenderer();
        textCentrado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i != column) {
                table.getColumnModel().getColumn(i).setCellRenderer(textCentrado);
            }
        }
    }
    default void ApplyStylesTable(JScrollPane scroll, JTable table) {
        scroll.getViewport().setBackground(Color.WHITE);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setShowGrid(false);
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.WHITE);
        header.setForeground(Color.BLACK);
        header.setBorder(null);
        table.setSelectionBackground(Color.BLACK);
        table.setSelectionForeground(Color.WHITE);
        JScrollBar vertical = scroll.getVerticalScrollBar();
        vertical.setBackground(Color.WHITE);
        vertical.setForeground(Color.LIGHT_GRAY);

    }
    default void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        table.clearSelection();
        table.revalidate();
        table.repaint();
    }  
    default void clearRow(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int row = table.getSelectedRow();
        model.removeRow(row);
    } 
    default void addRowTable(Vector row, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(row);
    }
}
```
## Manual de Usuario 
Para poder entrar en la aplicación, lo primero que tenenos que hacer es iniciar sesión. La ventana principal es la siguiente:

 ![Peticion1](/imagenes/ventanaPrincipal.png)

Una vez dentro, tenemos que introducir los repectivos datos según seas cliente, técnico, asistente o administrador.

 ![Peticion1](/imagenes/combox.PNG)

 Cuando el usuario introduzca los datos correctamente se mostrá un mensaje, y ya podrá acceder a la aplicación.

  ![Peticion1](/imagenes/inicioDeSesion.png)

En caso de introducir un DNI del cliente que no está registrado en la base de datos, nos mostrará un mensaje de si se quiere registrar:

  ![Peticion1](/imagenes/registro.png)

En caso de quere registrarse, tendrá que introducir los datos necesarios:

  ![Peticion1](/imagenes/datosRegistro.png)

### Vista Tecnico
Cuando iniciamos sesion con un usuario que es tecnico tenemos acceso a una ventana la cual podemos ver los estados de los productos y la opcion de poder repararlos.

![Peticion1](/imagenes/principal_tecnico.png)

Si elegimos un producto y le damos al boton para repararlo, nos pregunta si queremos que el producto seleccionado sea reparado.

![Peticion1](/imagenes/confirmar_reparacion.png)
![Peticion1](/imagenes/producto_reparado.png)

Al confirmar el producto cambia su estado a "Disponible" para saber que se encuentra en condiciones para la venta de nuevo.

![Peticion1](/imagenes/producto_disponible.png)

Control de errores: si no hay producto seleccionado y cuando el producto ya se encuentra disponible.

![Peticion1](/imagenes/aviso_selecciona_producto_a_reparar.png)
![Peticion1](/imagenes/aviso_producto_ya_reparado.png)
