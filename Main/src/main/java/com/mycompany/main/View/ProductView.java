package com.mycompany.main.View;

import com.mycompany.main.Exepciones.DatoInvalidoException;
import com.mycompany.main.Models.Producto;
import com.mycompany.main.Services.serviceProduct;
import com.mycompany.main.repository.jdbc.JdbcProductoRepository;
import com.mycompany.main.Exepciones.ErrorSistemaExepcion;
import com.mycompany.main.Exepciones.RecursoNoEncontradoExcepcion;
import com.mycompany.main.Exepciones.RegistroDuplicadoException;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductView {

    private final serviceProduct servicio;

    public ProductView() {
        //  Inyectamos el repositorio al servicio
        this.servicio = new serviceProduct(new JdbcProductoRepository());
    }

    public void mostrarMenu() {
        int opcion = 0;

        do {
            String menu = """
                          ------- M  E  N  U -------
                          1. Agregar
                          2. Listar
                          3. Actualizar
                          4. Eliminar
                          5. Buscar por ID
                          6. Salir
                          """;

            try {
                String input = JOptionPane.showInputDialog(null, menu, "Gestión de Productos", JOptionPane.PLAIN_MESSAGE);

                if (input == null) break; // Usuario canceló
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> listarProductos();
                    case 3 -> actualizarProducto();
                    case 4 -> eliminarProducto();
                    case 5 -> buscarPorId();
                    case 6 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida", "Error", JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } while (opcion != 6);
    }

    // ---------------------- OPCIÓN 5: Buscar por ID ----------------------
    private void buscarPorId() {
        try {
            String input = JOptionPane.showInputDialog(null, "Ingrese el ID del producto:");

            int id = Integer.parseInt(input);
            //Aqui llamamos a servicio y pedomos el metodo que necesitamos 
            Producto producto = servicio.buscarProductoPorId(id);

            if (producto != null) {
                String mensaje = """
                                 === Producto encontrado ===
                                 ID: %d
                                 Nombre: %s
                                 Precio: %.2f
                                 Stock: %d
                                 """.formatted(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock());
                JOptionPane.showMessageDialog(null, mensaje, "Producto encontrado", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (RecursoNoEncontradoExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error de negocio", JOptionPane.WARNING_MESSAGE);
        } catch (ErrorSistemaExepcion e) {
            JOptionPane.showMessageDialog(null, "Error del sistema: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------------- OPCIONES FUTURAS ----------------------
    private void agregarProducto() {
        try {
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del producto");
            String precioText = JOptionPane.showInputDialog(null, "Ingrese el precio del producto");
            double precio = Double.parseDouble(precioText);
            String stockText = JOptionPane.showInputDialog(null, "Ingrese la cantidad  del producto");
            int stock = Integer.parseInt(stockText);
            Producto producto = new Producto(nombre, precio, stock);
            Producto creado = servicio.crearProducto(producto);
            
              String mensaje = """
                                 === Producto Creado ===
                                 ID: %d
                                 Nombre: %s
                                 Precio: %.2f
                                 Stock: %d
                                 """.formatted(creado.getId(), creado.getNombre(), creado.getPrecio(), creado.getStock());
                JOptionPane.showMessageDialog(null, mensaje, "Producto Creado", JOptionPane.INFORMATION_MESSAGE);

        
        } catch (RegistroDuplicadoException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage(), "Error de negocio", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Debe ingresar valores numéricos válidos para precio y stock.",
                    "Error de Entrada",
                    JOptionPane.ERROR_MESSAGE);

        } catch (DatoInvalidoException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de negocio", JOptionPane.WARNING_MESSAGE);

        }

    }

    private void listarProductos() {
        try {

            List<Producto> productos = servicio.listarProductos();
            String lista = "Lsita de productos:\n\n ";

            for (Producto p : productos) {
                lista += "ID:  " + p.getId() + "\n"
                        + "Nombre:  " + p.getNombre() + "\n"
                        + "Precio:  " + p.getPrecio() + "\n"
                        + "Stock:  " + p.getStock() + "\n"
                        + "---------------------------------------------------\n";

            }

            JOptionPane.showMessageDialog(null, lista, "Productos Registrados", JOptionPane.INFORMATION_MESSAGE);

        } catch (RecursoNoEncontradoExcepcion e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void actualizarProducto() {
        JOptionPane.showMessageDialog(null, "Funcionalidad en desarrollo...");
    }

    private void eliminarProducto() {
        JOptionPane.showMessageDialog(null, "Funcionalidad en desarrollo...");
    }
}
