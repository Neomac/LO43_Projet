import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;


public class Fenetre2 extends JFrame{
	private JTabbedPane onglet1, onglet3;
	private JTabbedPane onglet2;
	private File file;
	
	public Fenetre2(){
		this.setTitle("Projet LO43");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    
	    JPanel pan1 = new JPanel();
	    JPanel pan2 = new JPanel();
	    JPanel pan3 = new JPanel();
	    JPanel pan4 = new JPanel();
	    JPanel content = new JPanel();
	    
	    JPanel pan6 = new JPanel();
	    
	    Cadre chauffeur = new Cadre();
	    Cadre cout = new Cadre();
	    Cadre coutTotal = new Cadre();
	    Cadre nbTache = new Cadre();
	    Cadre listeChauffeur = new Cadre();
	    
	    pan1 = chauffeur.CreerCadre_1("Chauffeur", 150, 150);
	    pan2 = cout.CreerCadre_1("Cout", 150, 150);
	    pan3 = coutTotal.CreerCadre_1("coutTotal", 150, 150);
	    pan4 = nbTache.CreerCadre_1("nbTache", 150, 150);
	    pan6 = listeChauffeur.CreerCadre_1("Liste des chauffeurs", 300, 300);
	    
	    content.add(pan1);
	    content.add(pan2);
	    content.add(pan3);
	    content.add(pan4);
	    this.getContentPane().add(content, BorderLayout.SOUTH);
	    
	    //Cr�ation d'onglets
	    onglet1 = new JTabbedPane();
	    onglet1.add(content);
	    onglet1.add(pan3);

	    this.getContentPane().add(onglet1, BorderLayout.NORTH);
	    
	    OuvrirFichier test = new OuvrirFichier();
	    OuvrirFichier lecture = new OuvrirFichier();
	    
	    JPanel pan5 = new JPanel();
	    pan5 = test.OuvrirFichier();
	    //JPanel pan7 = new JPanel();
	    //pan7 = lecture.AffichageFichier();
	    
	    //this.getContentPane().add(pan7, BorderLayout.CENTER);
	    JTabbedPane ongletFichier = new JTabbedPane();
	    ongletFichier = lecture.OuvrirFichier1();
	    this.getContentPane().add(ongletFichier, BorderLayout.SOUTH);
	    onglet2 = new JTabbedPane();
	    onglet2.add(pan5);
	    //this.getContentPane().add(onglet2, BorderLayout.SOUTH);
	    onglet3 = new JTabbedPane();
	    //onglet3.add(pan6);
	    
	    //Liste de t�ches
	    JTextArea tacheListe = new JTextArea();
	    tacheListe.append(null);
	    
	    JTextArea test1 = new JTextArea();
	    pan6.add(test1); //
	    
	}

}
