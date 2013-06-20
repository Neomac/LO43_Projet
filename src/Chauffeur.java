import java.util.ArrayList;

//Commit Test 2

public class Chauffeur {
	private int typeService;		//Type de service, 1: matin, 2: jour, 3: soir, 4: noir
	private int numeroChauffeur;	//Numéro du Chauffeur, valeur minimale: 1, différent de l'index dans "chauffeurs" de la classe Solution
	private int workerTimeSum;		//Temps de travail total
	private	int underTime;			//Temps de travail en dessous de la durée légale
	private int overTime;			//Temps de travail au dessus de la durée légale
	private int idleTime;			//Temps d'inactivité du Chauffeur
	private int cost;				//Cout du Chauffeur
	private int heureDepart;		//heure de départ de la première tache du Chauffeur
	private int heureFin;			//heure d'arrivée de la dernière tache du Chauffeur
	private int nombrePause;		//Nombre de pauses prises par le Chauffeur
	private ArrayList<Tache> tachesChauffeur = new ArrayList<Tache>();	//Liste des taches assignées au Chauffeur dans l'ordre chronologique

	//Constructeur par défaut de Chauffeur
	public Chauffeur(){
		typeService=0;
		numeroChauffeur=0;
		workerTimeSum=0;
		underTime=0;
		overTime=0;
		idleTime=0;
		cost=0;
		nombrePause=0;
		heureDepart=0;
		heureFin=0;
	}
	
	//Constructeur de Chauffeur
	public Chauffeur(int _numeroChauffeur, int _workerTimeSum, int _underTime, int _idleTime, int _cost){
		numeroChauffeur=_numeroChauffeur;
		workerTimeSum=_workerTimeSum;
		underTime=_underTime;
		idleTime=_idleTime;
		cost=_cost;
	}
	
	//Ajoute en fin de liste une tache au chauffeur et modifie workerTimeSum et idleTime en conséquence
	public void ajouterTache(Fichier fichierInstance, int indexTache){ //Ajoute la tache numTache du fichier d'instance au chauffeur c et calcul workerTimeSum et idleTime
		this.AjouterTacheChauffeur(fichierInstance.getTacheFichier(indexTache));
		if(this.getWorkerTimeSum()==0) //Mise à jour de workerTimeSum, selon si c'est la première Tache du Chauffeur ou non
			this.setWorkerTimeSum((fichierInstance.getTacheFichier(indexTache)).getHeureArrivee()-(fichierInstance.getTacheFichier(indexTache)).getHeureDepart());
		else
			this.setWorkerTimeSum(this.getWorkerTimeSum()+((fichierInstance.getTacheFichier(indexTache)).getHeureArrivee()-(this.getTache(this.getTachesChauffeur().size()-2)).getHeureArrivee()));
		if(this.getTachesChauffeur().size()>1) //Mise à jour de idleTime si ce n'est pas sa première Tache.
			this.setIdleTime(this.getIdleTime()+(fichierInstance.getTacheFichier(indexTache)).getHeureDepart()-(this.getTache((this.getTachesChauffeur()).size()-2)).getHeureArrivee());
		(fichierInstance.getTaches()).remove(indexTache); //Efface la tache de la liste du fichier d'instance pour qu'elle ne soit pas ajouté autre part
	}
	
	//Calcul du cout de Chauffeur en fonction 
	public void CalculerCout(Solution solution){
		int differenceTemps;
		differenceTemps=solution.getDureeLegale()-this.workerTimeSum; //Calcul de la difference de temps de travail par rapport à dureeLegale
		if(differenceTemps>=0)					//Calcul de overTime ou de underTime en conséquence
			this.underTime=differenceTemps;
		else
			this.overTime=(-1)*differenceTemps;
		if(this.nombrePause!=0)			//Calcul du idleTime définitif en prenant en compte les pauses, la première correspond à la pause principale et les autres aux pauses éventuelles
			this.idleTime=this.idleTime-solution.getDureePauseLegale()-(this.nombrePause-1)*solution.getDureePauseSecondaire();
		if(this.underTime!=0)			//Calcul du cout selon idleTime et overTime ou underTime
			this.cost=this.underTime+this.idleTime;
		else
			this.cost=this.overTime+this.idleTime;
		heureFin=(this.tachesChauffeur.get(this.tachesChauffeur.size()-1)).getHeureArrivee();
		heureDepart=(this.tachesChauffeur.get(0)).getHeureDepart();
	}
	
	//Ajout du Tache à tachesChauffeur
	public void AjouterTacheChauffeur(Tache T){
		this.tachesChauffeur.add(T);
	}
	
