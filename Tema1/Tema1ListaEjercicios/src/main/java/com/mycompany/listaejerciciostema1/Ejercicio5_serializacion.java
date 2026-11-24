/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.listaejerciciostema1;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author miguel
 */
public class Ejercicio5_serializacion {
    public static void main(String[] args) {
        String[] nombre = new String[]{"Lucas", "Lucía", "Hugo", "Sofía", "Maria", "Pablo", "Daniel", "Jesus", "Rocio", "Pedro"};
        int[] edad = new int[]{13, 21, 18, 19, 28, 8, 115, 20, 4, 31};
        try{
            File f = new File("objetos_alumnos.bin");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Alumno a;
            for (int i = 0; i < 10; i++) {
                a = new Alumno(nombre[i], edad[i]);
                oos.writeObject(a);
            }
            oos.close();
            fos.close();
            
            FileInputStream fis =  new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            for (int i = 0; i < 10; i++) {
                a = (Alumno)ois.readObject();
                System.out.println(a.toString());
            }
            ois.close();
            fis.close();
                    
        }catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        
    }
    
}
