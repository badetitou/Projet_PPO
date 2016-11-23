package ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by badetitou on 23/11/16.
 */
//classe locale pour la vue des messages
class PanelDeMessages extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;
    private TextField xview = new TextField(5);
    private TextField yview = new TextField(5);

    PanelDeMessages() {
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        add(BorderLayout.WEST, pan);
        pan.add(new JLabel("X"));
        xview.setEditable(false);
        pan.add(xview);
        pan.add(new JLabel("Y"));
        yview.setEditable(false);
        pan.add(yview);

        pan.add(new JLabel("NOM LIEU"));
        pan.add(new JTextField());
        pan.add(new JButton("SET DEPART"));
        pan.add(new JTextField());
        pan.add(new JButton("SET ARRIVEE"));
        pan.add(new JCheckBox("TEMPS REEL"));
        pan.add(new JButton("GO"));

        add(BorderLayout.CENTER, new JTextArea());
    }

    // protocole de MouseListener
    // released => selection de coordonnees
    // le reste => rien a faire
    public void mouseReleased(MouseEvent e) {
        int x = e.getX()/Cliqueur.DELTA;
        int y = e.getY()/Cliqueur.DELTA;
        xview.setText(""+x);
        yview.setText(""+y);
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