/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaejerciciostema1;
/*
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
*/
import java.io.*;

/**
 *
 * @author miguel
 */
public class Ejercicio7_acceso_directo {
    //lectura escritura por acceso directo
    public static void main(String[] args) {
        String[] nombre = new String[]{"Lucas", "Lucia", "Hugo", "Sofia", "Maria", "Pablo", "Daniel", "Jesus", "Rocio", "Pedro"};
        int[] edad = new int[]{13, 21, 18, 19, 28, 8, 115, 20, 4, 31};
        String nom;
        try{
            File f = new File("directo_alumnos.dat");
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.seek(0);
            
            //tamano de string 10 - tamano de int 4
            int tamanoregistro = 14;
            //lleva al registro 5
            //raf.seek(4*tamanoregistro);
            
            
            // escritura
            for(int i = 0 ; i < 10 ; i++){
                //raf.seek(i*tamanoregistro);
                nom = nombre[i];
                if(nom.length() < 10 ){
                    nom=String.format("%-10s", nom); // alinea nom a la izquierda y añade caracteres en blanco
                }
                else{
                    nom = nom.substring(0, 10);
                }
                raf.writeBytes(nom);
                raf.writeInt(edad[i]);                
            }
            
            
            // LECTURA
            raf.seek(0);
            int num;
            for(int i = 0 ; i < 10 ; i++){
                //raf.seek(i*tamanoregistro);
                nom="";
                for(int j = 0 ; j < 10 ; j++){//los diez caracteres del string
                    nom+=(char)raf.readByte();
                }
                //num=raf.readInt();
                
                System.out.println("Nombre: " + nom + "\tEdad: " + raf.readInt() );
            }            
            
        }catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
}
