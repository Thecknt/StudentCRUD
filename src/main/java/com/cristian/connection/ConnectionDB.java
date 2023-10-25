package com.cristian.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection getConnectionDB(){
        Connection connection = null;
        var dataBase = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/" + dataBase;
        var user ="root";
        var password ="root";
        //Cargo la base del driver en memoria:
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Ocurrio un error: "+ e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        var connection = ConnectionDB.getConnectionDB();
        if (connection != null){
            System.out.println("Conexion Exitosa " + connection);
        } else
            System.out.println("Error al conectarse a la base de Datos :( ");
    }
}
