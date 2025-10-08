package com.mycompany.main.Dao;

import com.mycompany.main.Models.Producto;
import com.mycompany.main.Bd.Conexion;
import java.sql.*;
import java.util.List;

public class ProductDao implements Repositorio<Producto>{

    @Override
    public void crear(Producto producto) {
        String sql ="INSERT INTO productos(nombre, precio, stock) VALUES (?, ?, ?)";
        try {
         Connection conn = Conexion.getConnection();
            
        } catch (Exception e) {
        }
        
    }

    @Override
    public Producto buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Producto> buscarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
