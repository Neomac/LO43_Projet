//Lecture du fichier d'instance

import java.io.*;  // Test pour GitHub
import java.util.ArrayList;
import java.util.Vector;
import java.util.StringTokenizer;

public class Fichier {
	
	private ArrayList<Tache> TachesFichier = new ArrayList<Tache>();
	
	
	/* A effacer
	public static void main(String args[]){
		LectureFichier();
	}
	*/
	
	public  void LectureInstance(){
		int i=1, a, b;
		String A, B;
		this.ReinitialiserFichier();
			try {
				FileInputStream fstream = new FileInputStream("Instance_1.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				
				while((strLine = br.readLine()) != null){
					StringTokenizer st = new StringTokenizer(strLine, " ");
					
					a=Integer.parseInt(st.nextToken());
					a = a*60 + Integer.parseInt(st.nextToken());
					b=Integer.parseInt(st.nextToken());
					b = b*60 + Integer.parseInt(st.nextToken());
					A=st.nextToken();
					B=st.nextToken();
					
					Tache NouvelleTache = new Tache(i, 0, a, b, A, B);
					TachesFichier.add(NouvelleTache);
					i++;
				}
				in.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur de lecture du fichier !!!");
			}
		}
	
	public  void PrintArray(){
		for (int i=0; i<this.TachesFichier.size(); i++){
			(this.TachesFichier.get(i)).PrintTache();
		}
	}

	public void ReinitialiserFichier(){
		this.TachesFichier.clear();
	}
	
	public Tache getTacheFichier(int i){
		return this.TachesFichier.get(i);
	}
	
	public  ArrayList<Tache> getTaches() {
		return TachesFichier;
	}
	public  void setTaches(ArrayList<Tache> taches) {
		TachesFichier = taches;
	}
	
	
}

