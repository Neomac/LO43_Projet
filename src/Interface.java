import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Interface extends JFrame implements ActionListener {
	private JFileChooser OpenFile;
	private File file;
	private int actionSouris; //Lorsque l'on clique sur le bouton recherche fichier
    private JTextArea Texte, CoutSolution, NbChauffeur, CoutTotal, Diagramme;
	private JPanel TextSolution;
	private Cadre CoutSolution_c, NbChauffeur_c, ZoneTexte_c, NbTache_c, TypeService_c,
				  TypeService1_c, NbTacheCh_c, WorkerTime_c, HeureChauffeurDepart_c, HeureChauffeurArrivee_c, Cost_c,
				  HeureDepart_c, HeureArrivee_c, LieuDepart_c, LieuArrivee_c, ChauffeurAssocie_c;
	private JComboBox Chauffeur_cb, Tache_cb;
	private Solution testSolution;
    private JCheckBox TexteSimplifie, TexteComplet;
    private String nomFichier;
    private JButton DiagGantt;
	
	int marqueur = 0;
    int numeroChauffeur = 0;
	
	
	public Interface(){
		this.setTitle("Projet LO43");
		this.setSize(620,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(620,600));

		
		TextSolution = new JPanel(); //Panel de la zone affichage de la solution
		
		//---------Liste des onglets ------------------------\\
        JPanel Onglet1 = new JPanel(new BorderLayout()); //Panel pour l'affichage des solutions et cout
        JPanel Onglet3 = new JPanel(new BorderLayout()); //Panel pour l'affichage du d�tail par chauffeur
        JPanel Onglet4 = new JPanel(new BorderLayout()); //Panel pour l'affichage du d�tail par t�che
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); //Panel pour le menu avec le bouton d'ouverture de fichier
        JPanel Content = new JPanel(); //Panel contenant tous les autres
        
        JPanel CoutSolution_p = new JPanel();
        JPanel NbChauffeur_p = new JPanel();
        JPanel NbTache_p = new JPanel();
        JPanel TypeService_p = new JPanel();
        
        //---------Declaration des panels pour l'onglet 3--------\\
        JPanel TypeService1_p = new JPanel();
        JPanel NbTacheCh_p = new JPanel();
        JPanel WorkerTime_p = new JPanel();
        JPanel HeureChauffeurDepart_p = new JPanel();
        JPanel HeureChauffeurArrivee_p = new JPanel();
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
		CoutSolution_c = new Cadre(); //Cadre pour le cout solution
		NbChauffeur_c = new Cadre(); //Cadre pour le nombre de chauffeur
		NbTache_c = new Cadre(); //Cadre pour le nombre de t�ches
		TypeService_c = new Cadre(); //Cadre pour le type de service
		
		//-------Cadres pour l'onglet 3 --------------------\\
		TypeService1_c = new Cadre();
		NbTacheCh_c = new Cadre();
		WorkerTime_c = new Cadre();
		HeureChauffeurDepart_c = new Cadre();
		HeureChauffeurArrivee_c = new Cadre();
		Cost_c = new Cadre();
		
		//--------Cadres pour l'onglet 4 -------------------\\
		HeureDepart_c = new Cadre();
		HeureArrivee_c = new Cadre();
		LieuDepart_c = new Cadre();
		LieuArrivee_c = new Cadre();
		ChauffeurAssocie_c = new Cadre();
		
		ZoneTexte_c = new Cadre(); //Cadre pour la zone de texte
		
		//--------D�claration des cadres pour l'onglet 1 -------------------\\
		CoutSolution_p = CoutSolution_c.CreerCadre_1("Cout de la solution", 100, 100);
		NbChauffeur_p = NbChauffeur_c.CreerCadre_1("Nombre de Chauffeur", 100, 100);
		NbTache_p = NbTache_c.CreerCadre_1("Nombre de taches", 100, 100);
		TypeService_p = TypeService_c.CreerCadre_1("Type de service", 100, 100);
		
		//-------D�claration des cadres pour l'onglet 3 ---------------------\\
		TypeService1_p = TypeService1_c.CreerCadre_1("Type de service", 100, 100);
		NbTacheCh_p = NbTacheCh_c.CreerCadre_1("Nombre de taches", 100, 100);
		WorkerTime_p = WorkerTime_c.CreerCadre_1("Temps de travail", 100, 100);
		HeureChauffeurDepart_p = HeureChauffeurDepart_c.CreerCadre_1("Heure de depart", 100, 100);
		HeureChauffeurArrivee_p = HeureChauffeurArrivee_c.CreerCadre_1("Heure d'arrivee", 100, 100);
		Cost_p = Cost_c.CreerCadre_1("Cout", 100, 100);
		
		//D�claration de la combobox de l'onglet 3
		Chauffeur_cb = new JComboBox();
		Chauffeur_cb.addItem("Pas de chauffeur.");
		
		TextSolution = ZoneTexte_c.CreerCadre_scrollpane("Affichage de la solution", 300, 400);

        //-----------Declaration des cadres pour l'onglet 4 --------------\\
        HeureDepart_p = HeureDepart_c.CreerCadre_1("Heure de depart", 100, 100);
        HeureArrivee_p = HeureArrivee_c.CreerCadre_1("Heure d'arrivee", 100, 100);
        LieuDepart_p = LieuDepart_c.CreerCadre_1("Lieu de depart", 100, 100);
        LieuArrivee_p = LieuArrivee_c.CreerCadre_1("Lieu d'arrivee", 100, 100);
        ChauffeurAssocie_p = ChauffeurAssocie_c.CreerCadre_1("Chauffeur associe a cette tache", 100, 100);
        //-----------Declaration de la combo box de l'onglet 4 -----------\\
        Tache_cb = new JComboBox();
        Tache_cb.addItem("Pas de tache.");

        Container pane4 = new Container();
        pane4.setLayout(new BoxLayout(pane4, BoxLayout.Y_AXIS));
        pane4.add(Tache_cb);
        pane4.add(HeureDepart_p);
        pane4.add(HeureArrivee_p);
        pane4.add(LieuDepart_p);
        pane4.add(LieuArrivee_p);
        pane4.add(ChauffeurAssocie_p);

        Container pane1 = new Container();
        pane1.setLayout(new BoxLayout(pane1, BoxLayout.Y_AXIS));
        pane1.add(CoutSolution_p);
        pane1.add(NbChauffeur_p);
        pane1.add(NbTache_p);
        pane1.add(TypeService_p);

        JPanel Heure_p = new JPanel(new FlowLayout());
        Heure_p.add(HeureChauffeurDepart_p);
        Heure_p.add(HeureChauffeurArrivee_p);

        DiagGantt = new JButton("Generer diagramme de Gantt");

        DiagGantt.setEnabled(false);

        DiagGantt.setIcon(new ImageIcon(".\\open-source-icons\\PNG\\purple\\bar-chart.png"));
        DiagGantt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                Gantt diag = new Gantt(nomFichier,numeroChauffeur);
            }
        });

        Container pane3 = new Container();
        pane3.setLayout(new BoxLayout(pane3, BoxLayout.Y_AXIS));
        DiagGantt.setAlignmentX(pane3.CENTER_ALIGNMENT);
        pane3.add(Chauffeur_cb);
        pane3.add(TypeService1_p);
        pane3.add(NbTacheCh_p);
        pane3.add(WorkerTime_p);
        pane3.add(HeureChauffeurDepart_p);
        pane3.add(HeureChauffeurArrivee_p);
        pane3.add(Cost_p);
        pane3.add(DiagGantt);




        Onglet3.add(pane3);

        Onglet4.add(pane4);
        Onglet1.add(pane1);

		OngletSolution.add(Onglet1, "Affichage de la solution");
		OngletSolution.add(Onglet3, "Details par chauffeur");
		OngletSolution.add(Onglet4, "Details par tache");

		JButton OpenButton = new JButton("Ouvrir un fichier"); //Bouton pour ouvrir un fichier
		//OpenButton.setIcon(new ImageIcon("C:\\Users\\Simon\\workspace\\LO43_Projet\\open-source-icons\\PNG\\purple\\add-item.png"));
        OpenButton.setIcon(new ImageIcon(".\\open-source-icons\\PNG\\purple\\add-item.png"));
        OpenButton.addActionListener(this);
        buttonPanel.add(OpenButton);

        TexteComplet = new JCheckBox("Affichage complet");
        TexteSimplifie = new JCheckBox("Affichage simplifie");
        TexteComplet.setEnabled(false);
        TexteSimplifie.setEnabled(false);
        TexteSimplifie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TexteComplet.isSelected()){
                    TexteComplet.setSelected(false);
                }
                if(marqueur == 1){
                    ZoneTexte_c.getTexte().setText("");
                    ZoneTexte_c.getTexte().append("Chauffeur | Cout ");
                    for(int i = 0; i < testSolution.getChauffeurs().size();i++){
                        ZoneTexte_c.getTexte().append("  " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() +"   |   " + testSolution.getChauffeurs().get(i).getCost()+  "\n");
                    }
                }
            }
        });
        TexteComplet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TexteSimplifie.isSelected()){
                    TexteSimplifie.setSelected(false);
                }
                if(marqueur == 1){
                    ZoneTexte_c.getTexte().setText("");
                    ZoneTexte_c.getTexte().append("" + testSolution.StringSolution());
                }
            }
        });

        buttonPanel.add(TexteComplet);
        buttonPanel.add(TexteSimplifie);
        Border bordNoir = BorderFactory.createLineBorder(Color.GRAY);
        buttonPanel.setBorder(bordNoir);

        //Interraction avec la combo Box
        Chauffeur_cb.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
            	if(marqueur == 1){
	            	int test;
	            	test = Chauffeur_cb.getSelectedIndex();

	            	//R?initialisation des champs de texte
	            	NbTacheCh_c.getTexte().setText("");
	            	Cost_c.getTexte().setText("");
	            	TypeService1_c.getTexte().setText("");
	            	WorkerTime_c.getTexte().setText("");
                    HeureChauffeurDepart_c.getTexte().setText("");
                    HeureChauffeurArrivee_c.getTexte().setText("");

	            	NbTacheCh_c.getTexte().append(" " + testSolution.getChauffeur(test).getNombreTaches() + " taches");
	            	Cost_c.getTexte().append(" "+testSolution.getChauffeur(test).getCost());
	            	TypeService1_c.getTexte().append(" " + testSolution.getChauffeur(test).PrintService());
	            	WorkerTime_c.getTexte().append(" "+ testSolution.GetHoraire(testSolution.getChauffeur(test).getWorkerTimeSum()));
                    HeureChauffeurDepart_c.getTexte().append(" " + testSolution.GetHoraire(testSolution.getChauffeur(test).getHeureDepart()));
                    HeureChauffeurArrivee_c.getTexte().append(" " + testSolution.GetHoraire(testSolution.getChauffeur(test).getHeureFin()));
                    numeroChauffeur = test;

            	}
            }
        });

        Tache_cb.addActionListener(new ActionListener () {
        	public void actionPerformed(ActionEvent e){
        		if(marqueur == 1){
        			int temp = Tache_cb.getSelectedIndex(); //Recuperation de l'information de la comboBox

        			//Reinitialisation des champs de texte
        			HeureDepart_c.getTexte().setText("");
        			HeureArrivee_c.getTexte().setText("");
        			LieuDepart_c.getTexte().setText("");
        			LieuArrivee_c.getTexte().setText("");
        			ChauffeurAssocie_c.getTexte().setText("");

        			//Renseignement des champs apres selection d'un onglet
        			HeureDepart_c.getTexte().append("" + testSolution.GetHoraire(testSolution.getTacheSolution(temp).getHeureDepart()) );
        			HeureArrivee_c.getTexte().append("" + testSolution.GetHoraire(testSolution.getTacheSolution(temp).getHeureArrivee()));
        			LieuDepart_c.getTexte().append("" + testSolution.getTacheSolution(temp).getLieuDepart());
        			LieuArrivee_c.getTexte().append("" + testSolution.getTacheSolution(temp).getLieuArrivee());
        			ChauffeurAssocie_c.getTexte().append("" + testSolution.getTacheSolution(temp).getNumeroChauffeur());
        		}
        	}
        });

        Content.add(buttonPanel, BorderLayout.NORTH);
        Content.add(TextSolution, BorderLayout.CENTER);
        Content.add(OngletSolution, BorderLayout.EAST);
        this.getContentPane().add(buttonPanel, BorderLayout.NORTH);
        this.getContentPane().add(TextSolution, BorderLayout.WEST);
        this.getContentPane().add(OngletSolution, BorderLayout.CENTER);
        //this.pack();
        this.setVisible(true);

	}
    /*
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
	}*/

    @Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		actionSouris = OpenFile.showOpenDialog(Interface.this);
		file = OpenFile.getSelectedFile();
		marqueur = 0;
		//------------------------------------------
        if (actionSouris == JFileChooser.APPROVE_OPTION) {

			//Reinitialisation des champs de parametres des zones de texte
			ZoneTexte_c.getTexte().setText(" ");
			CoutSolution_c.getTexte().setText("");
			NbChauffeur_c.getTexte().setText("");
			NbTache_c.getTexte().setText("");
			TypeService_c.getTexte().setText("");

            TexteSimplifie.setEnabled(true);
            TexteComplet.setEnabled(true);
            //String chemin = file.getAbsolutePath();
            //System.out.println(chemin);

			String fichierSolution = file.getName();
            nomFichier = new String(fichierSolution);
            DiagGantt.setEnabled(true);

            System.out.println(fichierSolution);
            if(fichierSolution.contains("Solution")){
			    testSolution = new Solution();
			    testSolution.LectureSolution(fichierSolution);
            }
            else{
                Fichier instance = new Fichier();
                instance.LectureInstance(fichierSolution);
                testSolution = new Solution();
                testSolution.GenerationSolution(instance);


            }

			ZoneTexte_c.getTexte().append("Chauffeur | Cout" + "\n");
			CoutSolution_c.getTexte().append(" " + testSolution.getCoutTotal() + " ");
			NbChauffeur_c.getTexte().append(" " + testSolution.getNombreChauffeurs() + " ");
			NbTache_c.getTexte().append(" " + testSolution.getTotalTaches() + " ");
			TypeService_c.getTexte().append("Service du matin : " + testSolution.getServiceMatin() + "\n" +
			    							"Service de jour  : " + testSolution.getServiceJour()  + "\n" +
			    							"Service du soir  : " + testSolution.getServiceSoir()  + "\n" +
			    							"Service de nuit  : " + testSolution.getServiceNuit()  + "\n");

			Chauffeur_cb.removeAllItems(); // Nettoie la combo box
			Chauffeur_cb.repaint();
			for(int i = 0; i < testSolution.getNombreChauffeurs(); i++){
			    Chauffeur_cb.addItem("Chauffeur n " + testSolution.getChauffeur(i).getNumeroChauffeur());
		    }

			Tache_cb.removeAllItems(); // Nettoie la combo box
			for(int i = 0; i < testSolution.getTotalTaches(); i++){
			    Tache_cb.addItem("Tache n " + testSolution.getTacheSolution(i).getNumeroTache()); //Rajouter le nombre de taches
			}
			    	
			for(int i = 0; i < testSolution.getChauffeurs().size();i++){
			    ZoneTexte_c.getTexte().append("   " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() +"   |   " + testSolution.getChauffeurs().get(i).getCost()+  "\n");
			}

			marqueur = 1;
	    }
		else
            ZoneTexte_c.getTexte().append("Erreur d'ouverture du fichier\n");
	}
		//------------------------------------------
}
