package com.mycompany.main;

import com.mycompany.main.Dao.ProductDao;
import com.mycompany.main.Models.Producto;
//import java.sql.Connection;
//import com.mycompany.main.Bd.Conexion;
//import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        ProductDao dao = new ProductDao();
        Producto p = new Producto(1, "olla", 2500, 2);
        dao.crear(p);

        dao.buscarTodos().forEach(System.out::print);

        /*
        try {
            Connection conn = Conexion.getConnection();
            if (conn != null) {
                System.out.println("conectada");

            } else {
                System.out.println("no  conectada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
    }
}
