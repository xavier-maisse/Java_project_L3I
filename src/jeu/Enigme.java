package jeu;

/**
 * 
 * @author xavier
 *
 */
public class Enigme {

	/**
	 * Attribut contenant la question
	 */
	private String question;
	
	/**
	 * Attribut contenant la reponse à la question
	 */
	private String reponse;
	
	/**
	 * Attribut qui permet de savoir si le joueur a déjà repondu ou
	 * non à l'énigme
	 */
	private boolean isDone = false;
	
	/**
	 * Constructeur d'énigme
	 * @param q
	 * @param r
	 */
	public Enigme(String q, String r) {
		this.question = q;
		this.reponse = r;
	}
	
	/**
	 * Retourne la question de l'énigme
	 */
	@Override
	public String toString() {
		return this.question;
	}
	
	/**
	 * Retourne la réponse de l'énigme
	 * @return reponse
	 */
	public String getReponse() {
		return this.reponse;
	}
	
	/**
	 * Retourne si l'énigme a déjà était faite
	 * @return isDone
	 */
	public boolean getIsDone() {
		return this.isDone;
	}
	
	/**
	 * Permet d'affecter l'enigme à "réaliser"
	 */
	public void setDone() {
		this.isDone = true;
	}
}
