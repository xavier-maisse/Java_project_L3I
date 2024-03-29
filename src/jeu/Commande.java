package jeu;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xavier
 *
 */
public enum Commande {
	NORD("N", "N (aller a la sortie nord)"), 
	SUD("S", "S (aller a la sortie sud)"), 
	EST("E", "E (aller a la sortie est)"), 
	OUEST("O", "O (aller a la sortie ouest)"), 
	AIDE("?", "? (aide)"), 
	QUITTER("P", "P (quitter)"),
    DEMANDER("DEMANDER", "DEMANDER (Demande l'enigme de la salle)"),
    CREDIT("CREDIT", "CREDIT (Nombre de pi�ces)"),
    REPONSE("REPONSE", "REPONSE + la r�ponse (Permet de r�ponde � l'�nigme)"),
    DEFUSE("DEFUSE", "DEFUSE + le code (Permet de d�samorcer la bombe)"),
    GONFLER("GONFLER", "GONFLER  (Permet d'activer la machine � ballon)"),
    RETOUR("R", "R(RETOUR)"),
    SOLUTION("T", "T (Fini le jeu)");

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
	 * @return list des descriptions.
	 */
	public static List<String> toutesLesDescriptions() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.description);
		}
		return resultat;
	}
	
	/**
	 * Retourne une liste de toutes les abrevations des commandes.
	 * @return list des abrevations.
	 */
	public static List<String> toutesLesAbreviations() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.abreviation);
		}
		return resultat;
	}
	
	/**
	 * Retourne les noms des commandes.
	 * @return list des noms des commandes.
	 */
	public static List<String> tousLesNoms() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.name());
		}
		return resultat;
	}

}
