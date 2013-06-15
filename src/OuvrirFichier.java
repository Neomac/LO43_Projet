import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

public class OuvrirFichier extends JFrame implements ActionListener {
	private JFileChooser OpenFile;
	private File file;
	private int actionSouris; //Lorsque l'on clique sur le bouton
	private JTable tableau;
	private String[][] data;
	private String title[];
	private JTextArea Texte, CoutSolution, NbChauffeur, CoutTotal, Diagramme;
	private JPanel TextSolution;
	private Cadre CoutSolution_c, NbChauffeur_c, CoutTotal_c, ZoneTexte_c;
	
	
	public OuvrirFichier(){
		this.setTitle("Projet LO43");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
		
		/*JPanel*/ TextSolution = new JPanel(); //Panel de la zone affichage de la solution
        JPanel Onglet1 = new JPanel(); //Panel pour l'affichage des solutions et cout
        JPanel Onglet2 = new JPanel(); //Panel pour l'affichage du diagramme de Gantt
        JPanel buttonPanel = new JPanel(); //Panel pour le menu avec le bouton d'ouverture de fichier
        JPanel DiagGantt = new JPanel(); //Panel pour le diagramme de Gantt
        JPanel Content = new JPanel(); //Panel contenant tous les autres
        
        JPanel CoutSolution_p = new JPanel();
        JPanel NbChauffeur_p = new JPanel();
        JPanel CoutTotal_p = new JPanel();
        
        JTabbedPane OngletSolution = new JTabbedPane(); //Onglet qui contient les panels onglet1 et onglet2
        
        Texte = new JTextArea(); //Zone de texte pour l'affichage de la solution
        CoutSolution = new JTextArea(); //Zone de texte pour l'affichage du cout de la solution
        NbChauffeur = new JTextArea(); //Zone de texte pour l'affichage du nombre de chauffeurs
        CoutTotal = new JTextArea(); //Zone de texte pour l'affichage du cout total
        Diagramme = new JTextArea(); //Zone de texte pour l'affichage du diagramme de Gantt
        
		OpenFile = new JFileChooser(); 
		
		/*Cadre*/ CoutSolution_c = new Cadre(); //Cadre pour le cout solution
		/*Cadre*/ NbChauffeur_c = new Cadre(); //Cadre pour le nombre de chauffeur
		/*Cadre*/ CoutTotal_c = new Cadre(); //Cadre pour le cout total
		/*Cadre*/ ZoneTexte_c = new Cadre(); //Cadre pour la zone de texte
			
		CoutSolution_p = CoutSolution_c.CreerCadre_1("Cout de la solution", 150, 150);
		NbChauffeur_p = NbChauffeur_c.CreerCadre_1("Nombre de Chauffeur", 150, 150);
		CoutTotal_p = CoutTotal_c.CreerCadre_1("Cout total", 150, 150);
		TextSolution = ZoneTexte_c.CreerCadre_scrollpane("Affichage de la solution", 200, 400);
		
		DiagGantt.add(Diagramme);
		
		Onglet1.setLayout(new GridLayout(3,1));
		
		Onglet1.add(CoutSolution_p);
		Onglet1.add(NbChauffeur_p);
		Onglet1.add(CoutTotal_p);
		
		Onglet2.add(DiagGantt);
		
		OngletSolution.add(Onglet1, "Affichage des couts");
		OngletSolution.add(Onglet2, "Diagramme de Gantt");
		
		JButton OpenButton = new JButton("Ouvrir un fichier"); //Bouton pour ouvrir un fichier
		OpenButton.addActionListener(this);
        buttonPanel.add(OpenButton);
        
        Border bordNoir = BorderFactory.createLineBorder(Color.GRAY);
        buttonPanel.setBorder(bordNoir);
        
        //Texte.setSize(10, 10);
        //TextSolution.add(new JScrollPane(Texte), BorderLayout.CENTER);
        
        Content.add(buttonPanel, BorderLayout.NORTH);
        Content.add(TextSolution, BorderLayout.WEST);
        Content.add(OngletSolution, BorderLayout.EAST);
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        this.getContentPane().add(TextSolution, BorderLayout.WEST);
        this.getContentPane().add(OngletSolution, BorderLayout.EAST);
               
	};
	
