/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import jeu.ImagePanel;

/**
 * @author AbdRahim
 *
 */
class ImagePanelTest {

	/**
	 * Test method for {@link jeu.ImagePanel#ImagePanel(java.awt.Image)}.
	 */
	@Test
	void testImagePanel() {
		try {
			JFrame frame = new JFrame("fondEcran");
			BufferedImage myImage;
			myImage = ImageIO.read(new File("src/jeu/images/bg.png"));
			ImagePanel i = new ImagePanel(myImage);
			System.out.println("Image bg.png récupérée avec succès");
		} catch (IOException e1) {
			System.out.println("Echec de la récupération de l'image bg.png");
		}
	}

}
