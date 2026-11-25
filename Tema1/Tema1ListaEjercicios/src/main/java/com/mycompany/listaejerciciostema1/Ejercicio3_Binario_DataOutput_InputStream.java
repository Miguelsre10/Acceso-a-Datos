/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaejerciciostema1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author miguel
 */
public class Ejercicio3_Binario_DataOutput_InputStream {
    public static void main(String[] args) {
        String[] nombre = new String[]{"Lucas", "Lucía", "Hugo", "Sofía"};
        int[] edad = new int[]{13, 21, 18, 19};
        try {
            File f = new File ("alumnos.bin");
            // abrimos para escribir (binario)| permite escribir bytes en el archivo
            FileOutputStream fos = new FileOutputStream(f);
            // DataOutputStream nos permite escribir diferentes tipos de datos
            DataOutputStream dos = new DataOutputStream(fos);
            for(int i = 0 ; i<nombre.length; i++){
                dos.writeUTF(nombre[i]);
                dos.writeInt(edad[i]);
                //System.out.println(i);
            }
            //dos.close();
            //fos.close();
        } catch (FileNotFoundException  e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            System.getLogger(Ejercicio3_Binario_DataOutput_InputStream.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        try{
            // (binario)
            //DataInputStream nos permite tratar con diferentes tipos de datos
            DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.bin"));
                                        //FileInputStream permite leer bytes del archivo
            
                                        for(int i = 0 ; i<nombre.length; i++){
                System.out.println("Nombre: "+ dis.readUTF() + " edad: " + dis.readInt());
            }
            
        }
        catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            System.getLogger(Ejercicio3_Binario_DataOutput_InputStream.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        
        // String nombre[x]
        // String edad[x]
        // nombre:(nombre)\tedad:(edad)\n
        
        
        // String nombre[x]
        // String edad[x]
        // nombre:(nombre)\tedad:(edad)\n
        
        
        // String nombre[x]
        // String edad[x]
        // nombre:(nombre)\tedad:(edad)\n
        
        
        // String nombre[x]
        // String edad[x]
        // nombre:(nombre)\tedad:(edad)\n
        
    }
    
}
