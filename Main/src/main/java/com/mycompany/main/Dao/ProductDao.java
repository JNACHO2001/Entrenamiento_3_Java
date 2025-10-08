package com.mycompany.main.Dao;

import com.mycompany.main.Models.Producto;
import com.mycompany.main.Bd.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Repositorio<Producto>{

    @Override
    public void crear(Producto producto) {
        String sql ="INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";
        
        
        try {
         Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         
         ps.setString(1,producto.getNombre());
         ps.setDouble(2,producto.getPrecio());
         ps.setInt(3, producto.getStock());
         ps.executeUpdate();
         
            System.out.println("producto agregado");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Producto buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> buscarTodos() {
        String sql = "SELECT * FROM productos";
        List<Producto> productos = new ArrayList<>();

        try {

            Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;

    }

    @Override
    public void actualizar(Producto entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
