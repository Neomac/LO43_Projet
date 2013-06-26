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

    //Assesseurs de Tache
	public int getNumeroTache() {
		return numeroTache;
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

    public int getNumeroChauffeur() {
		return numeroChauffeur;
	}

}
