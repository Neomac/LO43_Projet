

public class Tache {
	private int HeureDepart; 	//En minutes
	private int HeureArrivee;	//En minutes
	private String LieuDepart;	
	private String LieuArrivee;
	
	
	public Tache(int heureDepart, int heureArrivee, String lieuDepart, String lieuArrivee) {
		HeureDepart = heureDepart;
		HeureArrivee = heureArrivee;
		LieuDepart = lieuDepart;
		LieuArrivee = lieuArrivee;
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
