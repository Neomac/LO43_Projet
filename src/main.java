
public class main {

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    
    System.out.println("RŽsultat de la lecture du fichier:");
    
    //Fichier testFichier = new Fichier();
    //testFichier.LectureInstance();
    //testFichier.PrintArray();
    
    String fichierSolution = "Solution_1.txt";
    System.out.println("Lecture du fichier solution");
    Solution testSolution = new Solution();
    testSolution.LectureSolution(fichierSolution);
    testSolution.PrintSolution();
    

  }

}
