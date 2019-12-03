package jeu;

public class Malfaiteur extends Personne{
	private Enigme enigme;
	
	public Malfaiteur(String nom, Enigme eni) {
		super(nom);
		this.enigme = eni;
	}
	
	
	public Enigme getEnigme() {
		return this.enigme;
	}
}
