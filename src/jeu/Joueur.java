package jeu;

/**
 * 
 * @author xavier
 *
 */
public class Joueur extends Personne{

	/**
	 * Nombres de pièces du joueur
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
	 * Retourne le nombre de pièces du joueur
	 * @return nbrPieces
	 */
	public int getNbrDePieces() {
		return this.nbrDePieces;
	}
	
	/**
	 * Ajoute une pièce au nombre de pièces
	 */
	public void addPiece() {
		++nbrDePieces;
	}
	
	/**
	 * Permet de définir le nombre de pièces du joueur
	 * Fonction utile pour tester sans résoudre les enigmes
	 * @param n
	 */
	public void setNbrPieces(int n) {
		this.nbrDePieces = n;
	}
}
