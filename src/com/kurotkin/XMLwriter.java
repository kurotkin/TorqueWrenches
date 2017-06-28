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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Vitaly Kurotkin on 28.06.2017.
 */
public class XMLwriter {
    private static DocumentBuilder builder;

    public static void WriteXML(ArrayList<Fastener> fasteners) {
        ParamLangXML();
        WriteParamXML(fasteners);
    }

    private static void ParamLangXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void WriteParamXML(ArrayList<Fastener> fasteners) {
        Document doc = builder.newDocument();
        Element RootElement = doc.createElement("Stahlwille");

        for(Fastener f : fasteners) {
            Element fastenerXML = doc.createElement("w" + Integer.toString(f.id));

            Element timeXML = doc.createElement("time");
            String date = Settings.dateFormat.format(f.dat);
            timeXML.appendChild(doc.createTextNode(date));
            fastenerXML.appendChild(timeXML);

            Element torqueXML = doc.createElement("torque");
            torqueXML.appendChild(doc.createTextNode(Double.toString(f.torque)));
            fastenerXML.appendChild(torqueXML);

            Element tol_lowerXML = doc.createElement("tol_lower");
            tol_lowerXML.appendChild(doc.createTextNode(Double.toString(f.tol_lower)));
            fastenerXML.appendChild(tol_lowerXML);

            Element tol_upperXML = doc.createElement("tol_upper");
            tol_upperXML.appendChild(doc.createTextNode(Double.toString(f.tol_upper)));
            fastenerXML.appendChild(tol_upperXML);

            Element sernoXML = doc.createElement("serno");
            sernoXML.appendChild(doc.createTextNode(Integer.toString(f.serno)));
            fastenerXML.appendChild(sernoXML);

            Element nameXML = doc.createElement("name");
            nameXML.appendChild(doc.createTextNode(f.name));
            fastenerXML.appendChild(nameXML);

            Element resultXML = doc.createElement("result");
            resultXML.appendChild(doc.createTextNode(Boolean.toString(f.result)));
            fastenerXML.appendChild(resultXML);

            RootElement.appendChild(fastenerXML);
        }
        doc.appendChild(RootElement);

        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("C:\\zxy\\torquewr.xml")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}
