import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//Commit Test 2

public class Chauffeur {
	private int numero;
	private int workerTime;
	private	int underTime;
	private int idleTime;
	private int cost;
	protected static ArrayList<Tache> tachesChauffeur = new ArrayList<Tache>();

	
	public int getNumero() {
		return numero;
	}
	public int getWorkerTime() {
		return workerTime;
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
	public ArrayList<Tache> getTachesChauffeur() {
		return tachesChauffeur;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public void setWorkerTime(int workerTime) {
		this.workerTime = workerTime;
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
	public void setTachesChauffeur(ArrayList<Tache> tachesChauffeur) {
		this.tachesChauffeur = tachesChauffeur;
	}
}
