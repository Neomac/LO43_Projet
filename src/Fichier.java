//Lecture du fichier d'instance

import java.io.*;  // Test pour GitHub
import java.util.ArrayList;
import java.util.Vector;
import java.util.StringTokenizer;

public class Fichier {
	
	private ArrayList<Tache> Taches = new ArrayList<Tache>();
	
	
	/* A effacer
	public static void main(String args[]){
		LectureFichier();
	}
	*/
	
	public  void LectureInstance(){
		int i=1, a, b;
		String A, B; 
			try {
				FileInputStream fstream = new FileInputStream("Instance_3.txt");
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
					
					Tache NouvelleTache = new Tache(i, a, b, A, B);
					Taches.add(NouvelleTache);
					i++;
				}
				in.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur de lecture du fichier !!!");
			}
		}
	
	public  void PrintArray(){
		for (int i=0; i<this.Taches.size(); i++){
			(this.Taches.get(i)).PrintTache();
		}
	}

	 
	public Tache getTacheFichier(int i){
		return this.Taches.get(i);
	}
	
	public  ArrayList<Tache> getTaches() {
		return Taches;
	}
	public  void setTaches(ArrayList<Tache> taches) {
		Taches = taches;
	}
	
	
}

