import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Commit Test 2

public class Solution {
	private int coutTotal;					//coutTotal de la solution
	private int nombreChauffeurs;			//nombre total de Chauffeurs de la solution
	private int totalIdleTime;				//temps d'inactivit� total
	private int totalUnderTime;				//temps total en dessous de la duree l�gale 
	private int totalOverTime;				//temps total au dessus de la duree l�gale
	private int serviceMatin;				//Nombre total de chauffeurs en service du matin
	private int serviceJour;				//Nombre total de chauffeurs en service du jour
	private int serviceSoir;				//Nombre total de chauffeurs en service du soir
	private int serviceNuit;				//Nombre total de chauffeurs en service de nuit
	private int dureePauseLegale;			//duree de la pause legale obligatoire
	private int dureePauseSecondaire;		//duree des eventuelles pauses apres 4h de travail consecutif
	private int dureeLegale;				//duree normale de travail
	private int dureeMaximale;				//duree maximale de travail
	private int totalTaches;				//nombre total de taches
	protected static ArrayList<Chauffeur> chauffeurs = new ArrayList<Chauffeur>();	//liste de chauffeurs de la solution
	protected static ArrayList<Tache> taches = new ArrayList<Tache>();				//Copie de la liste des taches de la solution
																					//Utilis�e par l'interface graphique pour acc�der directement aux taches
	
