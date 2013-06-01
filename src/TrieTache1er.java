import java.util.ArrayList;


public class TrieTache1er extends Fichier {
	
	private static ArrayList<Tache> listeTacheOrga;

	public TrieTache1er TrieTache(){
		TrieTache1er resultat = new TrieTache1er();
		Fichier base = new Fichier();
		base.LectureFichier();
		resultat.listeTacheOrga.add(base.Taches.get(0));
		
		int cursorListeOrga = 0;
		int cursorBase = 0;
		Tache temp = base.Taches.get(0);
		while (cursorBase < base.Taches.size()){ // Parcours de la base
			if (listeTacheOrga.get(cursorListeOrga).getLieuArrivee() == base.Taches.get(cursorBase).getLieuDepart()){  // Regarde sur le lieu d'arrivŽ est bien le lieu de depart
				if (temp.getHeureDepart()-listeTacheOrga.get(cursorListeOrga).getHeureArrivee() > base.Taches.get(cursorBase).getHeureDepart() - listeTacheOrga.get(cursorListeOrga).getHeureArrivee()){  // Regarde si le temps d'attente est meilleur que l'actuel
					temp = listeTacheOrga.get(cursorListeOrga);
				}
				else cursorBase ++;
			}
			else cursorBase ++;
			resultat.listeTacheOrga.add(temp);  // Ajout de temp ˆ la listeOrga
		}
		return resultat;
	}
	
	public static void PrintListeTache(){
		for (int i=0; i<listeTacheOrga.size(); i++){
			(listeTacheOrga.get(i)).PrintTache();
		}
	}
	
	
	public TrieTache1er(){
		ArrayList liste = new ArrayList<Tache>();
		Tache temp = new Tache(0, 0, "", "");
		listeTacheOrga = liste;
		//listeTacheOrga.add(temp);
	}
	
	public ArrayList<Tache> getListeTacheOrga() {
		return listeTacheOrga;
	}

	public void setListeTacheOrga(ArrayList<Tache> listeTacheOrga) {
		this.listeTacheOrga = listeTacheOrga;
	}
	
	
	
	
}
