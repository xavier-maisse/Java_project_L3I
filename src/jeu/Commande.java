package jeu;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xavier
 *
 */
public enum Commande {
	NORD("N", "N (aller Ã  la sortie nord)"), 
	SUD("S", "S (aller Ã  la sortie sud)"), 
	EST("E", "E (aller Ã  la sortie est)"), 
	OUEST("O", "O (aller Ã  la sortie ouest)"), 
	AIDE("?", "? (aide)"), 
	QUITTER("P", "P (quitter)"),
    DEMANDER("DEMANDER", "DEMANDER (Demande l'enigme de la salle)"),
    CREDIT("CREDIT", "CREDIT (Nombre de pièces)"),
    REPONSE("REPONSE", "REPONSE + la réponse (Permet de réponde à l'énigme)"),
    DEFUSE("DEFUSE", "DEFUSE + le code (Permet de désamorcer la bombe)"),
    GONFLER("GONFLER", "GONFLER  (Permet d'activer la machine à ballon)");
    

	/**
	 * Abrevation de la commande
	 */
	private String abreviation;
	
	/**
	 * Description de la commande
	 */
	private String description;
	
	/**
	 * Une commande prend en parametre une abrevation et une description
	 * @param c
	 * @param d
	 */
	private Commande(String c, String d ) { 
		abreviation = c;
		description = d; 
	}
	
	/**
	 * Retourne le nom
	 */
	@Override
	public String toString() { 
		return name();
	}
	
	/**
	 * Retourne une list des toutes les descriptions des commandes
	 * @return
	 */
	public static List<String> toutesLesDescriptions() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.description);
		}
		return resultat;
	}
	
	/**
	 * Retourne une liste de toutes les abrevations des commandes
	 * @return
	 */
	public static List<String> toutesLesAbreviations() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.abreviation);
		}
		return resultat;
	}
	
	public static List<String> tousLesNoms() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.name());
		}
		return resultat;
	}

}
