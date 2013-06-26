//Lecture du fichier d'instance

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Fichier {
	
	private ArrayList<Tache> TachesFichier = new ArrayList<Tache>();	//Liste des Taches r�cup�r�es d'un fichier d'instance
	
	
	/* A effacer
	public static void main(String args[]){
		LectureFichier();
	}
	*/
	
	//Fonction de lecture d'un fichier d'instance
	public  void LectureInstance(String fichierInstance){
		int i=1, a, b;
		String A, B;
		this.ReinitialiserFichier();
			try {
				FileInputStream fstream = new FileInputStream(fichierInstance);
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
	
	//Impression du d�tail de la liste de Taches "tachesFichier"
	public  void PrintArray(){
		for (int i=0; i<this.TachesFichier.size(); i++){
			(this.TachesFichier.get(i)).PrintTache();
		}
	}
	
	//Efface le contenu de la liste de Taches "tachesFichier"
	public void ReinitialiserFichier(){
		this.TachesFichier.clear();
	}
	
	//Assesseurs de la classe Fichier
	public Tache getTacheFichier(int i){
		return this.TachesFichier.get(i);
	}
	
	public  ArrayList<Tache> getTaches() {
		return TachesFichier;
	}


}

