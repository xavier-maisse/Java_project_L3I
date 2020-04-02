package jeu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * @author xavier, Thai
 *
 */
public class Jeu {
    
    private GUI gui; 
    private Zone zoneCourante;
    protected Bombe bombe;
    protected Joueur joueur;
    private Zone [] zones;
    private ArrayList<Zone>  zonePrecedente = new ArrayList<Zone>();

    
    /**
     * Cr�er la carte, instancie le gui, la bombe et le joueur.
     * @param joueur
     */
    public Jeu(Joueur joueur) {
        creerCarte();
        gui = null;
        bombe = new Bombe(5,0);
        this.joueur = joueur;
    }

    /**
     * Setter gui + message de bienvenue.
     * @param g
     * @return
     */
    public boolean setGUI( GUI g) { 
        gui = g;
        afficherMessageDeBienvenue(); 
        return true;
        }
    
    /**
     * Cr�ation de la carte.
     * Comprend les malfaiteurs avec leurs �nigmes,
     * ainsi que les zones et leurs sorties.
     */
    private void creerCarte() {
        Malfaiteur malfaiteur1 = new Malfaiteur("Xavier",
                new Enigme("Quand j�avais 6 ans, ma s�ur avait la moiti� de mon �ge. Aujourd�hui j�ai 50 ans, quel �ge a ma s�ur ? ", "47"));
        Malfaiteur malfaiteur2 = new Malfaiteur("Abd",new Enigme("Quel est le plus grand chiffre du monde ?","9"));
        Malfaiteur malfaiteur3 = new Malfaiteur("Thai",new Enigme("Verticalement : les deuxi�mes lettres de chaque mot se suivent avec 2 rangs d��cart. "
                + " \n Horizontalement : les premi�res lettres de chaque mot se suivent avec 3 rangs d��cart. "
                + "\n Donner la suite des 3 lettres.","pni"));
        
        zones = new Zone [5];
        zones[0] = new Zone("centrale", "centre.jpg");
        zones[1] = new Zone("sud", "sud.jpg" );
        zones[2] = new Zone("nord", "nord.jpg",malfaiteur1 );
        zones[3] = new Zone("est", "est.jpg",malfaiteur2 );
        zones[4] = new Zone("ouest", "ouest.jpg",malfaiteur3 );
        
        //Sortie zone centrale
        zones[0].ajouteSortie(Sortie.EST, zones[3]);
        zones[0].ajouteSortie(Sortie.OUEST, zones[4]);
        zones[0].ajouteSortie(Sortie.NORD, zones[2]);
        zones[0].ajouteSortie(Sortie.SUD, zones[1]);
        
        //Sortie zone sud
        zones[1].ajouteSortie(Sortie.NORD, zones[0]);
        
        //Sortie zone nord
        zones[2].ajouteSortie(Sortie.SUD, zones[0]);
        
        //Sortie zone est
        zones[3].ajouteSortie(Sortie.OUEST, zones[0]);
        
        //Sortie zone ouest
        zones[4].ajouteSortie(Sortie.EST, zones[0]);
        
        
        zoneCourante = zones[0]; 
    }
    


    /**
     * Affiche la localisation de la zone courante.
     */
    private void afficherLocalisation() {
            gui.afficher( zoneCourante.descriptionLongue());
            gui.afficher();
    }

    /**
     * Affiche au joueur le message de bienvenue.
     * Fonction appel� lors de l'instanciation du GUI.
     */
    private void afficherMessageDeBienvenue() {
        gui.afficher("Bienvenue !");
        gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        afficherLocalisation();
        gui.afficheImage(zoneCourante.nomImage());
    }
    
    /**
     * Permet de traiter les commandes par rapport aux entr�es utilisateurs.
     * @param commandeLue
     * @return l'excutions de la fonction associ� � la commande lue.
     */
    public String traiterCommande(String commandeLue) {
        gui.afficher( "> "+ commandeLue + "\n");
        String[] result = commandeLue.split(" ");       
        switch (result[0].toUpperCase()) {
        case "?" : case "AIDE" : 
            afficherAide(); 
            return "?";
        case "Z": case "NORD" :
            allerEn( "NORD");
            return "NORD";
       case "S" : case "SUD" :
            allerEn( "SUD"); 
            return "SUD";
        case "D" : case "EST" :
            allerEn( "EST"); 
            return "EST";
        case "Q" : case "OUEST" :
            allerEn( "OUEST"); 
            return "OUEST";
        case "P" : case "QUITTER" :
            terminer();
            return "QUITTER";
        case "DEMANDER":
            raconteEnigme();
            return "DEMANDER";
        case "CREDIT":
            credit();
            return "CREDIT";
        case "REPONSE":
            if(result.length == 1) gui.afficher("Syntaxe: REPONSE + SOLUTION");
            else reponse(result[1]);
            return "REPONSE";
        case "DEFUSE":
            if(result.length == 1) gui.afficher("Syntaxe: DEFUSE + SOLUTION");
            else defuse(result[1]);
            return "DEFUSE";
        case "GONFLER":
            gonfler();
            return "GONFLER";
        case "T":
            solution();
        case "R" : case "RETOUR":
            retour();
            return "RETOUR";
        default : 
            gui.afficher("Commande inconnue");
            return "DEFAUT";
        }
    }

    /**
     * Renvoi le nom du joueur courant.
     */
    private void getNomJoueur() {
        gui.afficher(joueur.getNom());
    }
    
