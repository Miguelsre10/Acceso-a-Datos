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
public class Tema1_3_Comprobar_archivo_fichero_examen_recursivo {
    public static void main(String[] args) {
        File loc = new File(args[0]);
        DatosFile(loc, "");
        
    }
    
    public static void DatosFile(File loc, String ident){
        System.out.println(ident + loc.getName()+":");
        if(loc.isDirectory()){
            File[] archivos = loc.listFiles();
            for (int i = 0 ; i < archivos.length; i++){
                DatosFile(archivos[i], ident+"\t");
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
            System.out.println(ident+"\tTamaño: "+ space + "B");
            System.out.println(ident+"\tPermisos: \n"
                    + ident+"\t\tEjecucion: "+ execute + "\n"
                    + ident+"\t\tLectura: " + read + "\n"
                    + ident+"\t\tEscritura: "+ write);
        }
    }
    
}
