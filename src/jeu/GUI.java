package jeu;
import javax.swing.*;
import javax.swing.border.Border;

import com.sun.javafx.application.PlatformImpl;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;



public class GUI implements ActionListener
{
    private Jeu jeu;
    protected static JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image; 

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
    }

    public boolean afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
        return true;
    }
    
    public boolean afficher() {
        afficher("\n");
        return true;
    }

   public boolean afficheImage( String nomImage) {
        URL imageURL = this.getClass().getClassLoader().getResource("jeu/images/" + nomImage);
        if( imageURL != null ) {
            image.setIcon( new ImageIcon( imageURL ));
            fenetre.pack();
            return true;
        }
        else
        {
            return false;
        }
   }

    public boolean enable(boolean ok) {
        entree.setEditable(ok);
        if ( ! ok ) {
            entree.getCaret().setBlinkRate(0);
            return false;
        }
        else
        {
            return true;
        }
    }

    public static void creerGUIGame(boolean win) {
      SimpleSwingBrowser browser = new SimpleSwingBrowser();
      browser.setVisible(true);
      browser.loadURL("http://ivax.alwaysdata.net/miage/?win="+win);
      
  }
   
    
    private void creerGUI() {
        fenetre = new JFrame("Defuse");
        
        entree = new JTextField(34);

        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);

        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.pack();
        fenetre.setVisible(true);
        entree.requestFocus();
    }

    public static void changeTitle(String str) {
        fenetre.setTitle(str);
    }
    
    /**
     * Fermeture de la fenetre si gameOver et
     * affichage d'une fenetre GameOver
     * @return 
     */
    public static boolean gameOver() {
        fenetre.setVisible(false);
        GUI.creerGUIGame(false);
        //Si on veut couper l'application
        //fenetre.dispatchEvent(new WindowEvent(fenetre,WindowEvent.WINDOW_CLOSING));
        return true;
    }
    
    public static void win() {
        fenetre.setVisible(false);
        GUI.creerGUIGame(true);
        //Si on veut couper l'application
        //fenetre.dispatchEvent(new WindowEvent(fenetre,WindowEvent.WINDOW_CLOSING));
    }
    
    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande( commandeLue);
    }
}