package jeu;

/**
 * 
 * @author xavier
 *
 */
public class Personne {

	/**
	 * Nom de la personne
	 */
	private String nom;

	
	/**
	 * Constructeur de personne
	 * @param nom
	 */
	public Personne(String nom) {
		this.nom= nom;
	}
	
	/**
	 * Retourne le nom 
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}
}
