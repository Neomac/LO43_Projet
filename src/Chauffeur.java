import java.util.ArrayList;

//Commit Test 2

public class Chauffeur {
	private int typeService;
	private int numeroChauffeur;
	private int workerTimeSum;
	private	int underTime;
	private int overTime;
	private int idleTime;
	private int cost;
	private int nombrePause;
	private ArrayList<Tache> tachesChauffeur = new ArrayList<Tache>();

	public Chauffeur(){
		typeService=0;
		numeroChauffeur=0;
		workerTimeSum=0;
		underTime=0;
		overTime=0;
		idleTime=0;
		cost=0;
		nombrePause=0;
	}
	
	public Chauffeur(int _numeroChauffeur, int _workerTimeSum, int _underTime, int _idleTime, int _cost){
		numeroChauffeur=_numeroChauffeur;
		workerTimeSum=_workerTimeSum;
		underTime=_underTime;
		idleTime=_idleTime;
		cost=_cost;
	}
	
	public void ajouterTache(Fichier fichierInstance, int indexTache){ //Ajoute la tache numTache du fichier d'instance au chauffeur c et calcul workerTimeSum et idleTime
		this.AjouterTacheChauffeur(fichierInstance.getTacheFichier(indexTache));
		this.setWorkerTimeSum(this.getWorkerTimeSum()+(fichierInstance.getTacheFichier(indexTache)).getHeureArrivee()-(fichierInstance.getTacheFichier(indexTache)).getHeureDepart());
		if(this.getTachesChauffeur().size()>1)
			this.setIdleTime(this.getIdleTime()+(fichierInstance.getTacheFichier(indexTache)).getHeureDepart()-(this.getTache((this.getTachesChauffeur()).size()-2)).getHeureArrivee());
		(fichierInstance.getTaches()).remove(indexTache);
	}
	
	public void CalculerCout(Solution solution){
		int differenceTemps;
		differenceTemps=solution.getDureeLegale()-this.workerTimeSum;
		if(differenceTemps>=0)
			this.underTime=differenceTemps;
		else
			this.overTime=(-1)*differenceTemps;
		if(this.nombrePause!=0)
			this.idleTime=this.idleTime-solution.getDureePauseLegale()-(this.nombrePause-1)*solution.getDureePauseSecondaire();
		if(this.underTime!=0)
			this.cost=this.underTime+this.idleTime;
		else
			this.cost=this.overTime+this.idleTime;
	}
	
	public void AjouterTacheChauffeur(Tache T){
		this.tachesChauffeur.add(T);
	}
	
	public void PrintChauffeur(Solution solution){
		int i;
		System.out.println("----Worker "+getNumeroChauffeur()+"'s task(s)----");
		System.out.println();
		System.out.println("Type de service: "+this.PrintService());				//Ligne en plus par rapport au fichiers solution
		System.out.println("WorkerTimeSum="+this.getWorkerTimeSum());
		if (this.getWorkerTimeSum()<=solution.getDureeLegale())
			System.out.println("UnderTime = "+this.getUnderTime());
		else
			System.out.println("OverTime = "+this.getOverTime());
		System.out.println("IdleTime = "+this.getIdleTime());
		System.out.println("Nombre de pauses = "+this.getNombrePause());
		System.out.println("Cost="+this.getCost());
		for (i=0; i<(this.tachesChauffeur.size())-1; i++){
			(this.tachesChauffeur.get(i)).PrintTache();
			System.out.println("");
		}
		(this.tachesChauffeur.get(i++)).PrintTache();
	}
	
	public void TypeDeService(Tache t){
		int heure=t.getHeureDepart();
		if (heure >= 300 && heure <= 419)	
			this.typeService=1;				//Service du matin, numŽrotation: 1
		else if (heure >= 420 && heure <= 1019)
			this.typeService=2;				//Service du jour, numŽrotation: 2
		else if (heure >= 1020 && heure <= 1199)
			this.typeService=3;				//Service du soir, numŽrotation: 3
		else if (heure >= 1200 && heure <= 1440)
			this.typeService=4;				//Service de nuit, numŽrotation: 4
	}
	
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
	
}
