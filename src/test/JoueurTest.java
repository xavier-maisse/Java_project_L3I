/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jeu.Joueur;

/**
 * @author AbdRahim
 *
 */
class JoueurTest {
	
	static Joueur j1;
	static Joueur j2;

	/**
	 * @throws java.lang.Exception
	 * initialisation du Joueur
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		/**
		 * Vérification constructeur
		 */
		try
		{
			j1 = new Joueur("Abd");
			j2 = new Joueur("Rahim");
			System.out.println("Succès : Construction du Joueur");
		}
		catch(Exception e)
		{
			System.out.println("Echec : Construction du Joueur");
		}
	}

	/**
	 * Test method for {@link jeu.Joueur#getNbrDePieces()}.
	 */
	@Test
	void testGetNbrDePieces() {
		/**
		 * Test pour avoir le nombre de piece
		 */
		assertEquals(j1.getNbrDePieces(),0);
		System.out.println("Succès : le nombre de piècs est recupere");
		
	}
	
	/**
	 * Test method for {@link jeu.Joueur#setNbrPieces(int)}.
	 */
	@Test
	void testSetNbrPieces() {
		/**
		 * Test pour modifier le nombre de piece
		 */
		j1.setNbrPieces(19);
		assertEquals(j1.getNbrDePieces(),19);
		System.out.println("Succès : Modification du nombre de pièce");
	}

	/**
	 * Test method for {@link jeu.Joueur#addPiece()}.
	 */
	@Test
	void testAddPiece() {
		/**
		 * Test pour ajouter une piece
		 */
		j2.addPiece(); /** Ajout de la piece */
		assertEquals(j2.getNbrDePieces(),1);
		System.out.println("Succès : Ajout d'une pièce");
	}

}
