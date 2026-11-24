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
public class Ejercicio_01 {
    public static void main(String[] args) {
        final String server = "jdbc:mysql://localhost:3306/ies";
        final String user = "root";
        final String pass = "root";
        
        String consulta_sql = "SELECT * FROM alumnos;";
        String consulta_grupo_sql = "SELECT * FROM grupo;";
        
        
        /* especifico, conociendo los datos 
        int id_alumno_A_mayor_media;
        float nota_alumno_A_mayor_media;
        int id_alumno_B_mayor_media;
        float nota_alumno_B_mayor_media;
        */
        
        /*
        Opcion 1: listas
        
        Opcion 2: Clase alumno - lista mayor y lista menor con el alumno con mayor/menor media
                    + ( 2 listas) lista de grupos para controlar que grupos existen
        
        Opcion 3: Clase alumno
                    hashmap con array de 2 alumnos 0-> menor y 1->mayor
                        id es el grupo
        
        necesita query para tabla grupo / o un join
        en todos lista de grupos con sus id / nombre de grupo en el alumno
        */
        
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
                lista_grupos.put(rs.getInt(1), rs.getString(2));
            }
            
            PreparedStatement ps2 = conn.prepareStatement(consulta_sql, 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = ps2.executeQuery();
            
            
            while(rs.next()){
                int id_alumno = rs.getInt(1);
                String nombre_alumno = rs.getString(2);
                float nota_alumno = rs.getFloat(3);
                int id_grupo = rs.getInt(4);
                
                // media general de cada grupo
                if(media_grupos.containsKey(id_grupo)){
                    Float[] grupo = media_grupos.get(id_grupo);
                    grupo[0]++;
                    grupo[1] += nota_alumno;
                }
                else{
                    media_grupos.put(id_grupo, new Float[]{
                        1f,
                        nota_alumno
                    });
                }
                
                // nota media maxima y minima de cada grupo
                if(lista_alumnos.containsKey(id_grupo)){
                    Alumno[] v_alumnos = lista_alumnos.get(id_grupo);
                    if(nota_alumno < v_alumnos[0].getNota()){
                        v_alumnos[0] = new Alumno(id_alumno, nombre_alumno, nota_alumno);
                    }
                    else if(nota_alumno > v_alumnos[1].getNota()){
                        v_alumnos[1] = new Alumno(id_alumno, nombre_alumno, nota_alumno);
                    }
                    
                }
                else{
                    // si no existe el grupo creamos el array con el alumno en las dos posiciones
                    lista_alumnos.put(id_grupo, new Alumno[]{
                        new Alumno(id_alumno, nombre_alumno, nota_alumno),
                        new Alumno(id_alumno, nombre_alumno, nota_alumno),
                    });
                }
            }
            
            //sacamos los resultados
            Set<Integer> id_grupos  = lista_grupos.keySet();
            
            
            
            System.out.println("Media de cada grupo");
            for (int i : id_grupos){
                System.out.println(" - " + lista_grupos.get(i) + ": " + (media_grupos.get(i)[1]/media_grupos.get(i)[0])  );
            }
            System.out.println("Alumnos con la menor y con la mayor media de cada grupo");
            for (int i : id_grupos){
                Alumno[] l_alumnos = lista_alumnos.get(i);
                Alumno menor = l_alumnos[0];
                Alumno mayor = l_alumnos[1];
                
                System.out.println(" - " + lista_grupos.get(i) + ": ");
                System.out.println("    - Menor: " + menor.getNombre() + " - " + menor.getNota());
                System.out.println("    - Mayor: " + mayor.getNombre() + " - " + mayor.getNota());
            }
            
            
            
        } catch (SQLException ex) {
            System.getLogger(Ejercicio_01.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
}
