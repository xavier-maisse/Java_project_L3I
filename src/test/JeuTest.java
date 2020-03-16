/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jeu.GUI;
import jeu.Jeu;
import jeu.Joueur;

/**
 * @author AbdRahim
 *
 */
class JeuTest {

	static Jeu jeu;
	static GUI gui;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jeu = new Jeu(new Joueur("AbdRahim"));
		gui = new GUI( jeu);
		jeu.setGUI( gui);
	}

	/**
	 * Test method for {@link jeu.Jeu#setGUI(jeu.GUI)}.
	 */
	@Test
	void testSetGUI() {
		assertEquals(jeu.setGUI(gui),true);
		System.out.println("Succès : Execution de la fonction setGui");
	}

	/**
	 * Test method for {@link jeu.Jeu#traiterCommande(java.lang.String)}.
	 */
	@Test
	void testTraiterCommande() {
		/**
		 * Création d'une bombe b
		 */
		assertEquals("?",jeu.traiterCommande("?"));
		assertEquals("NORD",jeu.traiterCommande("Z"));
		assertEquals("DEFAUT",jeu.traiterCommande("renvijer"));
		System.out.println("Succès : Execution de la fonction traiterCommande, switch fonctionnel");
	}

}
