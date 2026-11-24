/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema2_conectores_sql_p1.examen_prueba;

/**
 *
 * @author miguel
 */
public class Alumno {
    
    int id;
    String nombre;
    float nota;

    public Alumno() {
    }
    
    public Alumno(int id, String nombre, float nota) {
        this.id = id;
        this.nombre = nombre;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
    
    

    
    
    
    
}
