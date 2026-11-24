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
public class Ejercicio1 {
    public static void main(String[] args) {
        String[] provincias = {"Jaén", "Córdoba", "Sevilla", "Huelva", "Cádiz", "Málaga", "Granada", "Almería"};
        
        if (args.length == 0) {
            System.out.println("Debes indicar el nombre del fichero como argumento.");
            System.out.println("Ejemplo: java Ejercicio1 provincias.txt");
            return;
        }
        File fichero = new File (args[0]);
        try (FileWriter fw = new FileWriter(fichero)) {
            for (String provincia : provincias) {
                fw.write(provincia + System.lineSeparator());
            }
            fw.close();
            System.out.println("Fichero '" + args[0] + "' creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
        
        try{
            FileReader fr = new FileReader(fichero);           
            int c;
            String p = "";
            while ((c = fr.read()) != -1) {
                p += (char)c;
            }
            String[] provinciasW = p.split("\n");
            System.out.println("\nProvincias leidas del fichero");
            for (String provincia : provinciasW){
                System.out.println(provincia);
            }
            
            fr.close();
        }
        catch(IOException e){
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    
    }
    
}
