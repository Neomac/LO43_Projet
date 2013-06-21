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
	private int totalIdleTime;				//temps d'inactivité total
	private int totalUnderTime;				//temps total en dessous de la duree légale 
	private int totalOverTime;				//temps total au dessus de la duree légale
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
																					//Utilisée par l'interface graphique pour accéder directement aux taches
	
	//Constructeur par défaut de Solution
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
	
	//Lecture du fichier de configuration et mise à jour des attributs configurables:
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
	
	//Fonction de tri des taches selon le numéro de la tache
	//nécessaire lorsque la liste de tache est récupérée d'un fichier solution car elles seront dans le désordre
	public void TriTaches(){							
		int j=0;										
		for(int i=0; i<this.taches.size();i++){
			j=this.RechercheIndexTacheNumero(i+1);		//Le +1 est là car les numéros des taches commencent à 1 et non pas 0
			this.taches.add(i, this.taches.remove(j));	//On efface la tache de sa position pour l'insérer à la réinsérer à l'index i
		}
	}
	
	//Fonction renvoyant l'index d'une tache dans la liste taches selon un numero de tache
	public int RechercheIndexTacheNumero(int numero){	
		int j=0;
		for(int i=0; i<this.taches.size();i++){					//Parcourt de la liste de Taches jusqu'à trouver la Tache voulue
			if(this.taches.get(i).getNumeroTache()==numero)
				j=i;
		}
		return j;
	}
	
	//Fonction de lecture d'un fichier de solution fourni et enregistrement des solutions dans des instances des classes Chauffeurs et Taches
	public void LectureSolution(String fichierSolution){
		int numero, heureDepart, heureArrivee, i=0, marker1=0, marker2=0;
		String lieuDepart, lieuArrivee;
		this.ReinitialiserSolution();		//On réinitialise la liste de Chauffeurs et de Taches pour ne pas les conserver à chaque fois que l'on change de fichier
		
		try {
			FileInputStream fstream = new FileInputStream(fichierSolution);			//Lecture du fichier et copie dans le BufferedReader br
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine, c="";
			
			strLine = br.readLine();												//Lecture du nombre total de chauffeurs sur la première ligne
			StringTokenizer st = new StringTokenizer(strLine, " ");
			st.nextToken();															
			st.nextToken();
			st.nextToken();
			this.setNombreChauffeurs(Integer.parseInt(st.nextToken()));	
			
			strLine = br.readLine();
			
			while(marker2==0){		//Boucle de lecture de chaque chauffeur, marker2 est mis à 1 une fois arrivé à la fin des chauffeurs
				i++;
				Chauffeur nouveauChauffeur = new Chauffeur();		//Création d'un nouveau chauffeur
				nouveauChauffeur.setNumeroChauffeur(i);
				marker1=0;											//Réinitialisation du marqueur d'arrêt de la boucle de lecture de taches
				
				strLine = br.readLine();							//Lecture de workerTimeSum du Chauffeur
				strLine = br.readLine();
				nouveauChauffeur.setWorkerTimeSum(Integer.parseInt(strLine.substring(14)));
				
				strLine = br.readLine();
				st = new StringTokenizer(strLine, " ");
				st.nextToken();
				st.nextToken();
				
				if (nouveauChauffeur.getWorkerTimeSum()<this.getDureeLegale()){				//Lecture de underTime ou overTime selon la situation
					nouveauChauffeur.setUnderTime(Integer.parseInt(st.nextToken()));
					this.totalUnderTime+=nouveauChauffeur.getUnderTime();					//Mise à jour de totalUnderTime de la Solution
				}
				else{
					nouveauChauffeur.setOverTime(Integer.parseInt(st.nextToken()));
					this.totalOverTime+=nouveauChauffeur.getOverTime();						//Mise à jour de totalOverTime de la Solution
				}
				
				strLine = br.readLine();													//Lecture de idleTime du Chauffeur
				st = new StringTokenizer(strLine, " ");
				st.nextToken();
				st.nextToken();
				nouveauChauffeur.setIdleTime(Integer.parseInt(st.nextToken()));
				this.totalIdleTime+=nouveauChauffeur.getIdleTime();							//Mise à jour de totalIdleTime de la Solution
				
				strLine = br.readLine();
				nouveauChauffeur.setCost(Integer.parseInt(strLine.substring(5)));			//Lecture du cout du Chauffeur
								
				strLine = br.readLine();
				st = new StringTokenizer(strLine, " ");
				
				while(marker1==0){										//Boucle de lecture des Taches du Chauffeur
					this.totalTaches++;									//Mise à jour du nombre total de taches
					st = new StringTokenizer(strLine, "\t");
					c=st.nextToken();
					numero=Integer.parseInt(c.substring(5));			//Récupération du numéro de la Tache
						
					c=st.nextToken();
					heureDepart=Integer.parseInt(c.substring(10));		//Récupération de l'heure de départ de la Tache
						
					c=st.nextToken();
					heureArrivee=Integer.parseInt(c.substring(11, c.length()-2));		//Récupération de l'heure d'arrivée de la Tache
					lieuDepart=c.substring(c.length()-2, c.length()-1);					//Récupération du lieu de départ de la Tache
					lieuArrivee=c.substring(c.length()-1);								//Récupération du lieu d'arrivée de la Tache
					Tache nouvelleTache = new Tache(numero, i, heureDepart, heureArrivee, lieuDepart,lieuArrivee);	//Création d'une nouvelle Tache avec les informations récupérées
					nouveauChauffeur.AjouterTacheChauffeur(nouvelleTache);				//Ajout de la Tache à la liste de Taches du Chauffeur
					this.taches.add(nouvelleTache);										//Ajout de la Tache à la liste de Taches de la Solution
					
					strLine=br.readLine();
					if(strLine.compareTo("")!=0){										//Boucle de Test afin de savoir si on est en fin de liste de Taches
						marker1=1;														//marker1 est mis à 1 afin de sortir de la boucle de lecture des Taches
						if(strLine.compareTo("--------------------------------")==0){	//Boucle de Test afin de savoir si on est en fin de liste de Chauffeurs
							marker2=1;													//marker2 est mis à 1 afin de sortir de la boucle de lecture des Chauffeurs
						}
					}
					else{																//Sinon on saute une ligne et on passe à la Tache suivante
						strLine=br.readLine();
					}
				}
																						//Fin de la lecture des Taches
				nouveauChauffeur.TypeDeService(nouveauChauffeur.getTache(0));			//Calcul du type de servce du Chauffeur
				this.TypeService(nouveauChauffeur);										//Mise à jour du nombre de chaque type de service de la Solution
				this.chauffeurs.add(nouveauChauffeur);									//Ajout du chauffeur à la liste de Chauffeurs de la Solution
				nouveauChauffeur.CalculerCout(this);									//Calcul du cout et de différents attributs du Chauffeur
			}
																						//Fin de la lecture des Chauffeurs
			strLine = br.readLine();
			strLine = br.readLine();
			this.coutTotal=Integer.parseInt(strLine.substring(10));						//Lecture du cout total de la Solution
			this.TriTaches();															//Tri de la liste de Taches de la Solution comme elles n'ont pas été récupérées dans l'ordre
			in.close();
			
		} catch (Exception e) {															//Catch en cas d'erreur dans la lecture du fichier
			System.out.println("Erreur de lecture du fichier solution!");
		}
	}
	
	//Fonction de création de solution à partir d'un fichier d'instance
	public void GenerationSolution(Fichier fichierInstance){
		int i=0, pause=0, marqueurArret=0, marqueurChoix=0;
		this.ReinitialiserSolution();							//On réinitialise la liste de Chauffeurs et de Taches pour ne pas les conserver à chaque fois que l'on change de fichier
		this.CopieTaches(fichierInstance);						//Copie de la liste de Taches du fichier d'instance dans celle de la Solution
		while(!((fichierInstance.getTaches()).isEmpty())){		//La boucle de création de solution ne s'arrêtera que lorsque toutes les solutions auront été attribuées,
																//les taches sont effacées de la liste du fichier d'instance dès qu'elles sont assignées à un Chauffeur
			i++;
			Chauffeur nouveauChauffeur = new Chauffeur();		//Création d'un nouveau Chauffeur
			nouveauChauffeur.setNumeroChauffeur(i);				//Assignation d'un numéro au Chauffeur grâce à la variable i incrémentée à chaque itération de la boucle while
			
			marqueurArret=0;									//Réinitialisation du marqueur d'arrêt de la boucle d'ajout de Taches au nouveau Chaffeur
			while(((nouveauChauffeur.getWorkerTimeSum())<(this.getDureeLegale())) && marqueurArret==0){		//Boucle d'ajout de Tache au nouveau Chauffeur, la boucle s'arrêtera si le chauffeur 
																											//a dépassé la durée maximale de travail ou si le marqueur d'arrêt est à 1
				pause=nouveauChauffeur.getNombrePause();						//Récupération du nombre de pauses déjà effectuées par le Chauffeur
				if((nouveauChauffeur.getWorkerTimeSum())>(240+240*pause)){		//Si le Chauffeur a travaillé plus de 4h depuis sa dernière pause on effectue:
					if(nouveauChauffeur.getNombrePause()==0){					//Si le Chauffeur n'a toujours pas pris de pause
						marqueurChoix=ChoisirProchaineTache(nouveauChauffeur, fichierInstance, this.getDureePauseLegale());		//Choix de la prochaine Tache du Chauffeur avec au moins 30min de pause
						nouveauChauffeur.setNombrePause(1);						//Mise à jour du nombre de pause effectuées par le chauffeur
					}
					else{																			//Si le Chauffeur a déjà fait au moins 1 pause
						marqueurChoix=ChoisirProchaineTache(nouveauChauffeur, fichierInstance, this.getDureePauseSecondaire());	//Choix de la prochaine Tache du Chauffeur avec au moins 20min de pause
						nouveauChauffeur.setNombrePause(nouveauChauffeur.getNombrePause()+1);		//Mise à jour du nombre de pause effectuées par le chauffeur
					}
				}
				else{																	//Sinon choisir la prochaine Tache avec le moins de temps d'attente possible
					marqueurChoix=ChoisirProchaineTache(nouveauChauffeur, fichierInstance, 0);
				}
				if(marqueurChoix!=0){													//Si une Tache a été choisie l'ajouter à la liste de taches du Chauffeur, si aucune Tache ne l'a été ChoisirProchaineTache renvoie 0
					nouveauChauffeur.ajouterTache(fichierInstance, marqueurChoix-1);	//Comme ChoisirProchaineTache renvoie 0 quand elle ne choisit pas de Tache l'index de la Tache choisie renvoyé est incrémenté à chaque fois,
																						//il faut donc le décrémenter lorsque l'on veut ajouter la Tache
				}							
				if(((nouveauChauffeur.getWorkerTimeSum())>(this.getDureeLegale())) || marqueurChoix==0){	//Si le Chauffeur a dépassé la durée légale de travail ou si aucune Tache n'a été trouvé on arrête de lui ajouter des Taches
					marqueurArret=1;							//Mise à 1 du marqueur d'arrêt de la boucle
					this.TypeService(nouveauChauffeur);			//Mise à jour du nombre de chaque service de la Solution
					this.chauffeurs.add(nouveauChauffeur);		//Ajout du chauffeur à la liste de Chauffeurs de la Solution
					nouveauChauffeur.CalculerCout(this);		//Calcul du cout du Chauffeur
				}
			}													//Fin de la boucle de création d'un Chauffeurs
		}														//Fin de la boucle d'ajouts de Chauffeurs à la liste
		this.setNombreChauffeurs(i);							//Calcul du nombre total de Chauffeurs de la Solution
		this.CalculerCoutTotal();								//Calcul du cout total de la Solution
	}
	
	//Fonction choississant la prochaine Tache possible du Chauffeur c selon la durée de l'éventuelle pause à prendre
	//La fonction renvoie 0 si aucune Tache n'a été choisie et sinon l'index de la Tache choisie
	//or comme les index commencent à 0 le résultat est incrémenté pour que la première Tache ne soit pas ignorée
	public int ChoisirProchaineTache(Chauffeur c, Fichier fichierInstance, int dureePause){
		int indexTacheChoisie, heureTacheChoisie, heureTachePrecedente;		//Tampons pour les boucles de choix de la meilleure Tache
																			//heureTachePrécédente est utilisée pour assigner un nouvelle tache après la dernière   
		String lieuTachePrecedente="";
		indexTacheChoisie=heureTacheChoisie=heureTachePrecedente=0;
		
		if ((c.getTachesChauffeur()).isEmpty()){										//Si ceci est la première tache de la journée du chauffeur on prendra la première Tache de la liste(chronologiquement parlant)
			heureTacheChoisie=(fichierInstance.getTacheFichier(0)).getHeureDepart(); 	//Récupération des informations de la première Tache
			heureTachePrecedente=0;														//heureTachePrécédente est mise à 0 afin de choisir n'importe quelle Tache
			indexTacheChoisie=1;
			for (int i=1; i<(fichierInstance.getTaches().size()); i++){							//Parcourt de la liste de Tache
				if(heureTacheChoisie>(fichierInstance.getTacheFichier(i)).getHeureDepart()){	//Si la Tache commence plus tôt que celle déjà choisie alors elle devient la nouvelle Tache choisie
					indexTacheChoisie=i+1;														//Incrémentation de l'index pour ne pas avoir 0 en sortie
					heureTacheChoisie=(fichierInstance.getTacheFichier(i)).getHeureDepart();	//Mise à jour du tampon heureTacheChoisie
				}
			}
			c.TypeDeService(fichierInstance.getTacheFichier(indexTacheChoisie-1));				//Calcul du type de service du Chauffeur comme c'est sa première Tache
		}
		else{																							//Sinon on choisie une autre Tache adéquate
			heureTachePrecedente=(c.getTache((c.getTachesChauffeur()).size()-1)).getHeureArrivee();		//Récupération de l'heure d'arrivée de la Tache précédente du Chauffeur
			lieuTachePrecedente=(c.getTache((c.getTachesChauffeur()).size()-1)).getLieuArrivee();		//Récupération du lieu d'arrivée de la Tache précédente du Chauffeur
			heureTacheChoisie=1500;																		//Initialisation de cette variable à une très haute valeur
			for (int i=0; i<(fichierInstance.getTaches().size()); i++){									//Parcourt de la liste de Tache
				//Si le lieu de départ de la Tache est le même que le lieu d'arrivée précédent et si la Tache commence après la fin de la Tache précédente et si le temps d'attente entre les deux est suffisant pour l'éventuel pause on choisie la Tache
				if(lieuTachePrecedente.equals((fichierInstance.getTacheFichier(i)).getLieuDepart()) && heureTacheChoisie>(fichierInstance.getTacheFichier(i)).getHeureDepart() && (fichierInstance.getTacheFichier(i)).getHeureDepart()-heureTachePrecedente>dureePause){
					indexTacheChoisie=i+1;														//Incrémentation de l'index pour ne pas avoir 0 en sortie
					heureTacheChoisie=(fichierInstance.getTacheFichier(i)).getHeureDepart();	//Mise à jour du tampon heureTacheChoisie
				}
			}
		}
		return indexTacheChoisie;	//On renvoie l'index de la Tache choisie, si aucune Tache ne l'a été alors cette variable n'a pas été modifiée et on renvoie 0
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
	//certaines informations on été rajoutées par rapport aux fichier originaux
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
	
	//Idem de PrintSolution mais renvoie le résultat sous forme de String pour pouvoir être exploité par l'interface graphique
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
	
	//Mise à jour du nombre de total des différents types de service
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
	
	//Réinitialisation de la solution et surtout des listes "chauffeurs" et "taches" 
	//afin de pouvoir ouvrir et générer de nouvelles solutions
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
		this.taches.clear();
	}
	
	//Renvoie sous forme de String un horaire à l'origine en minutes au format: "heure h minutes"
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
