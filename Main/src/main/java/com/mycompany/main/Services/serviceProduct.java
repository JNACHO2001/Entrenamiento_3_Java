package com.mycompany.main.Services;

import com.mycompany.main.Exepciones.DatoInvalidoException;
import com.mycompany.main.Models.Producto;
import com.mycompany.main.repository.jdbc.JdbcProductoRepository;

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
        if (p.getPrecio() < 0) {
            throw  new DatoInvalidoException("No se permiten numeros menores o igual que 0");
            
        }
        return repo.crear(p);

        

    }

}
