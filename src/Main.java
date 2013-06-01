//Lecture du fichier d'instance

import java.io.*;  // Test pour GitHub
import java.util.Vector;

public class Main {
	
	private Vector<Tache> Taches;
	
	
	public static void main(String args[]){
		LectureFichier();
	}
	
	public static void LectureFichier(){
		int a, b;
		String A, B; 
			try {
				FileInputStream fstream = new FileInputStream("Instance_1.txt");
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String strLine, c;
				
				while((strLine = br.readLine()) != null){
					
					
					
					c=strLine.substring(0,2);
					c=c.trim();
					a = Integer.parseInt(c);
					c=strLine.substring(3,5);
					c=c.trim();
					a = a*60 + Integer.parseInt(c);
					
					c=strLine.substring(9,11);
					c=c.trim();
					b = Integer.parseInt(c);
					c=strLine.substring(12,14);
					c=c.trim();
					b = b*60 + Integer.parseInt(c);
					
					A=strLine.substring(18,19);
					B=strLine.substring(23,24);
					
					System.out.println(a+" "+b+" "+A+" "+B);
					
					//System.out.println(compteur + " : " + strLine);
					//compteur++;
				}
				in.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Erreur de lecture du fichier !!!");
			}
			
			}
}

/*
int a, b;
		int start, end;
		start=end=0;
		String c="";
 strLine.substring(0,2);
strLine.trim();
a = Integer.parseInt(strLine);
System.out.println(a);
*/