import java.math.*;

public class Tache {
	private int numeroTache;
	private int numeroChauffeur;
	private int HeureDepart; 	//En minutes
	private int HeureArrivee;	//En minutes
	private String LieuDepart;	
	private String LieuArrivee;
	

	public Tache(){
		numeroChauffeur=HeureDepart = HeureArrivee = 0;
		LieuDepart = LieuArrivee = "";
	}
	
	public Tache(int _numeroTache,int _numeroChauffeur, int _heureDepart, int _heureArrivee, String _lieuDepart, String _lieuArrivee) {
		numeroTache = _numeroTache;
		numeroChauffeur = _numeroChauffeur;		//Mettre à 0 si un chauffeur n'est pas attribué
		HeureDepart = _heureDepart;
		HeureArrivee = _heureArrivee;
		LieuDepart = _lieuDepart;
		LieuArrivee = _lieuArrivee;
	}
	
	public void PrintTache(){
		System.out.println("Task:"+getNumeroTache()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee());
	}
	
	public String StringTacheChauffeur(){
		return "Task:"+getNumeroTache()+"\tChauffeur"+getNumeroChauffeur()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee()+"\n";
	}
	
	public String StringTache(){
		return "Task:"+getNumeroTache()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee()+"\n";
	}
	
	public Chauffeur getChauffeurTache(Solution solution){
		return solution.getChauffeur(this.numeroChauffeur-1);
	}
	
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
