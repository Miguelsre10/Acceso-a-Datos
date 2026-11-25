/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaejerciciostema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author miguel
 */
public class Ejercicio2_BufferedWriter_BufferedReader {
    //FileWriter: permite escribir caracteres en un archivo
    //BufferedWriter: añade un búfer (memoria intermedia) para hacer más eficiente la escritura
    public static void main(String[] args) {
        String[] provincias = {"Jaén", "Córdoba", "Sevilla", "Huelva", "Cádiz", "Málaga", "Granada", "Almería"};
        
        if (args.length == 0) {
            System.out.println("Debes indicar el nombre del fichero como argumento.");
            System.out.println("Ejemplo: java Ejercicio1 provincias.txt");
            return;
        }
        File fichero = new File (args[0]);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
            for (String provincia : provincias) {
                bw.write(provincia);
                bw.newLine();
            }
            bw.close();
            System.out.println("Fichero '" + args[0] + "' creado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(fichero));           
            String p;
            int i = 0;
            String[] provinciasW = new String[8];
            while ((p = br.readLine()) != null) {
                provinciasW[i] = p;
                i++;
            }

            System.out.println("\nProvincias leidas del fichero");
            for (String provincia : provinciasW){
                System.out.println(provincia);
            }
            
            br.close();
        }
        catch(IOException e){
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        
    }
    
}
