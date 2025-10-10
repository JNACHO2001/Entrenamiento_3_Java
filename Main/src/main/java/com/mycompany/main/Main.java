package com.mycompany.main;

import com.mycompany.main.repository.jdbc.JdbcProductoRepository;
import com.mycompany.main.Models.Producto;
//import java.sql.Connection;
//import com.mycompany.main.Bd.Conexion;
//import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        
        
        
        

        JdbcProductoRepository repo = new JdbcProductoRepository();
        Producto p = new Producto(2,"", 2500, 12);

        repo.crear(p);



    }
}
