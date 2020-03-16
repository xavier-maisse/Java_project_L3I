/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jeu.Enigme;
import jeu.Personne;

/**
 * @author AbdRahim
 *
 */
class PersonneTest {
	
	static Personne p;
	
	/**
	 * initialisation de la Personne
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		/**
		 * Vérification constructeur
		 */
		try
		{
			p = new Personne("Abd");
			System.out.println("Succès : Construction de la Persone");
		}
		catch(Exception e)
		{
			System.out.println("Echec : Construction de la Personne");
		}
	}

	/**
	 * Test method for {@link jeu.Personne#getNom()}.
	 */
	@Test
	void testGetNom() {		
		/**
		 * Verifie si la fonction recupere le bon nom
		 */
		assertEquals(p.getNom(), "Abd");
		System.out.println("Succès : Le nom récupéré est bien celui de la Personne ajoutée");
	}

}