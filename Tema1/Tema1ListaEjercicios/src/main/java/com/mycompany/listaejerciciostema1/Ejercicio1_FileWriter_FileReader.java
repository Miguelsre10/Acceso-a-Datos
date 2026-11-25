/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaejerciciostema1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author miguel
 */
public class Ejercicio1_FileWriter_FileReader {
    public static void main(String[] args) {
        String[] provincias = {"Jaén", "Córdoba", "Sevilla", "Huelva", "Cádiz", "Málaga", "Granada", "Almería"};
        
        if (args.length == 0) {
            System.out.println("Debes indicar el nombre del fichero como argumento.");
            System.out.println("Ejemplo: java Ejercicio1 provincias.txt");
            return;
        }
        //Creamos objeto fichero
        File fichero = new File (args[0]);
        //abrimos el fichero en escritura
        try (FileWriter fw = new FileWriter(fichero)) { //al declarar con try-with-resources, se cierra solo al terminar el try
            //recorremos el array de provincias
            for (String provincia : provincias) {
                //escribimos las provincias
                fw.write(provincia + System.lineSeparator());
            }
            fw.close();
            System.out.println("Fichero '" + args[0] + "' creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
        
        //leer el contenido de fichero
        try{
            //abrimos ficnero en modo de lectura
            FileReader fr = new FileReader(fichero);   
            //variable auxiliar
            int c;
            //avariable de valores
            String p = "";
            //mientras que no leea un EOF (End Of File) lee el fichero
            while ((c = fr.read()) != -1) {
                p += (char)c;
            }
            //dividimos el string con las provincias
            String[] provinciasW = p.split("\n");
            System.out.println("\nProvincias leidas del fichero");
            for (String provincia : provinciasW){
                System.out.println(provincia);
            }
            
            //cerramos el fichero
            fr.close();
        }
        catch(IOException e){
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    
    }
    
}
