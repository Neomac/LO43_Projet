
public class main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    
    System.out.println("Résultat de la lecture du fichier:");
    
    Fichier newInstance = new Fichier();
    newInstance.LectureInstance();
    newInstance.PrintArray();
    
    String fichierSolution = "Solution_2.txt";
    System.out.println("Lecture du fichier solution");
    Solution testSolution = new Solution();
    //testSolution.GenerationSolution(newInstance);
    testSolution.LectureSolution(fichierSolution);
    testSolution.PrintSolution();
    Fenetre2 affiche = new Fenetre2();
    
    //Testing!!!!!!
    
    System.out.println();
    
    //String fichierConfiguration="Fichier_Configuration.txt";
    //testSolution.LectureConfiguration(fichierConfiguration);
    //testSolution.PrintConfiguration();
  }

}
