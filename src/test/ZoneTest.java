/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jeu.Bombe;
import jeu.Enigme;
import jeu.Malfaiteur;
import jeu.Sortie;
import jeu.Zone;

/**
 * @author AbdRahim
 *
 */
class ZoneTest {
	
	
	static Malfaiteur m1;
	static Zone z1;
	
	/**
	 * initialisation de la Zone et du Malfaiteur
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		/**
		 * Vérification constructeur
		 */
		try
		{
			m1 = new Malfaiteur("Abd",new Enigme("Quel est le plus grand chiffre du monde ?","9"));
			z1 = new Zone("Aix","provence.jpg", m1 );
			System.out.println("Succès : Construction du Malfaiteur et de la Zone");
		}
		catch(Exception e)
		{
			System.out.println("Echec : Construction de la Zone et/ou du Malfaiteur");
		}
	}

	/**
	 * Test method for {@link jeu.Zone#getMalfaiteur()}.
	 */
	@Test
	void testGetMalfaiteur() {		
		/**
		 * Verifie si le malfaiteur m1 a bien ete inserer dans la bonne zone
		 */
		assertEquals(z1.getMalfaiteur(),m1);
		System.out.println("Succès : Malfaiteur insérer dans la bonne zone");
	}

	/**
	 * Test method for {@link jeu.Zone#ajouteSortie(jeu.Sortie, jeu.Zone)}.
	 */
	@Test
	void testAjouteSortie() {
		Zone z2 = new Zone("France","france.jpg");
		
		z1.ajouteSortie(Sortie.SUD, z2);
		
		/**
		 * Verifie si la sortie SUD est bien celle defini precedement
		 */
		assertEquals(z1.obtientSortie("SUD"),z2);
		System.out.println("Succès : Sortie ajouté");
	}

	/**
	 * Test method for {@link jeu.Zone#nomImage()}.
	 */
	@Test
	void testNomImage() {
		/**
		 * Verifie si le nom est correct
		 */
		assertEquals(z1.nomImage(),"provence.jpg");
		System.out.println("Succès : Nom image correct");
	}

	/**
	 * Test method for {@link jeu.Zone#toString()}.
	 */
	@Test
	void testToString() {		
		/**
		 * Verifie si la description sont correct
		 */
		assertEquals(z1.toString(),"Aix");
		System.out.println("Succès : Desctiption correct");
	}

	/**
	 * Test method for {@link jeu.Zone#descriptionLongue()}.
	 */
	@Test
	void testDescriptionLongue() {
		Zone z2 = new Zone("Marseille","marseille.jpg");
		
		z1.ajouteSortie(Sortie.SUD, z2);
		
		/**
		 * Verifie si la description longue est correct
		 */
		assertEquals(z1.descriptionLongue(),"Vous êtes dans " + z1.toString() + "\nSorties : [SUD]");
		System.out.println("Succès : Description longue correct");
	}

	/**
	 * Test method for {@link jeu.Zone#obtientSortie(java.lang.String)}.
	 */
	@Test
	void testObtientSortie() {
		Zone z2 = new Zone("Marseille","marseille.jpg");
		
		z1.ajouteSortie(Sortie.SUD, z2);
		
		/**
		 * Verifie si la fonction renvoie la bonne zone
		 */
		assertEquals(z1.obtientSortie("SUD"),z2);
		System.out.println("Succès : Bonne zone renvoyé");
	}

	/**
	 * Test method for {@link jeu.Zone#setImage(java.lang.String)}.
	 */
	@Test
	void testSetImage() {
		z1.setImage("provence.png");
		
		/**
		 * Verifie si la fonction modifie bien le nom de l'image
		 */
		assertEquals(z1.nomImage(),"provence.png");
		System.out.println("Succès : Modification du nom de l'image");
	}
}
