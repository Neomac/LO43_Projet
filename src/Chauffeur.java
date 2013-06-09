import java.util.ArrayList;

//Commit Test 2

public class Chauffeur {
	private int numeroChauffeur;
	private int workerTimeSum;
	private	int underTime;
	private int idleTime;
	private int cost;
	private ArrayList<Tache> tachesChauffeur = new ArrayList<Tache>();

	public Chauffeur(){
		numeroChauffeur=0;
		workerTimeSum=0;
		underTime=0;
		idleTime=0;
		cost=0;
	}
	
	public Chauffeur(int _numeroChauffeur, int _workerTimeSum, int _underTime, int _idleTime, int _cost){
		numeroChauffeur=_numeroChauffeur;
		workerTimeSum=_workerTimeSum;
		underTime=_underTime;
		idleTime=_idleTime;
		cost=_cost;
	}
	
	public void AjouterTacheChauffeur(Tache T){
		tachesChauffeur.add(T);
	}
	
	public void PrintChauffeur(){
		int i;
		System.out.println("----Worker "+getNumeroChauffeur()+"'s task(s)----");
		System.out.println();
		System.out.println("WorkerTimeSum="+getWorkerTimeSum());
		System.out.println("UnderTime = "+getUnderTime());
		System.out.println("IdleTime = "+getIdleTime());
		System.out.println("Cost="+getCost());
		for (i=0; i<(tachesChauffeur.size())-1; i++){
			(tachesChauffeur.get(i)).PrintTache();
			System.out.println("");
		}
		(tachesChauffeur.get(i++)).PrintTache();
	}
	
	public int getNumeroChauffeur() {
		return numeroChauffeur;
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
}
