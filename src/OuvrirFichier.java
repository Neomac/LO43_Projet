import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OuvrirFichier extends JFrame implements ActionListener {
	private JFileChooser OpenFile;
	private File file;
	private int actionSouris; //Lorsque l'on clique sur le bouton
	private JTable tableau;
	private String[][] data;
	private String title[];
	private JTextArea Texte, CoutSolution, NbChauffeur, CoutTotal, Diagramme;
	private JPanel TextSolution;
	private Cadre CoutSolution_c, NbChauffeur_c, CoutTotal_c, ZoneTexte_c, NbTache_c, TypeService_c,
				  TypeService1_c, NbTacheCh_c, WorkerTime_c, UnderTime_c, OverTime_c, IdleTime_c, Cost_c;
	private JComboBox Chauffeur_cb;
	private Solution testSolution;
    private JPanel Onglet2;
	
	int marqueur = 0;
	
	
	public OuvrirFichier(){
		this.setTitle("Projet LO43");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
		
		/*JPanel*/ TextSolution = new JPanel(); //Panel de la zone affichage de la solution
		
		//---------Liste des onglets ------------------------\\
        JPanel Onglet1 = new JPanel(); //Panel pour l'affichage des solutions et cout
        JPanel Onglet2 = new JPanel(); //Panel pour l'affichage du diagramme de Gantt
        JPanel Onglet3 = new JPanel(); //Panel pour l'affichage du d�tail par chauffeur
        JPanel Onglet4 = new JPanel(); //Panel pour l'affichage du d�tail par t�che
        
        JPanel buttonPanel = new JPanel(); //Panel pour le menu avec le bouton d'ouverture de fichier
        JPanel DiagGantt = new JPanel(); //Panel pour le diagramme de Gantt
        JPanel Content = new JPanel(); //Panel contenant tous les autres
        
        JPanel CoutSolution_p = new JPanel();
        JPanel NbChauffeur_p = new JPanel();
        JPanel CoutTotal_p = new JPanel();
        JPanel NbTache_p = new JPanel();
        JPanel TypeService_p = new JPanel();

        //Déclaration des panels pour l'onglet 2
        JPanel DiagGantt_ = new JPanel();
        
        //---------D�claration des panels pour l'onglet 3--------\\
        JPanel TypeService1_p = new JPanel();
        JPanel NbTacheCh_p = new JPanel();
        JPanel WorkerTime_p = new JPanel();
        JPanel UnderTime_p = new JPanel();
        JPanel OverTime_p = new JPanel();
        JPanel IdleTime_p = new JPanel();
        JPanel Cost_p = new JPanel();
        
        
        //---------Declaration des onglets pour l'onglet 4 -----------\\
        JPanel HeureDepart_p = new JPanel();
        JPanel HeureArrivee_p = new JPanel();
        JPanel LieuDepart_p = new JPanel();
        JPanel LieuArrivee_p = new JPanel();
        JPanel ChauffeurAssocie_p = new JPanel();
        
        JTabbedPane OngletSolution = new JTabbedPane(); //Onglet qui contient les panels onglet1 et onglet2
        
        Texte = new JTextArea(); //Zone de texte pour l'affichage de la solution
        CoutSolution = new JTextArea(); //Zone de texte pour l'affichage du cout de la solution
        NbChauffeur = new JTextArea(); //Zone de texte pour l'affichage du nombre de chauffeurs
        CoutTotal = new JTextArea(); //Zone de texte pour l'affichage du cout total
        Diagramme = new JTextArea(); //Zone de texte pour l'affichage du diagramme de Gantt
        
		OpenFile = new JFileChooser(); 
		
		//--------Cadres pour l'onglet 1 ---------------------\\
		/*Cadre*/ CoutSolution_c = new Cadre(); //Cadre pour le cout solution
		/*Cadre*/ NbChauffeur_c = new Cadre(); //Cadre pour le nombre de chauffeur
		/*Cadre*/ CoutTotal_c = new Cadre(); //Cadre pour le cout total
		NbTache_c = new Cadre(); //Cadre pour le nombre de t�ches
		TypeService_c = new Cadre(); //Cadre pour le type de service
		
		//-------Cadres pour l'onglet 2 --------------------\\
		TypeService1_c = new Cadre();
		NbTacheCh_c = new Cadre();
		WorkerTime_c = new Cadre();
		UnderTime_c = new Cadre();
		OverTime_c = new Cadre();
		IdleTime_c = new Cadre();
		Cost_c = new Cadre();
		
		//--------Cadres pour l'onglet 4 -------------------\\
		HeureDepart_c = new Cadre();
		HeureArrivee_c = new Cadre();
		LieuDepart_c = new Cadre();
		LieuArrivee_c = new Cadre();
		ChauffeurAssocie_c = new Cadre();
		
		/*Cadre*/ ZoneTexte_c = new Cadre(); //Cadre pour la zone de texte
		
		//--------D�claration des cadres pour l'onglet 1 -------------------\\
		CoutSolution_p = CoutSolution_c.CreerCadre_1("Cout de la solution", 100, 100);
		NbChauffeur_p = NbChauffeur_c.CreerCadre_1("Nombre de Chauffeur", 100, 100);
		CoutTotal_p = CoutTotal_c.CreerCadre_1("Cout total", 100, 100);
		NbTache_p = NbTache_c.CreerCadre_1("Nombre de taches", 100, 100);
		TypeService_p = TypeService_c.CreerCadre_1("Type de service", 100, 100);
		
		//-------D�claration des cadres pour l'onglet 3 ---------------------\\
		TypeService1_p = TypeService1_c.CreerCadre_1("Type de service", 100, 100);
		NbTacheCh_p = NbTacheCh_c.CreerCadre_1("Nombre de taches", 100, 100);
		WorkerTime_p = WorkerTime_c.CreerCadre_1("Temps de travail", 100, 100);
		UnderTime_p = UnderTime_c.CreerCadre_1("Temps de travail", 100, 100);
		OverTime_p = OverTime_c.CreerCadre_1("Temps de travail", 100, 100);
		Cost_p = Cost_c.CreerCadre_1("Cout", 150, 150);
		
		//D�claration de la combobox de l'onglet 3
		Chauffeur_cb = new JComboBox();
		Chauffeur_cb.addItem("0");
		TextSolution = ZoneTexte_c.CreerCadre_scrollpane("Affichage de la solution", 200, 400);

        JPanel temp = new JPanel();
        //final DiagGantt demo = new DiagGantt("Diagramme de Gantt des Chauffeurs");
        DiagGantt chart1 = new DiagGantt("Test");
        temp = chart1.creationChart();
        //demo.pack();
        //demo.setVisible(true);
        //temp.add(chart1);
        //IntervalCategoryDataset dataset;
        //dataset.createDataset();
        //JFreeChart chart = createChart(dataset);
        //ChartPanel CP = new ChartPanel(demo);

		//DiagGantt.add(Diagramme);
        DiagGantt.add(temp);
		
        //-----------Declaration des cadres pour l'onglet 4 --------------\\
        HeureDepart_p = HeureDepart_c.CreerCadre_1("Heure de depart", 100, 100);
        HeureArrivee_p = HeureArrivee_c.CreerCadre_1("Heure d'arrivee", 100, 100);
        LieuDepart_p = LieuDepart_c.CreerCadre_1("Lieu de depart", 100, 100);
        LieuArrivee_p = LieuArrivee_c.CreerCadre_1("Lieu d'arrivee", 100, 100);
        ChauffeurAssocie_p = ChauffeurAssocie_c.CreerCadre_1("Chauffeur associe a cette tache", 100, 100);
        //-----------Declaration de la combo box de l'onglet 4 -----------\\
        Tache_cb = new JComboBox();
        Tache_cb.addItem("Pas de tache.");
        
        
		Onglet1.setLayout(new GridLayout(3,2));
		
		Onglet1.add(CoutSolution_p);
		Onglet1.add(NbChauffeur_p);
		Onglet1.add(CoutTotal_p);
		Onglet1.add(NbTache_p);
		Onglet1.add(TypeService_p);
		
		Onglet2.add(DiagGantt);
		
		Onglet3.setLayout(new GridLayout(3,3));
		Onglet3.add(Chauffeur_cb);
		Onglet3.add(TypeService1_p);
		Onglet3.add(NbTacheCh_p);
		Onglet3.add(WorkerTime_p);
		Onglet3.add(UnderTime_p);
		Onglet3.add(OverTime_p);
		Onglet3.add(Cost_p);
		
		Onglet4.add(HeureDepart_p);
		Onglet4.add(HeureArrivee_p);
		Onglet4.add(LieuDepart_p);
		Onglet4.add(LieuArrivee_p);
		Onglet4.add(ChauffeurAssocie_p);
		
		OngletSolution.add(Onglet1, "Affichage des couts");
		OngletSolution.add(Onglet2, "Diagramme de Gantt");
		OngletSolution.add(Onglet3, "Detail par chauffeur");
		OngletSolution.add(Onglet4, "Detail par tache");
		
		JButton button = new JButton("Ouvrir un fichier");
		try {
			Image img = ImageIO.read(getClass().getResource("C:/Users/arnaudv/git/LO43_Projet/badge.jpg"));
		    button.setIcon(new ImageIcon(img));
		} catch (IOException ex) {
		}
		button.addActionListener(this);
		buttonPanel.add(button);
		
		JButton OpenButton = new JButton("Ouvrir un fichier"); //Bouton pour ouvrir un fichier
		OpenButton.addActionListener(this);
        buttonPanel.add(OpenButton);
        
        Border bordNoir = BorderFactory.createLineBorder(Color.GRAY);
        buttonPanel.setBorder(bordNoir);
        
        //Interraction avec la combo Box
        //NbTache_c.getTexte().append(" " +testSolution.getChauffeur(Integer.parseInt((String)Chauffeur_cb.getSelectedItem())).getCost());
        Chauffeur_cb.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	if(marqueur == 1){
	            	int test = 0;
	            	test = Chauffeur_cb.getSelectedIndex();
	            	
	            	//R�initialisation des champs de texte
	            	NbTacheCh_c.getTexte().setText("");
	            	Cost_c.getTexte().setText("");
	            	TypeService1_c.getTexte().setText("");
	            	WorkerTime_c.getTexte().setText("");
	        
	            	NbTacheCh_c.getTexte().append(" " + testSolution.getChauffeur(test).getNombreTaches());
	            	Cost_c.getTexte().append(" "+testSolution.getChauffeur(test).getCost());
	            	TypeService1_c.getTexte().append(" " + testSolution.getChauffeur(test).PrintService());
	            	WorkerTime_c.getTexte().append(" "+ testSolution.getChauffeur(test).getWorkerTimeSum());
            	}
            }
        });
        
        Tache_cb.addActionListener(new ActionListener () {
        	public void actionPerformed(ActionEvent e){
        		if(marqueur == 1){
        			int temp = 0;
        			temp = Tache_cb.getSelectedIndex(); //Recuperation de l'information de la comboBox
        			
        			//Reinitialisation des champs de texte
        			HeureDepart_c.getTexte().setText("");
        			HeureArrivee_c.getTexte().setText("");
        			LieuDepart_c.getTexte().setText("");
        			LieuArrivee_c.getTexte().setText("");
        			ChauffeurAssocie_c.getTexte().setText("");
        			
        			//Renseignement des champs apres selection d'un onglet
        			HeureDepart_c.getTexte().append(" " );
        			HeureArrivee_c.getTexte().append("");
        			LieuDepart_c.getTexte().append("");
        			LieuArrivee_c.getTexte().append("");
        			ChauffeurAssocie_c.getTexte().append("");
        		}
        	}
        });
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
		String  title[] = {"Num�ro Chauffeur", "Cout"};
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
		marqueur = 0;
		//System.out.println("test");
		//------------------------------------------
		 String  title[] = {"Num�ro Chauffeur", "Cout"};
		if (actionSouris == JFileChooser.APPROVE_OPTION) {
			 
			//Reinitialisation des champs de parametres des zones de texte
			ZoneTexte_c.getTexte().setText(" ");
			CoutSolution_c.getTexte().setText("");
			NbChauffeur_c.getTexte().setText("");
			NbTache_c.getTexte().setText("");
			TypeService_c.getTexte().setText("");
			
			
			    	String fichierSolution = file.getName();
			    	//Solution testSolution = new Solution();
			    	testSolution = new Solution();
			    	testSolution.LectureSolution(fichierSolution);
			    	System.out.println("Coucou Antoine");
			    	
			    	ZoneTexte_c.getTexte().append("Chauffeur | Cout" + "\n");
			    	CoutSolution_c.getTexte().append(" " + testSolution.getCoutTotal() + " ");
			    	NbChauffeur_c.getTexte().append(" " + testSolution.getNombreChauffeurs() + " ");
			    	NbTache_c.getTexte().append(" " + testSolution.getTotalTaches() + " ");
			    	TypeService_c.getTexte().append("Service du matin : " + testSolution.getServiceMatin() + "\n" +
			    									"Service de jour  : " + testSolution.getServiceJour()  + "\n" +
			    									"Service du soir  : " + testSolution.getServiceSoir()  + "\n" +
			    									"Service de nuit  : " + testSolution.getServiceNuit()  + "\n");
			    	
			    	
			    	Chauffeur_cb.removeAllItems();
			    	Chauffeur_cb.repaint();
			    	for(int i = 0; i < testSolution.getNombreChauffeurs(); i++){
			    		Chauffeur_cb.addItem("Chauffeur n�" + testSolution.getChauffeur(i).getNumeroChauffeur());
			    	}
			    	
			    	Tache_cb.removeAllItems(); // Nettoie la combo box
			    	for(int i = 0; i < testSolution.getTotalTaches(); i++){
			    		Tache_cb.addItem("Tache n" ); //Rajouter le nombre de taches
			    	}
			    	
			    	for(int i = 0; i < testSolution.getChauffeurs().size();i++){
			    		//Texte.append(" " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() +"   |   " + testSolution.getChauffeurs().get(i).getCost()+  "\n");
			    		ZoneTexte_c.getTexte().append("   " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() +"   |   " + testSolution.getChauffeurs().get(i).getCost()+  "\n");
			    	}

                JPanel DiagGantt = new JPanel(); //Panel pour le diagramme de Gantt
                JPanel temp = new JPanel();
                DiagGantt chart1 = new DiagGantt("Test", fichierSolution);
                temp = chart1.creationChart(fichierSolution);
                DiagGantt.add(temp);
                Onglet2.repaint();

			    	
			    	marqueur = 1;
			    }
		else{
			ZoneTexte_c.getTexte().append("Erreur d'ouverture du fichier\n");
			//ZoneTexte_c.getTexte().setText(""); t
		}
		//Interraction avec la combo Box
        //NbTache_c.getTexte().append(" " +testSolution.getChauffeur(Integer.parseInt((String)Chauffeur_cb.getSelectedItem())).getCost());
        
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
