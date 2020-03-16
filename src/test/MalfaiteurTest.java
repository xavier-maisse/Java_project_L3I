/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jeu.Enigme;
import jeu.Malfaiteur;
import jeu.Personne;

/**
 * @author AbdRahim
 *
 */
class MalfaiteurTest {
	
	static Enigme e;
	static Malfaiteur m;
	
	/**
	 * initialisation de l'Enigme et du Malfaiteur
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		/**
		 * Vérification constructeur
		 */
		try
		{
			e = new Enigme("Ca va ?","Oui");
			m = new Malfaiteur("Abd",e);
			System.out.println("Succès : Construction de l'Enigme et du Malfaiteur");
		}
		catch(Exception e)
		{
			System.out.println("Echec : Construction de l'Enigme et/ou du Malfaiteur");
		}
	}

	/**
	 * Test method for {@link jeu.Malfaiteur#Malfaiteur(java.lang.String, jeu.Enigme)}.
	 */
	@Test
	void testMalfaiteur() {		
		/**
		 * Verifie si le malfaiteur a bien ete ajoute avec son enigme
		 */
		assertEquals(m.getNom(),"Abd");
		assertEquals(m.getEnigme(),e);
		System.out.println("Succès : L'énigme est bien associée au bon malfaiteur");
	}

	/**
	 * Test method for {@link jeu.Malfaiteur#getEnigme()}.
	 */
	@Test
	void testGetEnigme() {
		/**
		 * Verifie si l enigme du malfaiteur est celle qui a ete ajoute
		 */
		assertEquals(m.getEnigme(),e);
		System.out.println("Succès : L'énigme correspond bien a celle ajoutée");
	}

}
