import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Commit Test 2

public class Instance {
	
	protected static ArrayList<Tache> tachesInstance = new ArrayList<Tache>();
		
	public static void LectureInstance(){
		int i=1;
			try {
				FileInputStream fstream = new FileInputStream("Instance_1.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine;
				
				while((strLine = br.readLine()) != null){
					StringTokenizer st = new StringTokenizer(strLine, " ");
					Tache NouvelleTache = new Tache(i, Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken());
					tachesInstance.add(NouvelleTache);
					i++;
				}
				in.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur de lecture du fichier d'instance!");
			}
			
		}
	public static void PrintArray(){
		for (int i=0; i<tachesInstance.size(); i++){
			(tachesInstance.get(i)).PrintTache();
		}
	}
}
