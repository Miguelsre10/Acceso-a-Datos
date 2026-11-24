/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tema1_3_serializaciondom;

import java.io.File;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author miguel
 */
public class Tema1_3_SerializacionDom {

    public static void main(String[] args) {
        try{
            //creamos doc
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            doc.setXmlVersion("1.0");
            
            //elemento raiz
            Element elmClientes = doc.createElement("clientes");
            doc.appendChild(elmClientes);
            
            //elemnto cliente y atributos
            Element elmCliente = doc.createElement("cliente");
            elmCliente.setAttribute("DNI", "11444555D");
            
            // elementos bajo cliente
            Element elmApell = doc.createElement("apellidos");
                // nodo de texto dentro de elemento apellidos
            elmApell.appendChild(doc.createTextNode("Lozano"));
            // colocamos validez como hijo de cliente
            elmCliente.appendChild(elmApell);
            
            //elemento validez bajo cliente
            Element elmValidez = doc.createElement("validez");
            // atributos de validez
            elmValidez.setAttribute("estado", "borrado");
            elmValidez.setAttribute("timestamp", "456342133");
            // colocamos validez como hijo de cliente
            elmCliente.appendChild(elmValidez);
            
            //añadimos cliente a raiz
            elmClientes.appendChild(elmCliente);
            
            
            DOMSource domSource = new DOMSource(doc);
            
            //Transforma el domsource
            Transformer transformer=TransformerFactory.newInstance().newTransformer();
            //configuramos el output
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apacher.org/xslt}indent-amount", "4");
            
            //esto convierte el doc en un string
            //StringWriter sw = new StringWriter();
            //StreamResult sr = new StreamResult(sw);
            
            File fichero = new File("salida.xml");
            //controla el flujo de salida [ lo redirige hacia fichero-->("salida.txt")]
            StreamResult sr = new StreamResult(fichero);
            
            //transforma domSource, es decir, nuestro doc, y el StreamResult recoge el resultado
            transformer.transform(domSource, sr);
            
        } catch (TransformerException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        

    }
}
