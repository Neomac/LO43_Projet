import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class OuvrirFichier extends JPanel implements ActionListener {
	private JFileChooser OpenFile;
	private File file;
	private int actionSouris; //Lorsque l'on clique sur le bouton
	private JTable tableau;
	private String[][] data;
	private String title[];
	private JTextArea Texte;
	
	
	
	public OuvrirFichier(){};
	public JPanel OuvrirFichier(){
		OpenFile = new JFileChooser();
		JButton OpenButton = new JButton("Ouvrir un fichier");
		OpenButton.addActionListener(this);
		JPanel buttonPanel = new JPanel(); 
        buttonPanel.add(OpenButton, BorderLayout.SOUTH);
        //Texte = new JTextArea(10,10);
        //buttonPanel.add(new JScrollPane(Texte), BorderLayout.CENTER);
        String  title[] = {"Numéro Chauffeur", "Cout"};
        String[][] data = {
    			{"test ","test "}
    	};
        tableau = new JTable(data, title);
        buttonPanel.add(new JScrollPane(tableau), BorderLayout.CENTER);
        /*
        String  title[] = {"Numéro Chauffeur", "Cout"};
        tableau = new JTable();
        */
        return buttonPanel;        
	}
	
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
			    	
			    	//for(int i = 0; i < testSolution.getChauffeurs().size();i++){
			    	//Texte.append(" " + testSolution.getChauffeurs().get(i).getNumeroChauffeur() + "\n");
			    	//}
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
