//Lecture du fichier d'instance

import java.io.*;  // Test pour GitHub
import java.util.ArrayList;
import java.util.Vector;
import java.util.StringTokenizer;

public class Fichier {
	
	protected static ArrayList<Tache> Taches = new ArrayList<Tache>();
	
	
	/* A effacer
	public static void main(String args[]){
		LectureFichier();
	}
	*/
	
	public static void LectureInstance(){
		int a, b;
		String A, B; 
			try {
				FileInputStream fstream = new FileInputStream("Instance_1.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine, c;
				
				while((strLine = br.readLine()) != null){
					
					StringTokenizer st = new StringTokenizer(strLine, " ");
					
					a=Integer.parseInt(st.nextToken());
					a = a*60 + Integer.parseInt(st.nextToken());
					b=Integer.parseInt(st.nextToken());
					b = b*60 + Integer.parseInt(st.nextToken());
					A=st.nextToken();
					B=st.nextToken();
					
					Tache NouvelleTache = new Tache(a, b, A, B);
					Taches.add(NouvelleTache);
				}
				in.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur de lecture du fichier !!!");
			}
			
		}
	public static void PrintArray(){
		for (int i=0; i<Taches.size(); i++){
			(Taches.get(i)).PrintTache();
		}
	}
}


