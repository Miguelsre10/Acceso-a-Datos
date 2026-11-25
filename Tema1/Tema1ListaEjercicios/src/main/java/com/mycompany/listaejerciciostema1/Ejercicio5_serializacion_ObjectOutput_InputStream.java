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
public class Ejercicio5_serializacion_ObjectOutput_InputStream {
    //ObjectOutputStream y ObjectInputStream
    public static void main(String[] args) {
        String[] nombre = new String[]{"Lucas", "Lucía", "Hugo", "Sofía", "Maria", "Pablo", "Daniel", "Jesus", "Rocio", "Pedro"};
        int[] edad = new int[]{13, 21, 18, 19, 28, 8, 115, 20, 4, 31};
        try{
            File f = new File("objetos_alumnos.bin");
            //Abrimos el archivo en modo escritura (binario)
            FileOutputStream fos = new FileOutputStream(f);
            //ObjectOutputStream Permite escribir objetos completos en un archivo mediante serialización
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Alumno a;
            //escribe los objetos en el fichero
            for (int i = 0; i < 10; i++) {
                //creamos objeto con los datos del array
                a = new Alumno(nombre[i], edad[i]);
                //escribimos en el fichero
                oos.writeObject(a);
            }
            //cerramos los flujos de escritura
            oos.close();
            fos.close();
            
            //abrimos el fichero en modo lectura (binario)
            FileInputStream fis =  new FileInputStream(f);
            //ObjectInputStream: permite recuperar los objetos que anteriormente hemos serializado
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            for (int i = 0; i < 10; i++) {
                a = (Alumno)ois.readObject();
                System.out.println(a.toString());
            }
            //cerramos los flujos de salida
            ois.close();
            fis.close();
                    
        }catch (IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        
    }
    
}
