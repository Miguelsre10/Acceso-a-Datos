/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tema1lecturadom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author miguel
 */
public class Tema1LecturaDOM {

    public static void main(String[] args) {
        Document doc = abrir_XML_DOM(new File("libros.xml"));
        System.out.println(recorrerDOMyMostrar(doc));
        
        anadirLibro(doc, "2000", "Mario Vargas Llosa", "La Fiesta del Chivo");
        
        anadirLibroE(doc, "2004", "Carlos Ruiz Zafón ", "La Sombra del Viento", "Planeta");
        
        System.out.println("Añadimos libro");
        System.out.println(recorrerDOMyMostrar(doc));

        
    }
    
    public static Document abrir_XML_DOM (File fichero){
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            //ignora comentarios y whitespaces
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            doc = builder.parse(fichero);
            
        } catch (ParserConfigurationException ex) {
            System.getLogger(Tema1LecturaDOM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (SAXException ex) {
            System.getLogger(Tema1LecturaDOM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(Tema1LecturaDOM.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return doc;
    }
    
    
    /**
     * procesamos un nodo libro
     * @param n
     * @return 
     */
    private static String[] procesarLibro(Node n){
        String datos[] = new String[4];
        Node ntemp = null;
        int cont = 1;
        
        //      atributos       1º atributo  Valor
        datos[0]=n.getAttributes().item(0).getNodeValue();
        //nodos hijo del primer nodo(<libro>) de la lista
        NodeList nodos = n.getChildNodes();
        
        for(int i = 0 ; i < nodos.getLength() ; i++){
            //procesamos todos los nodos hijo de <libro>
            ntemp = nodos.item(i);
                                    // es <div> <a> <p>...
            if(ntemp.getNodeType() == Node.ELEMENT_NODE){
                //de <autor><titulo> -> hijos -> primer hijo(es texto)->valor del texto
                datos[cont] = ntemp.getChildNodes().item(0).getTextContent();
                cont++;
            }
        }
        return datos;
    }
    
    
    public static String recorrerDOMyMostrar(Document doc){
        String datos_nodo[]=null;
        String salida="";
        Node node;
        
        //primer elemento
        Node raiz = doc.getFirstChild();
        
        //lista con los nodos hijo del nodo raiz
        NodeList nodelist = raiz.getChildNodes();
        
        for (int i = 0; i < nodelist.getLength(); i++){
            node = nodelist.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                //es nodo <libro>
                datos_nodo = procesarLibro(node);
                salida += "\n\nPublicado en: " + datos_nodo[0];
                salida += "\nEl autor es: " + datos_nodo[1];
                salida += "\nEl titulo es: " + datos_nodo[2];
                if(datos_nodo[3] != null){
                    salida += "\nLa editorial es: " + datos_nodo[3];
                }
            }
        }
        return salida;
    }
    
    public static void anadirLibro(Document doc, String fecha, String titulo, String autor){
       //creamos elemento <libro>
        Element nlibro = doc.createElement("libro");
        //anadimos atributo de publicacion
        nlibro.setAttribute("publicado_en", fecha);
        
        //creamos elemento de tipo <Titulo>
        Element ntitulo = doc.createElement("Titulo");
        //añadimos el hijo de tipo texto
        ntitulo.appendChild(doc.createTextNode(titulo));
        
        Element nautor = doc.createElement("Autor");
        nautor.appendChild(doc.createTextNode(autor));
        
        
        //añadimos a <libro> los nodos de hijo
        nlibro.appendChild(ntitulo);
        nlibro.appendChild(nautor);
        
        
        //hay que añadir al primer elemento que es <Libros>
        Node lb = doc.getElementsByTagName("Libros").item(0);
        // Como <Libros> es la raiz del doc esto tambien funcionaria
        //Node lb = doc.getFirstChild();
        lb.appendChild(nlibro);
    }
    
    public static void anadirLibroE(Document doc, String fecha, String titulo, String autor, String editorial){
       //creamos elemento <libro>
        Element nlibro = doc.createElement("libro");
        //anadimos atributo de publicacion
        nlibro.setAttribute("publicado_en", fecha);
        
        //creamos elemento de tipo <Titulo>
        Element ntitulo = doc.createElement("Titulo");
        //añadimos el hijo de tipo texto
        ntitulo.appendChild(doc.createTextNode(titulo));
        
        Element nautor = doc.createElement("Autor");
        nautor.appendChild(doc.createTextNode(autor));

        Element neditorial = doc.createElement("Editorial");
        neditorial.appendChild(doc.createTextNode(editorial));
        
        
        //añadimos a <libro> los nodos de hijo
        nlibro.appendChild(ntitulo);
        nlibro.appendChild(nautor);
        nlibro.appendChild(neditorial);
        
        
        //hay que añadir al primer elemento que es <Libros>
        Node lb = doc.getElementsByTagName("Libros").item(0);
        // Como <Libros> es la raiz del doc esto tambien funcionaria
        //Node lb = doc.getFirstChild();
        lb.appendChild(nlibro);
    }
}
