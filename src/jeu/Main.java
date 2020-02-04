package jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class Main {
	public static void main(String[] args) {

		JFrame frame = new JFrame("Defuse");
		BufferedImage myImage;

		try {
			myImage = ImageIO.read(new File("src/jeu/images/bg.png"));
			frame.setContentPane(new ImagePanel(myImage));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JButton btnPlay = new JButton("JOUER !");
		JTextField textField = new JTextField();
		JLabel label = new JLabel("Votre pseudo :");
		label.setFont(new Font("Impact", Font.BOLD, 35));
		JLabel labelerror = new JLabel("Renseignez un pseudo.");
		labelerror.setFont(new Font("Impact", Font.PLAIN, 18));
		labelerror.setForeground(Color.RED);
		btnPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					labelerror.setVisible(true);
				}else {
					Jeu jeu = new Jeu(new Joueur(textField.getText()));
					GUI gui = new GUI( jeu);
					jeu.setGUI( gui);
					frame.dispose();
				}
				
			}
		});
		
		frame.getContentPane().setLayout(null);
		btnPlay.setBounds(frame.getWidth()/2+150, 200, 300, 40);
		textField.setBounds(frame.getWidth()/2+150, 150, 300, 40);
		label.setBounds(frame.getWidth()/2+180, 100, 400, 45);
		labelerror.setBounds(frame.getWidth()/2+151, 230, 400, 45);
		labelerror.setVisible(false);
		frame.add(btnPlay);
		frame.add(textField);
		frame.add(label);
		frame.add(labelerror);
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    frame.setPreferredSize(new Dimension(600, 400));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}

