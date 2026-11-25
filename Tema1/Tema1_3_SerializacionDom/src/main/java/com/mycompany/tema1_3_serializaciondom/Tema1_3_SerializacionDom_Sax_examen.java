/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema1_3_serializaciondom;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author miguel
 */
public class Tema1_3_SerializacionDom_Sax_examen {
    //ejericio de serializacion utilizando SAX
    // leemos el archivo musica.xml y mostramos por pantalla su contenido
    public static void main(String[] args) {
        try {
        //creamos la instacia de Sax parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //creamos analizador sax
            SAXParser saxParser = factory.newSAXParser();
            
            // creamos manejador personalizado para analizar el xml
            // con los metodos de sax
            DefaultHandler handler = new DefaultHandler() {
                //declaramos la variables que vamos a utilizar
                
                //los string sera la informacion que mostraremos por pantalla
                String genero = "";
                //los boolean seran flags para controlar en que elemento nos encontramos 
                boolean bdisco = false;
                String autor = "";
                boolean bautor = false;
                String titulo = "";
                boolean btitulo = false;
                
                
                //para almacenar el contenido que encontramos cuando se ejecute characters()
                private StringBuilder texto = new StringBuilder();
                
                //se ejecuta cuando el analizador sax se encuentra con una etiqueta de apertura
                
                //qName: Nombre de la etiqueta
                //attributes: atributos que contiene la etiqueta
                public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException{
                    //si la etiqueta es de tipo disco, guardamos el valor de su atriburo genero
                    // y levantamos la flag de disco
                    if(qName.equalsIgnoreCase("disco")){
                        bdisco = true;
                        genero = attributes.getValue("genero");
                    }
                    if(qName.equalsIgnoreCase("autor")){
                        bautor = true;
                    }
                    if(qName.equalsIgnoreCase("titulo")){
                        btitulo = true;
                    }
                }
                
                // se ejecuta cuando se encuentra con una etiqueta de cierres
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    //si la etiquta de cierre en disco bajamos la bandera,
                    // imprimimos la informacion del dico por pantalla y
                    // reseteamos la variables de contenido
                    if(qName.equalsIgnoreCase("disco")){
                        bdisco = false;
                        System.out.println("Disco\nTitulo: " + titulo.trim() + "\nGenero: " + genero.trim()
                                + "\nAutor: " + autor.trim());
                        genero = "";
                        titulo = "";
                        autor = "";
                    }
                    if(qName.equalsIgnoreCase("Autor")){
                        bautor = false;
                    }
                    if(qName.equalsIgnoreCase("Titulo")){
                        btitulo = false;
                    }
                    //cuando se cierra una etiqueta limpiamos el stringBuilder
                    texto.setLength(0);
                }
                
                //se ejecuta dentro de una etiqueta
                
                //ch: array que contiene los caracteres
                //start: posicion donde comienza el texto que queremos leer
                //length: cantidad de caracteres que debemos leer
                public void characters(char ch[], int start, int length)throws SAXException{
                    //almacenamos lo que lee SAX
                    texto.append(ch, start, length);
                    // y lo guarmamos en su variable correspondiente, nos guiamos con las flags
                    if(bautor){
                        autor += texto.toString();
                    }
                    if(btitulo){
                        titulo += texto.toString();
                    }
                }
                
            };
            
            
            //Creamos el objeto file que referencia al archivo que vamos a analizar, contiene su ruta de referencoa
            File file = new File("musica.xml");
            
            //abimos el archivo que vamos a leer
            InputStream inputStream = new FileInputStream(file);
            
            //leemos los caracteres utilizando codificacion UTF-8
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
                                //convierte bytes en caracteres
                                
            //indica de donde vamos a leer
            InputSource is = new InputSource(reader);
                            //prepara el origen de lectura, es decir los bytes del archivo xml
            
            //declaramos codificacion UTF-8 para input sourcr
            //Inidcamos que el archivo viene codificado en UTF-8
            is.setEncoding("UTF-8");
            
            //empezamos el analisis con SAX del contenido del archivo XML
            //utilizando el handler declarado para procesar el fichero
            saxParser.parse(is, handler);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
