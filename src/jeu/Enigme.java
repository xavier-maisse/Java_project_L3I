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
	 * Attribut contenant la reponse � la question
	 */
	private String reponse;
	
	/**
	 * Attribut qui permet de savoir si le joueur a d�j� repondu ou
	 * non � l'�nigme
	 */
	private boolean isDone = false;
	
	/**
	 * Constructeur d'�nigme
	 * @param q
	 * @param r
	 */
	public Enigme(String q, String r) {
		this.question = q;
		this.reponse = r;
	}
	
	/**
	 * Retourne la question de l'�nigme
	 */
	@Override
	public String toString() {
		return this.question;
	}
	
	/**
	 * Retourne la r�ponse de l'�nigme
	 * @return reponse
	 */
	public String getReponse() {
		return this.reponse;
	}
	
	/**
	 * Retourne si l'�nigme a d�j� �tait faite
	 * @return isDone
	 */
	public boolean getIsDone() {
		return this.isDone;
	}
	
	/**
	 * Permet d'affecter l'enigme � "r�aliser"
	 */
	public void setDone() {
		this.isDone = true;
	}
}