	//Constructeur par d�faut de Solution
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
		dureePauseLegale=30;		//Valeur par d�faut
		dureePauseSecondaire=20;	//Valeur par d�faut
		dureeLegale=480;			//Valeur par d�faut
		dureeMaximale=600;			//Valeur par d�faut
		totalTaches=0;
	}
	
	//Lecture du fichier de configuration et mise � jour des attributs configurables:
	//dureePauseLegale, dureePauseSecondaire, dureeLegale et dureeMaximale
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
	
	//Impression des attibuts configurables
	public void PrintConfiguration(){
		System.out.println("Configuration:");
		System.out.println("DureePauseLegale= "+this.getDureePauseLegale());
		System.out.println("DureePauseSecondaire= "+this.getDureePauseSecondaire());
		System.out.println("DureeLegaleTravail= "+this.getDureeLegale());
		System.out.println("DureeMaximumTravail= "+this.getDureeMaximale());
	}
	
	//Copie de la liste des taches du fichier d'instance dans la liste "taches"
	public void CopieTaches(Fichier fichierInstance){
		Tache TamponTache=new Tache();
		for(int i=0; i<fichierInstance.getTaches().size();i++){
			this.taches.add(fichierInstance.getTacheFichier(i));
		}
	}
	
	//Fonction de tri des taches selon le num�ro de la tache
	//n�cessaire lorsque la liste de tache est r�cup�r�e d'un fichier solution car elles seront dans le d�sordre
	public void TriTaches(){							
		int j=0;										
		for(int i=0; i<this.taches.size();i++){
			j=this.RechercheIndexTacheNumero(i+1);		//Le +1 est l� car les num�ros des taches commencent � 1 et non pas 0
			this.taches.add(i, this.taches.remove(j));	//On efface la tache de sa position pour l'ins�rer � la r�ins�rer � l'index i
		}
	}
	
	//Fonction renvoyant l'index d'une tache dans la liste taches selon un numero de tache
	public int RechercheIndexTacheNumero(int numero){	
		int j=0;
		for(int i=0; i<this.taches.size();i++){					//Parcourt de la liste de Taches jusqu'� trouver la Tache voulue
			if(this.taches.get(i).getNumeroTache()==numero)
				j=i;
		}
		return j;
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
					Tache nouvelleTache = new Tache(numero, i, heureDepart, heureArrivee, lieuDepart,lieuArrivee);
					nouveauChauffeur.AjouterTacheChauffeur(nouvelleTache);
					this.taches.add(nouvelleTache);
					
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
				nouveauChauffeur.CalculerCout(this);
			}
			strLine = br.readLine();
			strLine = br.readLine();
			this.coutTotal=Integer.parseInt(strLine.substring(10));
			this.TriTaches();
			in.close();
			
		} catch (Exception e) {
			System.out.println("Erreur de lecture du fichier solution!");
		}
	}
	
	public void GenerationSolution(Fichier fichierInstance){
		int i=0, pause=0, marqueurArret=0, marqueurChoix=0;
		this.ReinitialiserSolution();
		this.CopieTaches(fichierInstance);
		while(!((fichierInstance.getTaches()).isEmpty())){
			i++;
			Chauffeur nouveauChauffeur = new Chauffeur();
			nouveauChauffeur.setNumeroChauffeur(i);
			this.setNombreChauffeurs(i);
			marqueurArret=0;
			while(((nouveauChauffeur.getWorkerTimeSum())<(this.getDureeLegale())) && marqueurArret==0){
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
				if(marqueurChoix!=0){
					nouveauChauffeur.ajouterTache(fichierInstance, marqueurChoix-1);
				}
				if(((nouveauChauffeur.getWorkerTimeSum())>(this.getDureeLegale())) || marqueurChoix==0){
					marqueurArret=1;
					this.TypeService(nouveauChauffeur);
					this.chauffeurs.add(nouveauChauffeur);
					nouveauChauffeur.CalculerCout(this);
				}
			}
		}
		this.CalculerCoutTotal();
	}
	
	public int ChoisirProchaineTache(Chauffeur c, Fichier fichierInstance, int dureePause){
		int indexTacheChoisie, heureTacheChoisie, heureTachePrecedente;
		String lieuTachePrecedente="";
		indexTacheChoisie=heureTacheChoisie=heureTachePrecedente=0;
		
		if ((c.getTachesChauffeur()).isEmpty()){	//Si ceci est la premi�re tache de la journ�e du chauffeur on prend la premi�re tache disponible
			heureTacheChoisie=(fichierInstance.getTacheFichier(0)).getHeureDepart();
			heureTachePrecedente=0;
			indexTacheChoisie=1;
			for (int i=1; i<(fichierInstance.getTaches().size()); i++){
				if(heureTacheChoisie>(fichierInstance.getTacheFichier(i)).getHeureDepart()){
					indexTacheChoisie=i+1;
					heureTacheChoisie=(fichierInstance.getTacheFichier(i)).getHeureDepart();
				}
			}
			c.TypeDeService(fichierInstance.getTacheFichier(indexTacheChoisie-1));
		}
		else{
			heureTachePrecedente=(c.getTache((c.getTachesChauffeur()).size()-1)).getHeureArrivee();
			lieuTachePrecedente=(c.getTache((c.getTachesChauffeur()).size()-1)).getLieuArrivee();
			heureTacheChoisie=1500;
			for (int i=0; i<(fichierInstance.getTaches().size()); i++){
				if(lieuTachePrecedente.equals((fichierInstance.getTacheFichier(i)).getLieuDepart()) && heureTacheChoisie>(fichierInstance.getTacheFichier(i)).getHeureDepart() && (fichierInstance.getTacheFichier(i)).getHeureDepart()-heureTachePrecedente>dureePause){
					indexTacheChoisie=i+1;
					heureTacheChoisie=(fichierInstance.getTacheFichier(i)).getHeureDepart();
				}
			}
		}
		return indexTacheChoisie;
	}
	
	//Calcul de coutTotal, totalIdleTime, totalOverTime, totalUnderTime, totalTaches
	//somme des attributs des Chauffeurs
	public void CalculerCoutTotal(){
		for(int i=0; i<this.chauffeurs.size();i++){
			this.coutTotal+=(this.getChauffeur(i)).getCost();
			this.totalIdleTime+=(this.getChauffeur(i)).getIdleTime();
			this.totalOverTime+=(this.getChauffeur(i)).getOverTime();
			this.totalUnderTime+=(this.getChauffeur(i)).getUnderTime();
			this.totalTaches+=(this.getChauffeur(i)).getNombreTaches();
		}
	}
	
	//Impression des informations de la solution en partie selon le format des fichiers solution_X.txt fournis
	//certaines informations on �t� rajout�es par rapport aux fichier originaux
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
	
	//Idem de PrintSolution mais renvoie le r�sultat sous forme de String pour pouvoir �tre exploit� par l'interface graphique
	public String StringSolution(){
		String resultat="";
		resultat=resultat+"**********The solution contains "+this.getNombreChauffeurs()+" driver(s) and "+this.getTotalTaches()+" tasks.**********"+"\n";
		for (int i=0; i<this.chauffeurs.size(); i++){
			resultat=resultat+(this.chauffeurs.get(i)).StringChauffeur(this)+"\n";
		}
		resultat=resultat+"--------------------------------\n";
		resultat=resultat+"\n";
		resultat=resultat+"TotalCost="+this.getCoutTotal()+"\n";
		resultat=resultat+"TotalUnderTime="+this.getUnderTime()+"\n";				//Ligne en plus par rapport au fichiers solution
		resultat=resultat+"TotalIdleTime="+this.getIdleTime()+"\n";					//Ligne en plus par rapport au fichiers solution
		resultat=resultat+"Services du matin: "+this.getServiceMatin()+"\n";		//Ligne en plus par rapport au fichiers solution
		resultat=resultat+"Services du jour: "+this.getServiceJour()+"\n";			//Ligne en plus par rapport au fichiers solution
		resultat=resultat+"Services du soir: "+this.getServiceSoir()+"\n";			//Ligne en plus par rapport au fichiers solution
		resultat=resultat+"Services de nuit : "+this.getServiceNuit()+"\n";			//Ligne en plus par rapport au fichiers solution
		return resultat;
	}
	
	//Renvoie sous forme de String le resultat contenant le descriptif de toutes les taches au format des fichiers solutions
	public String StringTaches(){
		String resultat="";
		for (int i=0; i<this.taches.size(); i++){
			resultat=resultat+((this.taches.get(i)).StringTache());
			
		}
		return resultat;
	}
	
	//Mise � jour du nombre de total des diff�rents types de service
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
	
	//R�initialisation de la solution et surtout des listes "chauffeurs" et "taches" 
	//afin de pouvoir ouvrir et g�n�rer de nouvelles solutions
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
		dureePauseLegale=30;		//Valeur par d�faut
		dureePauseSecondaire=20;	//Valeur par d�faut
		dureeLegale=480;			//Valeur par d�faut
		dureeMaximale=600;			//Valeur par d�faut
		totalTaches=0;
		this.chauffeurs.clear();
		this.taches.clear();
	}
	
	//Renvoie sous forme de String un horaire � l'origine en minutes au format: "heure h minutes"
	public String GetHoraire(int horaire){		//Renvoi
		String resultat;
		int heures, minutes;
		heures=(horaire-(horaire)%60)/60;
		minutes=(horaire)%60;
		if (minutes<10){
			resultat=heures+"h0"+minutes;
		}
		else
			resultat=heures+"h"+minutes;
		return resultat;
	}
	
	
	//Assesseurs de la classe Solution
	public Chauffeur getChauffeur(int i){
		return chauffeurs.get(i);
	}
	
	public Tache getTacheSolution(int i){
		return taches.get(i);
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
	
	public static ArrayList<Tache> getTachesSolution(){
		return taches;
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
