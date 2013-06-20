import java.math.*;

public class Tache {
	private int numeroTache;
	private int numeroChauffeur;				//Num�ro du chauffeur auquel la tache est assign�e, par d�faut � 0
	private int HeureDepart; 	//En minutes
	private int HeureArrivee;	//En minutes
	private String LieuDepart;	
	private String LieuArrivee;
	
	//Constructeur par d�faut de Tache
	public Tache(){
		numeroChauffeur=numeroChauffeur=HeureDepart = HeureArrivee = 0;
		LieuDepart = LieuArrivee = "";
	}
	
	//Constructeur de Tache
	public Tache(int _numeroTache,int _numeroChauffeur, int _heureDepart, int _heureArrivee, String _lieuDepart, String _lieuArrivee) {
		numeroTache = _numeroTache;
		numeroChauffeur = _numeroChauffeur;		//Mettre � 0 si un chauffeur n'est pas attribu�
		HeureDepart = _heureDepart;
		HeureArrivee = _heureArrivee;
		LieuDepart = _lieuDepart;
		LieuArrivee = _lieuArrivee;
	}
	
	//Imprime les attributs de la Tache dans la console
	public void PrintTache(){
		System.out.println("Task:"+getNumeroTache()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee());
	}
	
	//Renvoie sous forme de String les m�mes informations que la fonction PrintTache, utilis� pour afficher dans l'interface graphique
	public String StringTache(){
		return "Task:"+getNumeroTache()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee()+"\n";
	}
	
	//Idem que StringTache mais renvoie en plus le num�ro du Chauffeur assign� � la Tache
	public String StringTacheChauffeur(){
		return "Task:"+getNumeroTache()+"\tChauffeur"+getNumeroChauffeur()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee()+"\n";
	}
	
	//Renvoie le Chauffeur associ� � la Tache de la solution
	public Chauffeur getChauffeurTache(Solution solution){
		return solution.getChauffeur(this.numeroChauffeur-1);
	}
	
	//Assesseurs de Tache
	public int getNumeroTache() {
		return numeroTache;
	}
	public void setNumeroTache(int numeroTache) {
		this.numeroTache = numeroTache;
	}
	public int getHeureDepart() {
		return HeureDepart;
	}
	public int getHeureArrivee() {
		return HeureArrivee;
	}
	public String getLieuDepart() {
		return LieuDepart;
	}
	public String getLieuArrivee() {
		return LieuArrivee;
	}
	public void setHeureDepart(int heureDepart) {
		HeureDepart = heureDepart;
	}
	public void setHeureArrivee(int heureArrivee) {
		HeureArrivee = heureArrivee;
	}
	public void setLieuDepart(String lieuDepart) {
		LieuDepart = lieuDepart;
	}
	public void setLieuArrivee(String lieuArrivee) {
		LieuArrivee = lieuArrivee;
	}
	public int getNumeroChauffeur() {
		return numeroChauffeur;
	}
	public void setNumeroChauffeur(int numeroChauffeur) {
		this.numeroChauffeur = numeroChauffeur;
	}
	
}
