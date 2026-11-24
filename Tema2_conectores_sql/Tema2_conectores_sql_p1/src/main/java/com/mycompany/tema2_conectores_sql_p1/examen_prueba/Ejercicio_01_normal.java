/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema2_conectores_sql_p1.examen_prueba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author miguel
 */
public class Ejercicio_01_normal {
    public static void main(String[] args) {
        final String server = "jdbc:mysql://localhost:3306/ies";
        final String user = "root";
        final String pass = "root";
        
        String consulta_sql = "SELECT * FROM alumnos WHERE id_grupo = ";
        String consulta_grupo_sql = "SELECT * FROM grupo;";
     
        HashMap<Integer, Alumno[]> lista_alumnos = new HashMap<>();
        HashMap<Integer, String> lista_grupos = new HashMap<>();
        
        //en el valor guardamos 0: nuemro de alumnos del grupo
        //                      1: suma de notas
        // al ser un array facilita el la modificacion de los datos
        HashMap<Integer, Float[]> media_grupos = new HashMap<>();
        
        try{
            Connection conn = DriverManager.getConnection(server, user, pass);
            PreparedStatement ps1 = conn.prepareStatement(consulta_grupo_sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rs = ps1.executeQuery();
            
            //grupos y nombres para sacar el resumen de los resultados
            while(rs.next()){
                
                String sql_alumnos_filtro = (consulta_sql+rs.getInt(1)+";");
                PreparedStatement ps2 = conn.prepareStatement(sql_alumnos_filtro, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                ResultSet rs2 = ps2.executeQuery();
                
                String nombre_menor;
                float nota_menor;
                String nombre_mayor;
                float nota_mayor;
                String nombre;
                float nota;
                float media;
                int cantidad;
                
                
                if(rs2.next()){
                    //colocamos lo de la primera fila
                    nombre_menor = rs2.getString(2);
                    nota_menor = rs2.getFloat(3);
                    nombre_mayor = rs2.getString(2);
                    nota_mayor = rs2.getFloat(3);
                    
                    media = rs2.getFloat(3);
                    cantidad = 1;
                    
                    while(rs2.next()){
                        nombre = rs2.getString(2);
                        nota = rs2.getFloat(3);
                        
                        media += nota;
                        cantidad++;
                        
                        if(nota < nota_menor){
                            nota_menor = nota;
                            nombre_menor = nombre;
                        }
                        else if(nota > nota_mayor){
                            nota_mayor = nota;
                            nombre_mayor = nombre;
                        }
                    }
                    
                    media /= cantidad;
                    System.out.println(rs.getString(2));
                    System.out.println(" - Menor: " + nombre_menor + ": " + nota_menor);
                    System.out.println(" - Mayor: " + nombre_mayor + " - " + nota_mayor);
                    System.out.println(" - Media del grupo: " + media);
                }
            }   
            
        } catch (SQLException ex) {
            System.getLogger(Ejercicio_01.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
}
