package com.kurotkin;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by Vitaly Kurotkin on 28.06.2017.
 */
public class XMLwriter {
    private static DocumentBuilder builder;
    private static Logger log = Logger.getLogger(XMLwriter.class.getName());

    public static void WriteXML(ArrayList<Fastener> fasteners) {
        ParamLangXML();
        WriteParamXML(fasteners);
    }

    private static void ParamLangXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.warning(e.toString());
            e.printStackTrace();
        }
    }

    private static void WriteParamXML(ArrayList<Fastener> fasteners) {
        Document doc = builder.newDocument();
        Element RootElement = doc.createElement(fasteners.get(0).tagName);

        for(Fastener f : fasteners) {
            Element fastenerXML = doc.createElement("w" + Integer.toString(f.id));

            Element idXML = doc.createElement("id");
            idXML.appendChild(doc.createTextNode(Integer.toString(f.id)));
            fastenerXML.appendChild(idXML);

            Element torqueXML = doc.createElement("torque");
            torqueXML.appendChild(doc.createTextNode(Double.toString(f.torque)));
            fastenerXML.appendChild(torqueXML);

            Element timeXML = doc.createElement("time");
            String date = Settings.dateFormat.format(f.dat);
            timeXML.appendChild(doc.createTextNode(date));
            Element sernoXML = doc.createElement("serno");
            sernoXML.appendChild(doc.createTextNode(Integer.toString(f.serno)));

            switch(fasteners.get(0).tagName) {
                case "Stahlwille" :
                    fastenerXML.appendChild(timeXML);
                    fastenerXML.appendChild(sernoXML);
                    break;
                case "TohnichiCEM" :
                    fastenerXML.appendChild(timeXML);
                    break;
            }

            RootElement.appendChild(fastenerXML);
        }
        doc.appendChild(RootElement);

        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            File folder = new File("C:\\zxy");
            if (!folder.exists()) {
                folder.mkdir();
            }
            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("C:\\zxy\\torquewr.xml")));
        } catch (TransformerConfigurationException e) {
            log.warning(e.toString());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.warning(e.toString());
            e.printStackTrace();
        } catch (TransformerException e) {
            log.warning(e.toString());
            e.printStackTrace();
        }
    }
}
