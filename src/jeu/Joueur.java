package jeu;

/**
 * 
 * @author xavier
 *
 */
public class Joueur extends Personne{

	/**
	 * Nombres de pi�ces du joueur
	 */
	private int nbrDePieces;
	
	/**
	 * Constructeur de Joueur
	 * @param nom
	 */
	public Joueur(String nom) {
		super(nom);
		this.nbrDePieces = 2;
	}

	/**
	 * Retourne le nombre de pi�ces du joueur
	 * @return nbrPieces
	 */
	public int getNbrDePieces() {
		return this.nbrDePieces;
	}
	
	/**
	 * Ajoute une pi�ce au nombre de pi�ces
	 */
	public void addPiece() {
		++nbrDePieces;
	}
	
	/**
	 * Permet de d�finir le nombre de pi�ces du joueur
	 * Fonction utile pour tester sans r�soudre les enigmes
	 * @param n
	 */
	public void setNbrPieces(int n) {
		this.nbrDePieces = n;
	}
}
