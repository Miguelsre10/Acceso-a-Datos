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

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                String genero = "";
                boolean bdisco = false;
                String autor = "";
                boolean bautor = false;
                String titulo = "";
                boolean btitulo = false;
                
                private StringBuilder texto = new StringBuilder();
                
                public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException{
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
                
                public void endElement(String uri, String localName, String qName) throws SAXException {
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
                    texto.setLength(0);
                }
                
                public void characters(char ch[], int start, int length)throws SAXException{
                    texto.append(ch, start, length);
                    if(bautor){
                        autor += texto.toString();
                    }
                    if(btitulo){
                        titulo += texto.toString();
                    }
                }
                
            };

            File file = new File("musica.xml");
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            saxParser.parse(is, handler);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
