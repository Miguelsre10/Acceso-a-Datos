/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema1_3_serializaciondom;

import java.io.File;

/**
 *
 * @author miguel
 */
public class Tema1_3_Comprobar_archivo_fichero_examen {
    public static void main(String[] args) {
        File loc = new File(args[0]);
        System.out.println(loc.getName()+":");
        if(loc.isDirectory()){
            String[] archivos = loc.list();
            for (int i = 0 ; i < archivos.length; i++){
                System.out.println("\t"+archivos[i]);
            }
        }
        else if(loc.isFile()){
            String execute = "False";
            String read = "False";
            String write = "False";
            if(loc.canExecute()){
                execute = "True";
            }
            if(loc.canRead()){
                read = "True";
            }
            if(loc.canWrite()){
                write = "True";
            }
            long space = loc.getTotalSpace();
            System.out.println("\tTamaño: "+ space + "B");
            System.out.println("\tPermisos: \n"
                    + "\t\tEjecucion: "+ execute + "\n"
                    + "\t\tLectura: " + read + "\n"
                    + "\t\tEscritura: "+ write);
        }
    }
    
}
