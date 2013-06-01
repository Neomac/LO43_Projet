
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Fichier testFichier = new Fichier();
		testFichier.LectureFichier();
		TrieTache1er listeOrganise = new TrieTache1er();
		listeOrganise.TrieTache();
		listeOrganise.PrintListeTache();
	}

}
