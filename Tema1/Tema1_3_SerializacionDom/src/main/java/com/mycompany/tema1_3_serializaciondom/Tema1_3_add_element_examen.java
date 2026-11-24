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
    
    public static void main(String[] args) {
        File fichero = new File("musica.xml");
        Document doc = abrir_XML_DOM(fichero);
        
        Element ndisco = doc.createElement("Disco");
        ndisco.setAttribute("genero", "rock");
        
        Element nautor = doc.createElement("Autor");
        nautor.appendChild(doc.createTextNode("Rolling Stones"));
        
        Element ntitulo = doc.createElement("Titulo");
        ntitulo.appendChild(doc.createTextNode("It's Only Rock 'n' Roll"));
        
        Element nanyo = doc.createElement("Anyo");
        nanyo.appendChild(doc.createTextNode("1974"));
        
        Element ndiscograf = doc.createElement("Discografica");
        ndiscograf.appendChild(doc.createTextNode("Rolling Stones Records"));
        
        ndisco.appendChild(nautor);
        ndisco.appendChild(ntitulo);
        ndisco.appendChild(nanyo);
        ndisco.appendChild(ndiscograf);
        
        //Node lb = doc.getElementsByTagName("Musica").item(0);
        Node lb = doc.getFirstChild();
        lb.appendChild(ndisco);
        
        
        Node raiz = doc.getFirstChild();
        NodeList nodelist = raiz.getChildNodes();
        Node node = nodelist.item(nodelist.getLength()-1);
        
        String disco[] = procesarDisco(node);
        for(int i = 0 ; i < disco.length ; i++){
            System.out.println(disco[i]);
        }
        
        /*
        //Guarda los cambios
        DOMSource domSource = new DOMSource(doc);
        //Transforma el domsource
        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();

            //configuramos el output
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apacher.org/xslt}indent-amount", "4");
            
            //esto convierte el doc en un string
            //StringWriter sw = new StringWriter();
            //StreamResult sr = new StreamResult(sw);
            

            //controla el flujo de salida [ lo redirige hacia fichero-->("salida.txt")]
            StreamResult sr = new StreamResult(fichero);
            
            //transforma domSource, es decir, nuestro doc, y el StreamResult recoge el resultado
            transformer.transform(domSource, sr);
        } catch (TransformerException ex) {
            System.getLogger(Tema1_3_add_element_examen.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }*/
        
        
    }
    
    public static Document abrir_XML_DOM(File fichero) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            //ignora comentarios y whitespaces
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();

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
        String datos[] = new String[5];
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
    
    
}
