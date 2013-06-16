import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Commit Test 2

public class Solution {
	private int coutTotal;
	private int nombreChauffeurs;
	private int totalIdleTime;
	private int totalUnderTime;
	private int totalOverTime;
	private int serviceMatin;
	private int serviceJour;
	private int serviceSoir;
	private int serviceNuit;
	private int dureePauseLegale;
	private int dureeLegale;
	private int dureeMaximale;
	private int nombreTaches;
	protected static ArrayList<Chauffeur> chauffeurs = new ArrayList<Chauffeur>();
	

	public Solution(){
		coutTotal=0;
		nombreChauffeurs=0;
		totalIdleTime=0;
		totalUnderTime=0;
		totalOverTime=0;
		dureePauseLegale=30;		//Valeur par défaut
		dureeLegale=480;			//Valeur par défaut
		dureeMaximale=600;			//Valeur par défaut
		nombreTaches=0;
	}
	
	public void LectureConfiguration(String fichierConfiguration){
		try{
			FileInputStream fstream = new FileInputStream(fichierConfiguration);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine = br.readLine();
			StringTokenizer st = new StringTokenizer(strLine, " ");
			st.nextToken();
			this.setDureePauseLegale(Integer.parseInt(st.nextToken()));
			
			strLine = br.readLine();
			st = new StringTokenizer(strLine, " ");
			st.nextToken();
			this.setDureeLegale(Integer.parseInt(st.nextToken()));
			
			strLine = br.readLine();
			st = new StringTokenizer(strLine, " ");
			st.nextToken();
			this.setDureeMaximale(Integer.parseInt(st.nextToken()));
			
		}	catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur de lecture du fichier de configuration!");
		}
	}
	
	public void PrintConfiguration(){
		System.out.println("Configuration:");
		System.out.println("DureePauseLegale= "+this.getDureePauseLegale());
		System.out.println("DureeLegaleTravail= "+this.getDureeLegale());
		System.out.println("DureeMaximumTravail= "+this.getDureeMaximale());
	}
	
	public void LectureSolution(String fichierSolution){
		int numero, heureDepart, heureArrivee, i=0, marker1=0, marker2=0;
		String lieuDepart, lieuArrivee;
		try {
			FileInputStream fstream = new FileInputStream(fichierSolution);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine, c="";
			this.nombreChauffeurs=0;
			
			strLine = br.readLine();
			StringTokenizer st = new StringTokenizer(strLine, " ");
			st.nextToken();
			st.nextToken();
			st.nextToken();
			this.setCoutTotal(Integer.parseInt(st.nextToken()));
			
			strLine = br.readLine();
			
			while(marker2==0){
				i++;
				Chauffeur nouveauChauffeur = new Chauffeur();
				nouveauChauffeur.setNumeroChauffeur(i);
				this.nombreChauffeurs++;
				marker1=0;
				
				strLine = br.readLine();
				strLine = br.readLine();
				nouveauChauffeur.setWorkerTimeSum(Integer.parseInt(strLine.substring(14)));
				
				strLine = br.readLine();
				st = new StringTokenizer(strLine, " ");
				st.nextToken();
				st.nextToken();
				
				if (nouveauChauffeur.getWorkerTimeSum()<this.getDureeLegale()){
					nouveauChauffeur.setUnderTime(Integer.parseInt(st.nextToken()));
					this.totalUnderTime+=nouveauChauffeur.getUnderTime();
				}
				else{
					nouveauChauffeur.setOverTime(Integer.parseInt(st.nextToken()));
					this.totalOverTime+=nouveauChauffeur.getOverTime();
				}
				
				strLine = br.readLine();
				st = new StringTokenizer(strLine, " ");
				st.nextToken();
				st.nextToken();
				nouveauChauffeur.setIdleTime(Integer.parseInt(st.nextToken()));
				this.totalIdleTime+=nouveauChauffeur.getIdleTime();
				
				strLine = br.readLine();
				nouveauChauffeur.setCost(Integer.parseInt(strLine.substring(5)));
								
				strLine = br.readLine();
				st = new StringTokenizer(strLine, " ");
				
				while(marker1==0){
					this.nombreTaches++;
					st = new StringTokenizer(strLine, "\t");
					c=st.nextToken();
					numero=Integer.parseInt(c.substring(5));
						
					c=st.nextToken();
					heureDepart=Integer.parseInt(c.substring(10));
						
					c=st.nextToken();
					heureArrivee=Integer.parseInt(c.substring(11, c.length()-2));
					lieuDepart=c.substring(c.length()-2, c.length()-1);
					lieuArrivee=c.substring(c.length()-1);
					Tache nouvelleTache = new Tache(numero, heureDepart, heureArrivee, lieuDepart,lieuArrivee);
					nouveauChauffeur.AjouterTacheChauffeur(nouvelleTache);
					
					strLine=br.readLine();
					if(strLine.compareTo("")!=0){
						marker1=1;
						if(strLine.compareTo("--------------------------------")==0){
							marker1=marker2=1;
						}
					}
					else{
						strLine=br.readLine();
					}
				}
				nouveauChauffeur.TypeDeService(nouveauChauffeur.getTache(0));
				this.TypeService(nouveauChauffeur);
				this.chauffeurs.add(nouveauChauffeur);
			}
			strLine = br.readLine();
			strLine = br.readLine();
			this.coutTotal=Integer.parseInt(strLine.substring(10));
			in.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erreur de lecture du fichier solution!");
		}
	}
	
	public void GenerationSolution(Fichier fichierInstance){
		int i=0, j=1, pause=0, marqueurArret=0, marqueurChoix=0;
		
		while(!((fichierInstance.getTaches()).isEmpty())){
			i++;
			Chauffeur nouveauChauffeur = new Chauffeur();
			nouveauChauffeur.setNumeroChauffeur(i);
			while(nouveauChauffeur.getWorkerTimeSum()<this.getDureeLegale() && marqueurArret==0){
				pause=nouveauChauffeur.getHeurePause(0);
				while(nouveauChauffeur.getHeurePause(j+1)!=0){
					pause=nouveauChauffeur.getHeurePause(j);
					j++;
				}
				if((nouveauChauffeur.getWorkerTimeSum()-pause)>240){
					//if()
				}
			}
		}
	}
	
	public int ChoisirProchaineTache(Chauffeur c, Fichier fichierInstance, int dureePause){
		int indexTacheChoisie, heureTacheChoisie, heureTachePrecedente;
		String lieuTachePrecedente="";
		indexTacheChoisie=heureTacheChoisie=heureTachePrecedente=0;
		
		if ((c.getTachesChauffeur()).isEmpty()){	//Si ceci est la première tache de la journée du chauffeur on prend la première tache disponible
			heureTacheChoisie=(fichierInstance.getTache(0)).getHeureDepart();
			heureTachePrecedente=0;
			for (int i=1; i<(fichierInstance.getTaches().size()); i++){
				if(heureTacheChoisie>(fichierInstance.getTache(i)).getHeureDepart()){
					indexTacheChoisie=i;
					heureTacheChoisie=(fichierInstance.getTache(i)).getHeureDepart();
				}
			}
		}
		else{
			heureTachePrecedente=(c.getTache((c.getTachesChauffeur()).size())).getHeureArrivee();
			lieuTachePrecedente=(c.getTache((c.getTachesChauffeur()).size())).getLieuDepart();
			heureTacheChoisie=1500;
			for (int i=0; i<(fichierInstance.getTaches().size()); i++){
				if(lieuTachePrecedente==(fichierInstance.getTache(i)).getLieuDepart() && heureTacheChoisie>(fichierInstance.getTache(i)).getHeureDepart() && (fichierInstance.getTache(i)).getHeureDepart()-heureTachePrecedente>dureePause){
					indexTacheChoisie=i;
					heureTacheChoisie=(fichierInstance.getTache(i)).getHeureDepart();
				}
			}
		}
		return indexTacheChoisie;
	}
	
	public void ajouterTache(Chauffeur c, Fichier fichierInstance, int indexTache){ //Ajoute la tache numTache du fichier d'instance au chauffeur c et calcul workerTimeSum et idleTime
		c.AjouterTacheChauffeur(fichierInstance.getTache(indexTache));
		c.setWorkerTimeSum(c.getWorkerTimeSum()+(fichierInstance.getTache(indexTache)).getHeureArrivee()-(fichierInstance.getTache(indexTache)).getHeureDepart());
		c.setIdleTime((fichierInstance.getTache(indexTache)).getHeureDepart()-(c.getTache((c.getTachesChauffeur()).size())).getHeureArrivee());
		(fichierInstance.getTaches()).remove(indexTache);
	}
	
	public void PrintSolution(){
		System.out.println("**********The solution contains "+this.getNombreChauffeurs()+" driver(s) and "+this.getNombreTaches()+" tasks.**********");
		for (int i=0; i<this.chauffeurs.size(); i++){
			(this.chauffeurs.get(i)).PrintChauffeur(this);
		}
		System.out.println("--------------------------------");
		System.out.println();
		System.out.println("TotalCost="+this.getCoutTotal());
		System.out.println("TotalUnderTime="+this.getUnderTime());				//Ligne en plus par rapport au fichiers solution
		System.out.println("TotalIdleTime="+this.getIdleTime());				//Ligne en plus par rapport au fichiers solution
		System.out.println("Services du matin: "+this.getServiceMatin());		//Ligne en plus par rapport au fichiers solution
		System.out.println("Services du jour: "+this.getServiceJour());			//Ligne en plus par rapport au fichiers solution
		System.out.println("Services du soir: "+this.getServiceSoir());			//Ligne en plus par rapport au fichiers solution
		System.out.println("Services de nuit : "+this.getServiceNuit());		//Ligne en plus par rapport au fichiers solution
	}
	
	public void TypeService(Chauffeur c){
		switch(c.getTypeService()){
		case 1:
			this.serviceMatin++;
			break;
		case 2:
			this.serviceJour++;
			break;
		case 3:
			this.serviceSoir++;
			break;
		case 4:
			this.serviceNuit++;
			break;
		}
	}
	
	public Chauffeur getChauffeur(int i){
		return chauffeurs.get(i);
	}
	
	public int getServiceMatin() {
		return serviceMatin;
	}

	public int getServiceJour() {
		return serviceJour;
	}

	public int getServiceSoir() {
		return serviceSoir;
	}

	public int getServiceNuit() {
		return serviceNuit;
	}

	public static ArrayList<Chauffeur> getChauffeurs() {
		return chauffeurs;
	}

	public void setServiceMatin(int serviceMatin) {
		this.serviceMatin = serviceMatin;
	}

	public void setServiceJour(int serviceJour) {
		this.serviceJour = serviceJour;
	}

	public void setServiceSoir(int serviceSoir) {
		this.serviceSoir = serviceSoir;
	}

	public void setServiceNuit(int serviceNuit) {
		this.serviceNuit = serviceNuit;
	}

	public static void setChauffeurs(ArrayList<Chauffeur> chauffeurs) {
		Solution.chauffeurs = chauffeurs;
	}

	public int getCoutTotal() {
		return coutTotal;
	}

	public void setCoutTotal(int coutTotal) {
		this.coutTotal = coutTotal;
	}
	
	public int getNombreChauffeurs() {
		return nombreChauffeurs;
	}

	public void setNombreChauffeurs(int nombreChauffeurs) {
		this.nombreChauffeurs = nombreChauffeurs;
	}

	public int getIdleTime() {
		return totalIdleTime;
	}

	public int getUnderTime() {
		return totalUnderTime;
	}

	public void setIdleTime(int idleTime) {
		this.totalIdleTime = idleTime;
	}

	public void setUnderTime(int underTime) {
		this.totalUnderTime = underTime;
	}

	public int getTotalIdleTime() {
		return totalIdleTime;
	}

	public int getTotalUnderTime() {
		return totalUnderTime;
	}

	public int getDureePauseLegale() {
		return dureePauseLegale;
	}

	public int getDureeLegale() {
		return dureeLegale;
	}

	public int getDureeMaximale() {
		return dureeMaximale;
	}

	public void setTotalIdleTime(int totalIdleTime) {
		this.totalIdleTime = totalIdleTime;
	}

	public void setTotalUnderTime(int totalUnderTime) {
		this.totalUnderTime = totalUnderTime;
	}

	public void setDureePauseLegale(int dureePauseLegale) {
		this.dureePauseLegale = dureePauseLegale;
	}

	public void setDureeLegale(int dureeLegale) {
		this.dureeLegale = dureeLegale;
	}

	public void setDureeMaximale(int dureeMaximale) {
		this.dureeMaximale = dureeMaximale;
	}

	public int getNombreTaches() {
		return nombreTaches;
	}

	public void setNombreTaches(int nombreTaches) {
		this.nombreTaches = nombreTaches;
	}

	public int getTotalOverTime() {
		return totalOverTime;
	}

	public void setTotalOverTime(int totalOverTime) {
		this.totalOverTime = totalOverTime;
	}
	
	
}
