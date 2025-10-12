package com.mycompany.main.Services;

import com.mycompany.main.Exepciones.DatoInvalidoException;
import com.mycompany.main.Exepciones.RecursoNoEncontradoExcepcion;
import com.mycompany.main.Models.Producto;
import com.mycompany.main.repository.jdbc.JdbcProductoRepository;
import java.util.List;

public class serviceProduct implements ProductoServicioI {

    private final JdbcProductoRepository repo;

    public serviceProduct(JdbcProductoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Producto crearProducto(Producto p) {
        if (p == null || p.getNombre() == null || p.getNombre().isEmpty()) {
            throw new DatoInvalidoException("Nombre requerido");
        }
        if (p.getPrecio() <= 0) {
            throw new DatoInvalidoException("El precio debe ser mayor que 0");

        }
        if (p.getStock() < 0) {
            throw new DatoInvalidoException("El stock no puede ser negativo");

        }
        return repo.crear(p);

    }

    @Override
    public Producto buscarProductoPorId(int id) {
        if (id <=0) {
            throw  new RecursoNoEncontradoExcepcion("El producto con este ID no se encuantra " + id);
            
        }
        return repo.buscarPorId(id);
    }

    @Override
    public List<Producto> listarProductos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto actualizarProducto(Producto p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
