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
        if (p == null || p.getNombre() == null || p.getNombre().isBlank()) {
            throw new DatoInvalidoException("Nombre requerido");
        }

        return repo.crear(p);

    }

}
