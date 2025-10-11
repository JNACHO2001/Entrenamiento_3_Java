package com.mycompany.main.repository.jdbc;

import com.mycompany.main.Bd.Conexion;
import com.mycompany.main.Exepciones.ErrorSistemaExepcion;
import com.mycompany.main.Exepciones.RecursoNoEncontradoExcepcion;
import com.mycompany.main.Exepciones.RegistroDuplicadoException;
import com.mycompany.main.Models.Producto;
import com.mycompany.main.repository.IProductoRepo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JdbcProductoRepository implements IProductoRepo {

    @Override
    public Producto buscarPorId(Integer id) {

        String sql = "SELECT * FROM productos WHERE id = ? ";
        try {
            Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));

                    return p;

                } else {
                    throw new RecursoNoEncontradoExcepcion("No se encontro el producto" + id);
                }

            }

        } catch (SQLException e) {
            throw new ErrorSistemaExepcion("Error al buscar producto por ID" + e.getMessage());
        }

    }

    @Override
    public void eliminar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto crear(Producto p) {
        String sql = "INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId(rs.getInt(1));
                }

            }
            System.out.println("se creo");
            return p;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new RegistroDuplicadoException("Y existe el producto.");

            } else {
                throw new ErrorSistemaExepcion("no se pudo crear " + e.getMessage());

            }
        }
    }

    @Override
    public Producto actualizar(Producto entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
