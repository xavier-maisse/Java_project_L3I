package jeu;

/**
 * 
 * @author xavier
 *
 */
public class Malfaiteur extends Personne{
	
	/**
	 * Un malfaiteur poss�de une enigme
	 */
	private Enigme enigme;
	
	/**
	 * Constructeur de malfaiteur
	 * @param nom
	 * @param eni
	 */
	public Malfaiteur(String nom, Enigme eni) {
		super(nom);
		this.enigme = eni;
	}
	
	/**
	 * Retourne l'enigme du malfaiteur
	 * @return enigme
	 */
	public Enigme getEnigme() {
		return this.enigme;
	}
}
