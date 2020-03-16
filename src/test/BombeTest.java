/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeu.Bombe;

/**
 * @author AbdRahim
 *
 */
class BombeTest {
	
	/**
	 * Création d'une bombe b
	 */
	static Bombe b;
	
	/**
	 * initialisation de la bombe b
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		b = new Bombe(5,00);
		System.out.println("Succès : Construction de la bombe");
	}

	/**
	 * Test method for {@link jeu.Bombe#tempsRestant()}.
	 */
	@Test
	void testTempsRestant() {
		/**
		 * Test temps restant pour la bombe b
		 */
		assertEquals(b.tempsRestant(),"Temps restant : 5:00s");
		System.out.println("Succès : Temps restant correct");
	}

	/**
	 * Test method for {@link jeu.Bombe#getCode()}.
	 */
	@Test
	void testGetCode() {
		/**
		 * Test du code 196
		 */
		assertEquals(b.getCode(),196);
		System.out.println("Succès : Code correct");
		
	}

}
