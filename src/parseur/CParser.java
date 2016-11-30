package parseur;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import solver.Station;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CParser {

    Station station;

    public CParser(Station station) {
        this.station = station;

    }
     public void run (String path) {
         // Le parseur SAX
         XMLReader reader
                 = null;
         try {
             reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
         } catch (SAXException e) {
             e.printStackTrace();
         }

         // Creation d'un flot XML sur le fichier d'entree
         InputSource input = null;
         try {
             input = new InputSource(new FileInputStream(path));
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }

         // Connexion du ContentHandler
         reader.setContentHandler(new CHandler(station));
         // Lancement du traitement...
         try {
             reader.parse(input);
         } catch (IOException e) {
             e.printStackTrace();
         } catch (SAXException e) {
             e.printStackTrace();
         }
     }
}
