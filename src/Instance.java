import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Commit Test 2

public class Instance {
	
	private ArrayList<Tache> tachesInstance = new ArrayList<Tache>();
		
	public void LectureInstance(){
		int i=1;
			try {
				FileInputStream fstream = new FileInputStream("Instance_3.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				
				while((strLine = br.readLine()) != null){
					StringTokenizer st = new StringTokenizer(strLine, " ");
					Tache NouvelleTache = new Tache(i, Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken());
					this.tachesInstance.add(NouvelleTache);
					i++;
				}
				in.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur de lecture du fichier d'instance!");
			}
			
		}
	public  void PrintArray(){
		for (int i=0; i<this.tachesInstance.size(); i++){
			(this.tachesInstance.get(i)).PrintTache();
		}
	}
}
