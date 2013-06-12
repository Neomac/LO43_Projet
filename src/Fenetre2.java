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
	    //JPanel ultima = new JPanel();
	    
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
	    
	    //Création d'onglets
	    onglet1 = new JTabbedPane();
	    //onglet1.add("Chauffeur", pan1);
	    //onglet1.add(pan2);
	    onglet1.add(content);
	    //onglet2 = new JTabbedPane();
	    onglet1.add(pan3);

	    this.getContentPane().add(onglet1, BorderLayout.NORTH);
	    //this.getContentPane().add(onglet2, BorderLayout.CENTER);
	    
	    OuvrirFichier test = new OuvrirFichier();
	    OuvrirFichier tableau = new OuvrirFichier();
	    
	    JPanel pan5 = new JPanel();
	    pan5 = test.OuvrirFichier();
	    
	    onglet2 = new JTabbedPane();
	    onglet2.add(pan5);
	    this.getContentPane().add(onglet2, BorderLayout.SOUTH);
	    onglet3 = new JTabbedPane();
	    //onglet3.add(pan6);
	    
	    //Liste de tâches
	    JTextArea tacheListe = new JTextArea();
	    tacheListe.append(null);
	    
	    //----------------------------------
	    
	    //String fichierSolution = "Solution_3.txt";
	    /*
	    JTable tableau;
	    
	    
	    if(test.getActionSouris() == test.getOpenFile().APPROVE_OPTION){
	    	//String fichierSolution = test.getFile().getName();
	    	Solution testSolution = new Solution();
	    	//testSolution.LectureSolution(fichierSolution);
	    	System.out.println("Coucou Antoine");
	    	
		    //Les données du tableau
		    String[][] data = new String[testSolution.getChauffeurs().size()][2];
	
		    for (int i = 0; i < testSolution.getChauffeurs().size(); i++){
		        data[i][0] = String.valueOf(testSolution.getChauffeurs().get(i).getNumeroChauffeur());
		        data[i][1] = Integer.toString(testSolution.getChauffeurs().get(i).getCost());
		    }
		    
		    tableau = new JTable(data, title);
		    //Les titres des colonnes
		    
		   
		    //this.getContentPane().add(new JScrollPane(tableau));
		  	
		    //----------------------------------
	    }
	    else{
	    	String[][] vide = {
	    			{" "," "}
	    	};
	    	tableau = new JTable(vide, title);
	    	System.out.println("Aurevoir Antoine");
	    }
	    */
	    //String  title[] = {"Numéro Chauffeur", "Cout"};
	    //String[][] vide = {
	    //		{" "," "}
	    //};
	    //JTabbedPane onglet4 = new JTabbedPane();
	    //pan6 = tableau.creerTableau();
	    JTextArea test1 = new JTextArea();
	    pan6.add(test1);
	    //JPanel pan7 = new JPanel();
	    //pan7 = tableau.creerTableau();
	    //onglet4.add(pan6);
	    //onglet4.add(pan7);
	    //pan6.add(test.getTableau());
	    //pan6.add(tableau);
	    //pan6.add(new JScrollPane(tableau));
	    //this.getContentPane().add(pan6, BorderLayout.CENTER);
	    //this.getContentPane().add(onglet4, BorderLayout.CENTER);
	    
	}
	/*
	String fichierSolution = "Solution_1.txt";
    Solution testSolution = new Solution();
    testSolution.LectureSolution(fichierSolution);
 
    //Les données du tableau
    String[][] data = new String[testSolution.getChauffeurs().size()][2];

    for (int i = 0; i < testSolution.getChauffeurs().size(); i++){
        data[i][0] = String.valueOf(testSolution.getChauffeurs().get(i).getNumeroChauffeur());
        data[i][1] = Integer.toString(testSolution.getChauffeurs().get(i).getCost());
    }
 
    //Les titres des colonnes
    String  title[] = {"Numéro Chauffeur", "Cout"};
    JTable tableau = new JTable(data, title);
    this.getContentPane().add(new JScrollPane(tableau));
  }   
 
  public static void main(String[] args){
    FenetreChauffeur fen = new FenetreChauffeur();
    fen.setVisible(true);
  } //Test commit
  */
}
