/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tema1_3_serializaciondom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author miguel
 */
public class Tema1_3_add_element_examen {
    //tambien explicado en el metodo añadirLibro de Tema1LecutraDOM
    
    public static void main(String[] args) {
        
        //abrimos XML
        File fichero = new File("musica.xml");
        Document doc = abrir_XML_DOM(fichero);
        
        //creamos elemento disco, del que cuelgan el resto de elementos
        Element ndisco = doc.createElement("Disco");
        //le añadimos el atributo genero con el valor rock
        ndisco.setAttribute("genero", "rock");
        
        //creamos elemento autor, que es hijo de disco
        Element nautor = doc.createElement("Autor");
        //Cremos un nodo de texto y lo colocamos como hijo de autor
        nautor.appendChild(doc.createTextNode("Rolling Stones"));
        
        Element ntitulo = doc.createElement("Titulo");
        ntitulo.appendChild(doc.createTextNode("It's Only Rock 'n' Roll"));
        
        Element nanyo = doc.createElement("Anyo");
        nanyo.appendChild(doc.createTextNode("1974"));
        
        Element ndiscograf = doc.createElement("Discografica");
        ndiscograf.appendChild(doc.createTextNode("Rolling Stones Records"));
        
        
        //colocamos todos los elementos creados como hijos de disco
        ndisco.appendChild(nautor);
        ndisco.appendChild(ntitulo);
        ndisco.appendChild(nanyo);
        ndisco.appendChild(ndiscograf);
        
        //Node lb = doc.getElementsByTagName("Musica").item(0);
        
        //obtenemos el primer hijo del documento, el elemento raiz
        Node lb = doc.getFirstChild();
        
        //añadimos el disco que hemos creado como hijo del elemento raiz
        lb.appendChild(ndisco);
        
        
        //Ejemplo de como recuperar el ultimo elemento de un dom
        Node raiz = doc.getFirstChild();
        NodeList nodelist = raiz.getChildNodes();
        Node node = nodelist.item(nodelist.getLength()-1);
        
        String disco[] = procesarDisco(node);
        for(int i = 0 ; i < disco.length ; i++){
            System.out.println(disco[i]);
        }
        
        
        //Guarda los cambios en en archivo xml, en este caso en el mismo del que se ha leido
        
        //contiene el Dom a guardar
        DOMSource domSource = new DOMSource(doc);
        
        // Transformer: función transformar el documento DOM en una salida
        //declaramos transformer
        Transformer transformer;
        try {
            //creamos objeto transformer
            transformer = TransformerFactory.newInstance().newTransformer();

            //configuramos el output
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apacher.org/xslt}indent-amount", "4");
            
            //esto convierte el doc en un string si no queremos transformar en un archivo xml
            //StringWriter sw = new StringWriter();
            //StreamResult sr = new StreamResult(sw);
            

            //controla el flujo de salida [ lo redirige hacia fichero-->("salida.txt")]
            StreamResult sr = new StreamResult(fichero);
            
            //ejecuta la transformacion
            //transforma domSource, es decir, nuestro doc, y el StreamResult recoge el resultado
            transformer.transform(domSource, sr);
        } catch (TransformerException ex) {
            System.getLogger(Tema1_3_add_element_examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        
    }
    
    
    //devuelve un document con el arbol de nodos del xml
    public static Document abrir_XML_DOM(File fichero) {
        Document doc = null;
        try {
            // Creamos la fábrica de documentos DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            //ignora comentarios y whitespaces
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

             // Creamos el constructor del documento
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leemos el archivo XML y lo cargamos en memoria
            doc = builder.parse(fichero);
        } catch (SAXException ex) {
            System.getLogger(Tema1_3_add_element_examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(Tema1_3_add_element_examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ParserConfigurationException ex) {
            System.getLogger(Tema1_3_add_element_examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return doc;
    }
    
    private static String[] procesarDisco(Node n){
        
        //array donde guardaremos los datos del disco
        String datos[] = new String[5];
        Node ntemp = null;
        
        //variable auxiliar para recorrer los nodos, empieza en 1 porque en 0 va el genero
        int cont = 1;
        
        //guardamos el genero en la primera posicion del array
        //      atributos       1º atributo  Valor
        datos[0]=n.getAttributes().item(0).getNodeValue();
        
        //nodos hijo del primer nodo(<libro>) de la lista
        NodeList nodos = n.getChildNodes();
        
        for(int i = 0 ; i < nodos.getLength() ; i++){
            //procesamos todos los nodos hijo de <libro>
            
            //guardamos el nodo acutual en un nodo temporal
            ntemp = nodos.item(i);

            //comprobamos que el nodo sea un elemento xml: es <div> <a> <p>...
            if(ntemp.getNodeType() == Node.ELEMENT_NODE){
                // = 
                //ntemp: de <autor> o <titulo> o....
                //getChildNodes(): hijos 
                //item(0): primer hijo(sabemos que es texto)
                //getTextContent(): valor del texto
                datos[cont] = ntemp.getChildNodes().item(0).getTextContent();
                cont++;
            }
        }
        return datos;
    }
    
    
}