    /**
     * Permet d'activer la machine � gonfler les ballons.
     * Possible seulement dans la zone principale et si le joueur
     * poss�de 3 pi�ces.
     */
    private void gonfler() {
        if(zoneCourante == zones[0]) {
            if(joueur.getNbrDePieces() < 3) {
                gui.afficher("Vous n'avez pas assez de pi�ces pour activer la machine");
                gui.afficher();
                gui.afficher("Il te manque " + (3-(joueur.getNbrDePieces())) + " pieces.");
            }else {
                joueur.setNbrPieces(0);
                gui.afficheImage("win.jpg");
                zoneCourante.setImage("win.jpg");
            }
        }else {
            gui.afficher("Vous devez �tre dans la zone principale pour activer la machine");
        }
        
    }
    
    /**
     * Permet de d�samorcer la bombe.
     * Si le code fournit par le joueur est correct, on ajoute son score 
     * dans la BDD, et on lance la WebView de la victoire.
     * Sinon pas d'insertion en BDD et Webview de la defaite.
     * @param str, le code pour d�samorcer la bombe.
     */
    private void defuse(String str) {
        if(zoneCourante == zones[0]) {
            if(Integer.parseInt(str) == bombe.getCode()) {
                Database dbb = new Database();
                dbb.saveToBDD(joueur.getNom(), bombe.scoreBombe());
                GUI.win();
            }else {
                GUI.gameOver();
            }
        }else {
            gui.afficher("Vous devez �tre dans la zone principale pour d�samorcer la bombe");
        }
    }
    
    /**
     * Permet de repondre � une �nigme.
     * Pour cela la zone doit contenir un malfaiteur, et
     * il est impossible de r�pondre � une �nigme d�j� r�solu.
     * Si le joueur r�pond une mauvaise r�ponse, il est alors 
     * t�l�port� dans la zone principale afin de lui faire perdre
     * du temps.
     * @param str la reponse � l'�nigme.
     */
    private void reponse(String str) {
        if(zoneCourante.getMalfaiteur() != null) {
            if(zoneCourante.getMalfaiteur().getEnigme().getReponse().equals(str)) {
                if(zoneCourante.getMalfaiteur().getEnigme().getIsDone()) {
                    gui.afficher("Vous avez d�j� r�pondu � cette �nigme.");
                }else {
                    gui.afficher("Bravo tu as gagn� une pi�ce !");
                    zoneCourante.getMalfaiteur().getEnigme().setDone();
                    joueur.addPiece();
                }
            }else {
                gui.afficher("MAUVAISE REPONSE , t�l�portation � la zone principale :( ");
                Zone nouvelle = zones[0];
                zoneCourante = nouvelle;
                gui.afficheImage("centre.jpg");
            }
        }else gui.afficher("Pas d'enigme ici");
    }
    
    /**
     * Permet de savoir combien le joueur poss�de
     * de cr�dit/pi�ces.
     */
    private void credit() {
        gui.afficher("Nombre de pi�ces : " +joueur.getNbrDePieces());
    }
    
    /**
     * Permet d'afficher l'�nigme de la salle courante.
     * Possible si la salle contient un malfaiteur et que l'�nigme
     * n'a pas d�j� �t� faite.
     */
    private void raconteEnigme() {
        if(zoneCourante.getMalfaiteur() != null) {
            if(zoneCourante.getMalfaiteur().getEnigme().getIsDone()) {
                gui.afficher("Vous avez d�j� r�pondu � cette �nigme.");
            }else {
                gui.afficher(zoneCourante.getMalfaiteur().getEnigme().toString());
            }
        }else {
            gui.afficher("Pas d'enigme dans cette zone !");
        }
    }
    
    /**
     * Permet d'afficher toutes les commandes.
     */
    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }

    /**
     * Permet de changer de salle courante.
     * @param direction o� l'utilisateur souhaite aller.
     */
    private void allerEn(String direction) {
        Zone nouvelle = zoneCourante.obtientSortie( direction);
        if ( nouvelle == null ) {
            gui.afficher( "Pas de sortie " + direction);
            gui.afficher();
        }
        else {
            zonePrecedente.add(zoneCourante);
            zoneCourante = nouvelle;
            gui.afficher(zoneCourante.descriptionLongue());
            gui.afficher();
            gui.afficheImage(zoneCourante.nomImage());
        }
    }
    
    /**
     * Permet de revenir dans la salle pr�c�dente.
     */
    private void retour() {
        if(!zonePrecedente.isEmpty()) {
            gui.afficher(zonePrecedente.get(zonePrecedente.size()-1).descriptionLongue());
            gui.afficher();
            gui.afficheImage(zonePrecedente.get(zonePrecedente.size()-1).nomImage());
            zonePrecedente.remove(zonePrecedente.size()-1);
        }
        else {
            gui.afficher("Plus de retour en arriere possible !!!");
        }
        
    }
    
    /**
     * Quitter le jeu.
     * Affiche message d'au revoir.
     */
    private void terminer() {
        gui.afficher( "Au revoir...");
        gui.enable( false);
    }
    
    /**
     * Parcours du fichier solution et execution
     * des commandes.
     * Commande � lancer des le debut du jeu apr�s la saisie
     * du pseudo.
     */
    private void solution() {
      try{
      InputStream flux=new FileInputStream("Solution.txt"); 
      InputStreamReader lecture=new InputStreamReader(flux);
      BufferedReader buff=new BufferedReader(lecture);
      String ligne;
      while ((ligne=buff.readLine())!=null){
          traiterCommande(ligne);
      }
      buff.close(); 
      }       
      catch (Exception e){
      System.out.println(e.toString());
      }
  }
}