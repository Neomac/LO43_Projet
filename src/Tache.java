

public class Tache {
	private int numeroTache;
	private int HeureDepart; 	//En minutes
	private int HeureArrivee;	//En minutes
	private String LieuDepart;	
	private String LieuArrivee;
	

	public Tache(){
		HeureDepart = HeureArrivee = 0;
		LieuDepart = LieuArrivee = "";
	}
	
	public Tache(int _numeroTache, int _heureDepart, int _heureArrivee, String _lieuDepart, String _lieuArrivee) {
		numeroTache = _numeroTache;
		HeureDepart = _heureDepart;
		HeureArrivee = _heureArrivee;
		LieuDepart = _lieuDepart;
		LieuArrivee = _lieuArrivee;
	}
	
	public void PrintTache(){
		System.out.println("Task:"+getNumeroTache()+"\tstartTime:"+getHeureDepart()+"\tfinishTime:"+getHeureArrivee()+getLieuDepart()+getLieuArrivee());
	}
	
	public String HeureDepartTache(){
		String resultat;
		return resultat=((this.HeureDepart)%60)+" heures "+(this.HeureDepart-((this.HeureDepart)%60)*60)+" minutes";
	}
	
	public String HeureArriveeTache(){
		String resultat;
		return resultat=((this.HeureArrivee)%60)+" heures "+(this.HeureArrivee-((this.HeureArrivee)%60)*60)+" minutes";
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
	
}
