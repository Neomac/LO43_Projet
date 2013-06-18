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
	private int dureePauseSecondaire;
	private int dureeLegale;
	private int dureeMaximale;
	private int totalTaches;
	protected static ArrayList<Chauffeur> chauffeurs = new ArrayList<Chauffeur>();
	

	public Solution(){
		coutTotal=0;
		nombreChauffeurs=0;
		totalIdleTime=0;
		totalUnderTime=0;
		totalOverTime=0;
		serviceMatin=0;
		serviceJour=0;
		serviceSoir=0;
		serviceNuit=0;
		dureePauseLegale=30;		//Valeur par défaut
		dureePauseSecondaire=20;	//Valeur par défaut
		dureeLegale=480;			//Valeur par défaut
		dureeMaximale=600;			//Valeur par défaut
		totalTaches=0;
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
			this.setDureePauseSecondaire(Integer.parseInt(st.nextToken()));
			
			strLine = br.readLine();
			st = new StringTokenizer(strLine, " ");
			st.nextToken();
			this.setDureeLegale(Integer.parseInt(st.nextToken()));
			
			strLine = br.readLine();
			st = new StringTokenizer(strLine, " ");
			st.nextToken();
			this.setDureeMaximale(Integer.parseInt(st.nextToken()));
			
		}	catch (Exception e) {
			System.out.println("Erreur de lecture du fichier de configuration!");
		}
	}
	
	public void PrintConfiguration(){
		System.out.println("Configuration:");
		System.out.println("DureePauseLegale= "+this.getDureePauseLegale());
		System.out.println("DureePauseSecondaire= "+this.getDureePauseSecondaire());
		System.out.println("DureeLegaleTravail= "+this.getDureeLegale());
		System.out.println("DureeMaximumTravail= "+this.getDureeMaximale());
	}
	
	public void LectureSolution(String fichierSolution){
		int numero, heureDepart, heureArrivee, i=0, marker1=0, marker2=0;
		String lieuDepart, lieuArrivee;
		this.ReinitialiserSolution();
		
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
					this.totalTaches++;
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
			System.out.println("Erreur de lecture du fichier solution!");
		}
	}
	
	public void GenerationSolution(Fichier fichierInstance){
		int i=0, j=0, pause=0, marqueurArret=0, marqueurChoix=0;
		this.ReinitialiserSolution();
		while(!((fichierInstance.getTaches()).isEmpty())){
			i++;
			j=1;
			Chauffeur nouveauChauffeur = new Chauffeur();
			nouveauChauffeur.setNumeroChauffeur(i);
			this.setNombreChauffeurs(i);
			marqueurArret=0;
			//System.out.println("Numero Chauffeur "+i+" temps de travail "+nouveauChauffeur.getWorkerTimeSum()+" duree legale "+this.getDureeLegale()+" marqueurArret "+marqueurArret+" nombre de taches restantes "+fichierInstance.getTaches().size());
			while(((nouveauChauffeur.getWorkerTimeSum())<(this.getDureeLegale())) && marqueurArret==0){
				//System.out.println("\tNumero tache "+j);
				j++;
				pause=nouveauChauffeur.getNombrePause();
				if((nouveauChauffeur.getWorkerTimeSum())>(240+240*pause)){
					if(nouveauChauffeur.getNombrePause()==0){
						marqueurChoix=ChoisirProchaineTache(nouveauChauffeur, fichierInstance, this.getDureePauseLegale());
						nouveauChauffeur.setNombrePause(nouveauChauffeur.getNombrePause()+1);
					}
					else{
						marqueurChoix=ChoisirProchaineTache(nouveauChauffeur, fichierInstance, this.getDureePauseSecondaire());
						nouveauChauffeur.setNombrePause(nouveauChauffeur.getNombrePause()+1);
					}
				}
				else{
					marqueurChoix=ChoisirProchaineTache(nouveauChauffeur, fichierInstance, 0);
				}
				//System.out.println(marqueurChoix-1);
				if(marqueurChoix!=0){
					nouveauChauffeur.ajouterTache(fichierInstance, marqueurChoix-1);
				}
				if(((nouveauChauffeur.getWorkerTimeSum())>(this.getDureeLegale())) || marqueurChoix==0){
					//System.out.println(marqueurChoix);
					marqueurArret=1;
					this.TypeService(nouveauChauffeur);
					this.chauffeurs.add(nouveauChauffeur);
					nouveauChauffeur.CalculerCout(this);
					//nouveauChauffeur.PrintChauffeur(this);
				}
			}
		}
		this.CalculerCoutTotal();
	}
	
	public int ChoisirProchaineTache(Chauffeur c, Fichier fichierInstance, int dureePause){
		int indexTacheChoisie, heureTacheChoisie, heureTachePrecedente;
		String lieuTachePrecedente="";
		indexTacheChoisie=heureTacheChoisie=heureTachePrecedente=0;
		
		if ((c.getTachesChauffeur()).isEmpty()){	//Si ceci est la première tache de la journée du chauffeur on prend la première tache disponible
			//System.out.println("Test 1");
			heureTacheChoisie=(fichierInstance.getTacheFichier(0)).getHeureDepart();
			heureTachePrecedente=0;
			indexTacheChoisie=1;
			for (int i=1; i<(fichierInstance.getTaches().size()); i++){
				//System.out.println("Test 1.1");
				if(heureTacheChoisie>(fichierInstance.getTacheFichier(i)).getHeureDepart()){
					//System.out.println("Test 1.2");
					indexTacheChoisie=i+1;
					heureTacheChoisie=(fichierInstance.getTacheFichier(i)).getHeureDepart();
				}
			}
			c.TypeDeService(fichierInstance.getTacheFichier(indexTacheChoisie-1));
		}
		else{
			//System.out.println("Test 2");
			heureTachePrecedente=(c.getTache((c.getTachesChauffeur()).size()-1)).getHeureArrivee();
			lieuTachePrecedente=(c.getTache((c.getTachesChauffeur()).size()-1)).getLieuArrivee();
			heureTacheChoisie=1500;
			for (int i=0; i<(fichierInstance.getTaches().size()); i++){
				if(lieuTachePrecedente.equals((fichierInstance.getTacheFichier(i)).getLieuDepart()) && heureTacheChoisie>(fichierInstance.getTacheFichier(i)).getHeureDepart() && (fichierInstance.getTacheFichier(i)).getHeureDepart()-heureTachePrecedente>dureePause){
					//System.out.println("Test 2.2");
					indexTacheChoisie=i+1;
					heureTacheChoisie=(fichierInstance.getTacheFichier(i)).getHeureDepart();
				}
			}
		}
		//System.out.println("\t\tTache choisie "+indexTacheChoisie);
		return indexTacheChoisie;
	}
	
	public void CalculerCoutTotal(){
		for(int i=0; i<this.chauffeurs.size();i++){
			this.coutTotal+=(this.getChauffeur(i)).getCost();
			this.totalIdleTime+=(this.getChauffeur(i)).getIdleTime();
			this.totalOverTime+=(this.getChauffeur(i)).getOverTime();
			this.totalUnderTime+=(this.getChauffeur(i)).getUnderTime();
			this.totalTaches+=(this.getChauffeur(i)).getNombreTaches();
		}
	}
	
	public void PrintSolution(){
		System.out.println("**********The solution contains "+this.getNombreChauffeurs()+" driver(s) and "+this.getTotalTaches()+" tasks.**********");
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
	
	public String StringSolution(){
		String resultat="";
		System.out.println("**********The solution contains "+this.getNombreChauffeurs()+" driver(s) and "+this.getTotalTaches()+" tasks.**********");
		for (int i=0; i<this.chauffeurs.size(); i++){
			resultat=resultat+(this.chauffeurs.get(i)).StringChauffeur(this)+"\n";
		}
		resultat=resultat+"--------------------------------\n";
		resultat=resultat+"\n";
		resultat=resultat+"TotalCost="+this.getCoutTotal()+"\n";
		resultat=resultat+"TotalUnderTime="+this.getUnderTime()+"\n";
		resultat=resultat+"TotalIdleTime="+this.getIdleTime()+"\n";
		resultat=resultat+"Services du matin: "+this.getServiceMatin()+"\n";
		resultat=resultat+"Services du jour: "+this.getServiceJour()+"\n";
		resultat=resultat+"Services du soir: "+this.getServiceSoir()+"\n";
		resultat=resultat+"Services de nuit : "+this.getServiceNuit()+"\n";
		return resultat;
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
	
	public void ReinitialiserSolution(){
		coutTotal=0;
		nombreChauffeurs=0;
		totalIdleTime=0;
		totalUnderTime=0;
		totalOverTime=0;
		serviceMatin=0;
		serviceJour=0;
		serviceSoir=0;
		serviceNuit=0;
		dureePauseLegale=30;		//Valeur par défaut
		dureePauseSecondaire=20;	//Valeur par défaut
		dureeLegale=480;			//Valeur par défaut
		dureeMaximale=600;			//Valeur par défaut
		totalTaches=0;
		this.chauffeurs.clear();
	}
	
	public String GetHoraire(int minutes){
		String resultat;
		return resultat=((minutes)%60)+" heures "+(minutes-((minutes)%60)*60)+" minutes";
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

	public int getTotalTaches() {
		return totalTaches;
	}

	public void setTotalTaches(int totalTaches) {
		this.totalTaches = totalTaches;
	}

	public int getTotalOverTime() {
		return totalOverTime;
	}

	public void setTotalOverTime(int totalOverTime) {
		this.totalOverTime = totalOverTime;
	}

	public int getDureePauseSecondaire() {
		return dureePauseSecondaire;
	}

	public void setDureePauseSecondaire(int dureePauseSecondaire) {
		this.dureePauseSecondaire = dureePauseSecondaire;
	}
	
	
}
