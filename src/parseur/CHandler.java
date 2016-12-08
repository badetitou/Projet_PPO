package parseur;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import solver.*;

/**
 * Created by fdeze on 29/11/16.
 */
public class CHandler implements ContentHandler{

    private String typeCourant;
    private int numero;
    private String nom;
    private double altitude;
    private int x;
    private int y;
    private double tempsFixe;
    private double vitesse;
    private double tpsTrajet;
    private Point arrivee;
    private Point depart;
    private TypeDescente typeDescente;
    private TypeNavette typeNavette;
    private TypeRemontee typeRemontee;

    Station station;

    public CHandler(Station station) {
        this.station = station;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("start document...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\nDocument termine.");
    }

    @Override
    public void startPrefixMapping(String s, String s1) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String s) throws SAXException {

    }

    @Override
    public void startElement(String s, String localName, String s2, Attributes attributes) throws SAXException {
        System.out.println("start "+localName);
        typeCourant=localName;
    }

    @Override
    public void endElement(String s, String localName, String s2) throws SAXException {
        System.out.println("endElement: "+localName);
        if (localName.equals("piste")){
            try {
                station.addTransition(new Descente(numero, nom, depart, arrivee, typeDescente, vitesse));
            } catch (Solver.NoPointException e){
                e.printStackTrace();
            }
        }
        else if (localName.equals("remontee")){
            try{
                station.addTransition(new Remontee(numero, nom, depart, arrivee, typeRemontee, tempsFixe, vitesse));
            } catch (Solver.NoPointException e){
                e.printStackTrace();
            }
        }
        else if(localName.equals("point")){
            try {
                station.addPoint(new Point(numero,nom,altitude,x,y));
            } catch (Solver.PointAlreadyExistException e) {
                e.printStackTrace();
            }
        }
        else if (localName.equals("navette")){
            try {
                station.addTransition(new Navette(numero, nom, depart, arrivee, typeNavette, tpsTrajet));
            } catch (Solver.NoPointException e){
                e.printStackTrace();
            }
        }
        typeCourant=null;
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        if(typeCourant != null) {
            if (typeCourant.equals("numero")) {
                numero = Integer.parseInt(new String(chars, start, length));
            }
            else if(typeCourant.equals("nom")){
                nom = new String(chars, start, length);
            }
            else if(typeCourant.equals("altitude")){
                altitude = Double.parseDouble(new String (chars, start, length));
            }
            else if(typeCourant.equals("x")){
                x = Integer.parseInt(new String(chars, start, length));
            }
            else if(typeCourant.equals("y")){
                y = Integer.parseInt(new String(chars, start, length));
            }
            else if(typeCourant.equals("depart")){
                try {
                    depart = station.getPoint(Integer.parseInt(new String(chars, start, length)));
                } catch (Solver.NoPointException npe){
                    npe.printStackTrace();
                }
            }
            else if(typeCourant.equals("arrivee")){
                try {
                    arrivee = station.getPoint(Integer.parseInt(new String(chars, start, length)));
                } catch (Solver.NoPointException npe){
                    npe.printStackTrace();
                }
            }
            else if(typeCourant.equals("tpsFixe")){
                tempsFixe = Double.parseDouble(new String(chars, start, length));
            }
            else if(typeCourant.equals("tpsDenivele")){
                vitesse = Double.parseDouble(new String(chars, start, length));
            } else if (typeCourant.equals("tpsTrajet")){
                tpsTrajet = Double.parseDouble(new String(chars, start, length));
            }
            else if(typeCourant.equals("type")){
                String type = new String (chars, start, length);
                if(type.equals("V"))
                    typeDescente=TypeDescente.V;
                else if(type.equals("B"))
                    typeDescente=TypeDescente.B;
                else if(type.equals("R"))
                    typeDescente=TypeDescente.R;
                else if(type.equals("N"))
                    typeDescente=TypeDescente.N;
                else if(type.equals("BUS"))
                    typeNavette=TypeNavette.BUS;
                else if(type.equals("METRO"))
                    typeNavette=TypeNavette.METRO;
                else if(type.equals("TK"))
                    typeRemontee=TypeRemontee.TK;
                else if(type.equals("TS"))
                    typeRemontee=TypeRemontee.TS;
                else if(type.equals("TC"))
                    typeRemontee=TypeRemontee.TC;
                else if(type.equals("TSD"))
                    typeRemontee=TypeRemontee.TSD;
                else if(type.equals("TPH"))
                    typeRemontee=TypeRemontee.TPH;
            }
        }
    }

    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {

    }

    @Override
    public void processingInstruction(String s, String s1) throws SAXException {

    }

    @Override
    public void skippedEntity(String s) throws SAXException {

    }
}
