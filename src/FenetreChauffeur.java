import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FenetreChauffeur extends JFrame {
	  
	
	public FenetreChauffeur(){
		    this.setLocationRelativeTo(null);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setTitle("JTable");
		    this.setSize(300, 120);

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
}