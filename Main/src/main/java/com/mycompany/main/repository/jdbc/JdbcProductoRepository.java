package com.mycompany.main.repository.jdbc;

import com.mycompany.main.Bd.Conexion;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                if (rs.next())  p.setId(rs.getInt(1));
                
            }
            System.out.println("se creo");
            return p;


        } catch (SQLException ex) {
            throw new RegistroDuplicadoException("no se permite duplicados"+ ex) ;
        }

    }

    @Override
    public Producto actualizar(Producto entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }  
}
