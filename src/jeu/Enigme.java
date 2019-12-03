package jeu;

public class Enigme {

	private String question;
	private String reponse;
	private boolean isDone = false;
	
	public Enigme(String q, String r) {
		this.question = q;
		this.reponse = r;
	}
	
	@Override
	public String toString() {
		return this.question;
	}
	
	public String getReponse() {
		return this.reponse;
	}
	
	public boolean getIsDone() {
		return this.isDone;
	}
	
	public void setDone() {
		this.isDone = true;
	}
}
