//usage : java ihm.Cliqueur fichierimage.jpg


package ihm;

import parseur.CParser;
import solver.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Cliqueur extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static final int DELTA = 20;
	int hauteurPlan, largeurPlan;
	// pour la vue scrollable de l'image du plan des pistes
	protected ImageCanvas canvas = new ImageCanvas();
	protected ScrollPane mapView = new ScrollPane();
	// pour les messages et interactions avec l'utilisateur
	protected PanelDeMessages msgView;
	
	public Cliqueur (String fichierImage, Station station) {
		//chargement de l'image
        msgView = new PanelDeMessages(station);

		Image im= new ImageIcon(fichierImage).getImage();
		hauteurPlan = im.getHeight(null);
		largeurPlan = im.getWidth(null);
		// preparation de la vue scrollable de l'image du plan des pistes
		canvas.setImage(im);
		canvas.addMouseListener(msgView);
		mapView.setSize(hauteurPlan+DELTA,largeurPlan+DELTA);
		mapView.add(canvas);
		// construction de l'ensemble
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(mapView, gbc);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(msgView, gbc);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		setTitle("Cliqueur");
		setSize(700,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	//classe locale de canvas
	class ImageCanvas extends Canvas {
		private static final long serialVersionUID = 1L;
		Image image;
		
		void setImage(Image img) {
			image=img;
			setSize(largeurPlan, hauteurPlan);
			repaint();
		}
		public void paint(Graphics g) {
			// image
			//if (image!=null) g.drawImage(image,DELTA,DELTA,hauteurPlan,largeurPlan,this);
			if (image!=null) g.drawImage(image,DELTA,DELTA,this);
			// quadrillage
			int lignes = hauteurPlan/DELTA;
			int colonnes = largeurPlan/DELTA;
			g.setColor(Color.gray);
			for (int i = 1; i <= lignes; i++) {
				g.drawString(""+i, 0, (i+1)*DELTA);
				g.drawLine(DELTA, i*DELTA, DELTA+largeurPlan, i*DELTA);
			}
			g.drawLine(DELTA, (lignes+1)*DELTA, DELTA+largeurPlan, (lignes+1)*DELTA);
			for (int i = 1; i <= colonnes; i++) {
				g.drawString(""+i, i*DELTA, DELTA/2);
				g.drawLine(i*DELTA, DELTA, i*DELTA, DELTA+hauteurPlan);
			}
			g.drawLine((colonnes+1)*DELTA, DELTA, (colonnes+1)*DELTA, DELTA+hauteurPlan);
		}
	}
	
    //lancement de l'IHM
    public static void main(String argv[]) {
		if (argv.length != 1)
			System.err.println("usage : java java ihm.Cliqueur fichiermage.jpg");
		else {
			Station station = new Station();
			CParser cparser = new CParser(station);
			/**
			 * Chargement de la station
			 */
			cparser.run("ressources/station.xml");

            Cliqueur w = new Cliqueur(argv[0], station);
		}
	}
}