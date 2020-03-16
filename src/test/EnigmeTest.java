/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jeu.Enigme;
import jeu.Malfaiteur;
import jeu.Zone;

/**
 * @author AbdRahim
 *
 */
class EnigmeTest {
	
	static Enigme e;
	
	/**
	 * initialisation de l'enigme
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		/**
		 * Vérification constructeur
		 */
		try
		{
			e = new Enigme("Notre travail merite t-il une bonne note ?","OUI");
			System.out.println("Succès : Construction de l'enigme");
		}
		catch(Exception e)
		{
			System.out.println("Echec : Construction de la Zone et/ou du Malfaiteur");
		}
	}

	/**
	 * Test method for {@link jeu.Enigme#toString()}.
	 */
	@Test
	void testToString() {		
		/**
		 * Verifie si la fonction renvoie bien la question
		 */
		assertEquals(e.toString(),"Notre travail merite t-il une bonne note ?");
		System.out.println("Succès : Question correct");
	}

	/**
	 * Test method for {@link jeu.Enigme#getReponse()}.
	 */
	@Test
	void testGetReponse() {		
		/**
		 * Verifie si la fonction renvoie bien la reponse
		 */
		assertEquals(e.getReponse(),"OUI");
		System.out.println("Succès : Reponse correct");
	}

	/**
	 * Test method for {@link jeu.Enigme#getIsDone()}.
	 */
	@Test
	void testGetIsDone() {		
		/**
		 * Verifie si la fonction renvoie bien que l enigme n a pas ete resolu
		 */
		assertEquals(e.getIsDone(),false);
		System.out.println("Succès : Enigme non resolu renvoie false");
	}

	/**
	 * Test method for {@link jeu.Enigme#setDone()}.
	 */
	@Test
	void testSetDone() {		
		/**
		 * Verifie si la fonction modifie bien l etat de l enigme comme resolu
		 */
		e.setDone();
		assertEquals(e.getIsDone(),true);
		System.out.println("Succès : Enigme resolu renvoie true");
	}

}