	public JPanel OuvrirFichier(){
		
		JPanel TextSolution = new JPanel(); //Panel de la zone affichage de la solution
        JPanel Onglet1 = new JPanel(); //Panel pour l'affichage des solutions et cout
        JPanel Onglet2 = new JPanel(); //Panel pour l'affichage du diagramme de Gantt
        JPanel buttonPanel = new JPanel(); //Panel pour le menu avec le bouton d'ouverture de fichier
        JPanel DiagGantt = new JPanel(); //Panel pour le diagramme de Gantt
        JPanel Content = new JPanel(); //Panel contenant tous les autres
        
        JPanel CoutSolution_p = new JPanel();
        JPanel NbChauffeur_p = new JPanel();
        JPanel CoutTotal_p = new JPanel();
        
        JTabbedPane OngletSolution = new JTabbedPane(); //Onglet qui contient les panels onglet1 et onglet2
        
        Texte = new JTextArea(10,10); //Zone de texte pour l'affichage de la solution
        CoutSolution = new JTextArea(); //Zone de texte pour l'affichage du cout de la solution
        NbChauffeur = new JTextArea(); //Zone de texte pour l'affichage du nombre de chauffeurs
        CoutTotal = new JTextArea(); //Zone de texte pour l'affichage du cout total
        Diagramme = new JTextArea(); //Zone de texte pour l'affichage du diagramme de Gantt
        
		OpenFile = new JFileChooser(); 
		
		Cadre CoutSolution_c = new Cadre(); //Cadre pour le cout solution
		Cadre NbChauffeur_c = new Cadre(); //Cadre pour le nombre de chauffeur
		Cadre CoutTotal_c = new Cadre(); //Cadre pour le cout total
		
		CoutSolution_p = CoutSolution_c.CreerCadre_1("Cout de la solution", 40, 40);
		NbChauffeur_p = NbChauffeur_c.CreerCadre_1("Nombre de Chauffeur", 40, 40);
		CoutTotal_p = CoutTotal_c.CreerCadre_1("Cout total", 40, 40);
		
		DiagGantt.add(Diagramme);
		
		Onglet1.add(CoutSolution_p, BorderLayout.NORTH);
		Onglet1.add(NbChauffeur_p, BorderLayout.CENTER);
		Onglet1.add(CoutTotal_p, BorderLayout.SOUTH);
		
		Onglet2.add(DiagGantt);
		
		OngletSolution.add(Onglet1, "Affichage des couts");
		OngletSolution.add(Onglet2, "Diagramme de Gantt");
		
		JButton OpenButton = new JButton("Ouvrir un fichier"); //Bouton pour ouvrir un fichier
		OpenButton.addActionListener(this);
		 
        buttonPanel.add(OpenButton);
        
        //Texte.setSize(getMaximumSize());
        TextSolution.add(new JScrollPane(Texte), BorderLayout.CENTER);
        
        Content.add(buttonPanel, BorderLayout.NORTH);
        Content.add(TextSolution, BorderLayout.WEST);
        Content.add(OngletSolution, BorderLayout.EAST);
        
        return Content;        
	}
	
	public JTabbedPane OuvrirFichier1(){
		JTabbedPane onglet = new JTabbedPane();
		JPanel temp = new JPanel();
		OpenFile = new JFileChooser();
		JButton OpenButton = new JButton("Ouvrir un fichier");
		OpenButton.addActionListener(this);
		JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(OpenButton);
        Texte = new JTextArea(10, 50);
        //Texte.setSize(50,50);
        temp.add(new JScrollPane(Texte));
        onglet.add(buttonPanel);
        onglet.add(temp);
        return onglet;
	}
	/*
	public JPanel AffichageFichier(){
		JPanel temp = new JPanel();
		Texte = new JTextArea(10,10);
        temp.add(new JScrollPane(Texte), BorderLayout.CENTER);
        return temp;
	}*/
	
	public JPanel creerTableau(){
		JPanel temp = new JPanel();
		String  title[] = {"Numéro Chauffeur", "Cout"};
		String[][] vide = {
    			{"test ","test "}
    	};
    	tableau = new JTable(vide, title);
    	temp.add(tableau);
        return temp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		actionSouris = OpenFile.showOpenDialog(OuvrirFichier.this);
		file = OpenFile.getSelectedFile();
		System.out.println("test");
		//------------------------------------------
		//JTable tableau;
		 String  title[] = {"Numéro Chauffeur", "Cout"};
		if (actionSouris == JFileChooser.APPROVE_OPTION) {
			 
			    	String fichierSolution = file.getName();
			    	Solution testSolution = new Solution();
			    	testSolution.LectureSolution(fichierSolution);
			    	System.out.println("Coucou Antoine");
			    	
			    	ZoneTexte_c.getTexte().append("Chauffeur | Cout" + "\n");
			    	
			    	for(int i = 0; i < testSolution.getChauffeurs().size();i++){
			    		//Texte.append(" " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() +"   |   " + testSolution.getChauffeurs().get(i).getCost()+  "\n");
			    		ZoneTexte_c.getTexte().append("   " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() +"   |   " + testSolution.getChauffeurs().get(i).getCost()+  "\n");
			    	}
			    	
				    //Les données du tableau
				    /*String[][]*/ data = new String[testSolution.getChauffeurs().size()][2];
			
				    for (int i = 0; i < testSolution.getChauffeurs().size(); i++){
				        data[i][0] = String.valueOf(testSolution.getChauffeurs().get(i).getNumeroChauffeur());
				        data[i][1] = Integer.toString(testSolution.getChauffeurs().get(i).getCost());
				        System.out.println("COucou");
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
		}
		//------------------------------------------
	
	
	public JTable getTableau() {
		return tableau;
	}
	public File getFile() {
		return file;
	}
	
	public int getActionSouris() {
		return actionSouris;
	}
	
	public JFileChooser getOpenFile() {
		return OpenFile;
	}
}
