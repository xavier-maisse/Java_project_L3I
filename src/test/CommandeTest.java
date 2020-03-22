/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu.Commande;

/**
 * @author AbdRahim
 *
 */
class CommandeTest {

	@Test
	void testToutesLesDescriptions() {
		
		/**
		 * Verifie si la fonction renvoie bien la description
		 */
		assertEquals(Commande.toutesLesDescriptions().toString(),
		"[N (aller a la sortie nord), S (aller a la sortie sud), E (aller a la sortie est), O (aller a la sortie ouest), ? (aide), P (quitter), DEMANDER (Demande l'enigme de la salle), CREDIT (Nombre de pi�ces), REPONSE + la r�ponse (Permet de r�ponde � l'�nigme), DEFUSE + le code (Permet de d�samorcer la bombe), GONFLER  (Permet d'activer la machine � ballon), R(RETOUR), T (Fini le jeu)]");
		System.out.println("Succ�s : Toutes les descriptions");
	}
	/**
	 * Test method for {@link jeu.Commande#toutesLesAbreviations()}.
	 */
	@Test
	void testToutesLesAbreviations() {
		
		/**
		 * Verifie si la fonction renvoie bien les abreviations
		 */
		assertEquals(Commande.toutesLesAbreviations().toString(),"[N, S, E, O, ?, P, DEMANDER, CREDIT, REPONSE, DEFUSE, GONFLER, R, T]");
		System.out.println("Succ�s : Toutes les abr�viations");
	}

	/**
	 * Test method for {@link jeu.Commande#tousLesNoms()}.
	 */
	@Test
	void testTousLesNoms() {
		
		/**
		 * Verifie si la fonction renvoie bien les tous les noms
		 */
		assertEquals(Commande.tousLesNoms().toString(),"[NORD, SUD, EST, OUEST, AIDE, QUITTER, DEMANDER, CREDIT, REPONSE, DEFUSE, GONFLER, RETOUR, SOLUTION]");
		System.out.println("Succ�s : Toutes les directions");
	}

}
