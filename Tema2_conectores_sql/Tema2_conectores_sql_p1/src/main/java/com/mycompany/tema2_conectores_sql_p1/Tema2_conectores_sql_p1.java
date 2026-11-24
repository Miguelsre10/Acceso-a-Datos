/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tema2_conectores_sql_p1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author miguel
 */
public class Tema2_conectores_sql_p1 {

    public static void main(String[] args) {
        final String server = "jdbc:mysql://localhost:3306/instituto?serverTimezone=UTC"; 
        final String user = "root"; 
        final String pass = "root"; 
        
        try{
           
            Connection conn = DriverManager.getConnection(server, user, pass);
            
            if(conn != null){
                System.out.println("Conexion realizada.");

                DatabaseMetaData metadatos = conn.getMetaData();
                System.out.println("Metadatos: " + metadatos);
                System.out.println("Nombre SGBD: "+metadatos.getDatabaseProductName());
                System.out.println("Version SGBD: "+metadatos.getDatabaseProductVersion());
                System.out.println("Nombre Driver: "+metadatos.getDriverName());
                System.out.println("Version Driver: "+metadatos.getDriverVersion());
                System.out.println("Usuario Conectado: "+metadatos.getUserName());
                
                Statement s = conn.createStatement();
                
                ResultSet rs = s.executeQuery("SELECT * FROM alumnos");
                while(rs.next()){
                    System.out.println("\nAlumno:");
                    System.out.println("ID: " + rs.getInt("id_alumno"));
                    System.out.println("Nombre: " + rs.getString("nombre_alumno"));
                    System.out.println("Nota Media: " + rs.getFloat("nota_media"));
                }
                
                
                
            }
            
            
            
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        
    }
    
    
}
