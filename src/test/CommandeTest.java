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
		"[N (aller a la sortie nord), S (aller a la sortie sud), E (aller a la sortie est), O (aller a la sortie ouest), ? (aide), P (quitter), DEMANDER (Demande l'enigme de la salle), CREDIT (Nombre de pièces), REPONSE + la réponse (Permet de réponde à l'énigme), DEFUSE + le code (Permet de désamorcer la bombe), GONFLER  (Permet d'activer la machine à ballon), R(RETOUR), T (Fini le jeu)]");
		System.out.println("Succès : Toutes les descriptions");
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
		System.out.println("Succès : Toutes les abréviations");
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
		System.out.println("Succès : Toutes les directions");
	}

}
