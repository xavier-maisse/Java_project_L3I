package jeu;

public class Joueur extends Personne{

	private int nbrDePieces;
	
	public Joueur(String nom) {
		super(nom);
		this.nbrDePieces = 0;
	}

	public int getNbrDePieces() {
		return this.nbrDePieces;
	}
	
	public void addPiece() {
		++nbrDePieces;
	}
	
	public void setNbrPieces(int n) {
		this.nbrDePieces = n;
	}
}
