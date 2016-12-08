package ihm;

import solver.*;
import solver.Point;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * Created by badetitou on 23/11/16.
 */
//classe locale pour la vue des messages
class PanelDeMessages extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;
    private JTextField xview = new JTextField(5);
    private JTextField yview = new JTextField(5);
    private JTextField nomDepart = new JTextField();
    private JTextField nomArrivee = new JTextField();
    private JTextField nomLieu = new JTextField();
    private JButton setDepart = new JButton("SET DEPART");
    private JButton setArrivee = new JButton("SET ARRIVEE");
    private JCheckBox tempsReel = new JCheckBox("TEMPS REEL");
    private JButton valide = new JButton("GO");
    private JTextArea messagePanel = new JTextArea();

    private Station station;
    private Point depart;
    private Point arrivee;

    PanelDeMessages(Station station) {
        initComponant();
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        add(BorderLayout.WEST, pan);
        pan.add(new JLabel("X"));
        pan.add(xview);
        pan.add(new JLabel("Y"));
        pan.add(yview);
        pan.add(new JLabel("NOM LIEU"));
        pan.add(nomLieu);
        pan.add(setDepart);
        pan.add(nomDepart);
        pan.add(setArrivee);
        pan.add(nomArrivee);
        pan.add(tempsReel);
        pan.add(valide);
        add(BorderLayout.CENTER, messagePanel);

        this.station = station;
    }

    private void initComponant(){
        xview.setEditable(false);
        yview.setEditable(false);
        nomDepart.setEditable(false);
        nomArrivee.setEditable(false);
        nomLieu.setEditable(false);
        messagePanel.setEditable(false);

        tempsReel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Station.TEMPS_REEL = !Station.TEMPS_REEL;
            }
        });

        setDepart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    depart = station.getPoint(Integer.parseInt(xview.getText()), Integer.parseInt(yview.getText()));
                    nomDepart.setText(depart.toString());
                } catch (Solver.NoPointException e1){
                    e1.printStackTrace();
                }
            }
        });

        setArrivee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    arrivee = station.getPoint(Integer.parseInt(xview.getText()), Integer.parseInt(yview.getText()));
                    nomArrivee.setText(arrivee.toString());
                } catch (Solver.NoPointException e1){
                    e1.printStackTrace();
                }
            }
        });

        valide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (depart.equals(arrivee)){
                    messagePanel.setText("Vous etes deja arrive a destination");
                } else {
                    Solver solver = new Solver();
                    ResultCalculTemps resultCalculTemps = solver.calculTemps(depart, arrivee, station);
                    if(resultCalculTemps.getTypeResult() == TypeResult.NoWayFound){
                        setResultTextAreaNoWay();
                    } else if (resultCalculTemps.getTypeResult() == TypeResult.SamePoints){
                        setResultTextSamePoints();
                    } else {
                        setResultTextWayFound(resultCalculTemps.getTransitionList());
                    }
                }
            }
        });
    }

    private void setResultTextWayFound(List<Transition> transitionList){
        double temps = 0.0;
        double denivele = 0.0;
        messagePanel.setText("");
        messagePanel.append("Plus court chemin entre les points " + depart + " et " + arrivee + "\n");

        for (Transition transition : transitionList) {
            messagePanel.append("  Transition numero : " + transition.getNumero() + " nom : " + transition.getNom() +
                    " depuis : " + transition.getDepart() + " vers : " + transition.getArrivee() +
                    " duree : " + transition.temps() + "\n");
            temps += transition.temps();
            denivele += transition.denivele();
        }
        messagePanel.append("Duree du trajet : " + temps + " secondes, soit " + ((int) (temps / 3600)) + "h" + ((int) (temps % 3600) / 60)
                + "mn" + ((int) (temps % 3600) % 60) + "s\n");
        messagePanel.append("Cumul des deniveles : " + denivele + "m");

    }

    private void setResultTextSamePoints(){
        messagePanel.setText("Vous etes deja arrive");
    }

    private void setResultTextAreaNoWay(){
        messagePanel.setText("Il n'y a pas de chemin entre les deux points");
    }

    // protocole de MouseListener
    // released => selection de coordonnees
    // le reste => rien a faire
    public void mouseReleased(MouseEvent e) {
        int x = e.getX()/Cliqueur.DELTA;
        int y = e.getY()/Cliqueur.DELTA;
        xview.setText(""+x);
        yview.setText(""+y);
        try {
            nomLieu.setText(station.getPoint(x,y).toString());
        } catch (Solver.NoPointException e1) {
            nomLieu.setText("");
        };
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}