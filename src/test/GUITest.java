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
class GUITest {

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
	 * Test method for {@link jeu.GUI#afficher(java.lang.String)}.
	 */
	@Test
	void testAfficherString() {
		assertEquals(gui.afficher("N"),true);
		System.out.println("Succès : Execution de la fonction afficher avec paramètre");
	}

	/**
	 * Test method for {@link jeu.GUI#afficher()}.
	 */
	@Test
	void testAfficher() {
		assertEquals(gui.afficher(),true);
		System.out.println("Succès : Execution de la fonction Afficher sans paramètre");
	}

	/**
	 * Test method for {@link jeu.GUI#afficheImage(java.lang.String)}.
	 */
	@Test
	void testAfficheImage() {
		/**
		 * test si l'image a bien pu etre recupere
		 */
		assertEquals(gui.afficheImage("bg.png"),true);
		System.out.println("Succès : Récupération de l'image");
	}

	/**
	 * Test method for {@link jeu.GUI#enable(boolean)}.
	 */
	@Test
	void testEnable() {
		assertEquals(gui.enable(true),true);
		System.out.println("Succès : Execution de la fonction enable");
	}



}
