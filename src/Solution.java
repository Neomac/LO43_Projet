import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Commit Test 2

public class Solution {
	private int coutTotal;
	protected static ArrayList<Chauffeur> Chauffeurs = new ArrayList<Chauffeur>();
	
	
	public static void LectureSolution(){
		try {
			FileInputStream fstream = new FileInputStream("Solution_1.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			while((strLine = br.readLine()) != null){
				StringTokenizer st = new StringTokenizer(strLine, " ");
				System.out.println(st.nextToken());
				//Chauffeur nouveauChauffeur = new Chauffeur();
				//Chauffeurs.add(nouveauChauffeur);
			}
			in.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur de lecture du fichier d'instance!");
		}
	}
	
	public int getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(int coutTotal) {
		this.coutTotal = coutTotal;
	}
}