	//Impression des informations de Chauffeur en partie selon le format des fichiers solution_X.txt fournis
	//certaines informations on été rajoutées par rapport aux fichier originaux
	public void PrintChauffeur(Solution solution){
		int i;
		System.out.println("----Worker "+getNumeroChauffeur()+"'s task(s)----");
		System.out.println();
		System.out.println("Type de service: "+this.PrintService());				//Ligne en plus par rapport aux fichiers solution
		System.out.println("WorkerTimeSum="+this.getWorkerTimeSum());
		if (this.getWorkerTimeSum()<=solution.getDureeLegale())						//Boucle if pour renvoyer overTime ou underTime selon les circonstances
			System.out.println("UnderTime = "+this.getUnderTime());
		else
			System.out.println("OverTime = "+this.getOverTime());
		System.out.println("IdleTime = "+this.getIdleTime());
		System.out.println("Nombre de pauses = "+this.getNombrePause());			//Ligne en plus par rapport aux fichiers solution
		System.out.println("Cost="+this.getCost());
		for (i=0; i<(this.tachesChauffeur.size())-1; i++){
			(this.tachesChauffeur.get(i)).PrintTache();
			System.out.println("");
		}
		(this.tachesChauffeur.get(i++)).PrintTache();
	}
	
	//Idem que PrintChauffeur mais renvoie le résultat sous forme de String pour pouvoir être exploité par l'interface graphique
	public String StringChauffeur(Solution solution){
		String resultat="";
		int i;
		resultat=resultat+"----Worker "+getNumeroChauffeur()+"'s task(s)----\n";
		resultat=resultat+"\n";
		resultat=resultat+"Type de service: "+this.PrintService()+"\n";
		resultat=resultat+"WorkerTimeSum="+this.getWorkerTimeSum()+"\n";
		if (this.getWorkerTimeSum()<=solution.getDureeLegale())
			resultat=resultat+"UnderTime = "+this.getUnderTime()+"\n";
		else
			resultat=resultat+"OverTime = "+this.getOverTime()+"\n";
		resultat=resultat+"IdleTime = "+this.getIdleTime()+"\n";
		resultat=resultat+"Nombre de pauses = "+this.getNombrePause()+"\n";
		resultat=resultat+"Cost="+this.getCost()+"\n";
		for (i=0; i<(this.tachesChauffeur.size())-1; i++){
			resultat=resultat+(this.tachesChauffeur.get(i)).StringTache();
			resultat=resultat+"\n";
		}
		resultat=resultat+(this.tachesChauffeur.get(i++)).StringTache();
		return resultat;
	}
	
	//Détermine le type de service du Chauffeur
	public void TypeDeService(Tache t){
		int heure=t.getHeureDepart();
		if (heure >= 300 && heure <= 419)	
			this.typeService=1;				//Service du matin, numérotation: 1
		else if (heure >= 420 && heure <= 1019)
			this.typeService=2;				//Service du jour, numérotation: 2
		else if (heure >= 1020 && heure <= 1199)
			this.typeService=3;				//Service du soir, numérotation: 3
		else if (heure >= 1200 && heure <= 1440)
			this.typeService=4;				//Service de nuit, numérotation: 4
	}
	
	//Renvoie le type de service sous forme de String pour être utilisé par l'interface graphique
	public String PrintService(){
		String result="";
		if(this.getTypeService()==1)
			return result="Service du matin";
		else if(this.getTypeService()==2)
			return result="Service du jour";
		else if(this.getTypeService()==3)
			return result="Service du soir";
		else if(this.getTypeService()==4)
			return result="Service de nuit";
		return result;
	}
	
	//Renvoie sous forme de String les numéros des taches assignées au Chauffeur sous forme de String pour être utilisé par l'interface graphique
	public String getNumerosTaches(){
		String resultat="";
		for(int i=0; i<this.tachesChauffeur.size();i++){
			resultat=resultat+"tache numero: "+(this.getTache(i)).getNumeroTache()+"\n";
		}
		return resultat;
	}
	
	//Assesseurs de la classe Chauffeur
	public Tache getTache(int i){
		return this.tachesChauffeur.get(i);
	}
	
	public int getNombreTaches(){
		return this.tachesChauffeur.size();
	}
	
	public int getNumeroChauffeur() {
		return numeroChauffeur;
	}
	public int getTypeService() {
		return typeService;
	}
	public ArrayList<Tache> getTachesChauffeur() {
		return tachesChauffeur;
	}
	public void setTypeService(int typeService) {
		this.typeService = typeService;
	}
	public void setTachesChauffeur(ArrayList<Tache> tachesChauffeur) {
		this.tachesChauffeur = tachesChauffeur;
	}
	public int getWorkerTimeSum() {
		return workerTimeSum;
	}
	public int getUnderTime() {
		return underTime;
	}
	public int getIdleTime() {
		return idleTime;
	}
	public int getCost() {
		return cost;
	}

	public void setNumeroChauffeur(int numeroChauffeur) {
		this.numeroChauffeur = numeroChauffeur;
	}
	public void setWorkerTimeSum(int workerTimeSum) {
		this.workerTimeSum = workerTimeSum;
	}
	public void setUnderTime(int underTime) {
		this.underTime = underTime;
	}
	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public int getNombrePause() {
		return nombrePause;
	}

	public void setNombrePause(int _nombrePause) {
		this.nombrePause = _nombrePause;
	}

	public int getHeureDepart() {
		return heureDepart;
	}

	public int getHeureFin() {
		return heureFin;
	}

	public void setHeureDepart(int heureDepart) {
		this.heureDepart = heureDepart;
	}

	public void setHeureFin(int heureFin) {
		this.heureFin = heureFin;
	}
	
	
	
}
