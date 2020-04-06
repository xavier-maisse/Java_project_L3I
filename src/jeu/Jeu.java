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
     * Créer la carte, instancie le gui, la bombe et le joueur.
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
     * Création de la carte.
     * Comprend les malfaiteurs avec leurs énigmes,
     * ainsi que les zones et leurs sorties.
     */
    private void creerCarte() {
        Malfaiteur malfaiteur1 = new Malfaiteur("Xavier",
                new Enigme("Quand j’avais 6 ans, ma sœur avait la moitié de mon âge. Aujourd’hui j’ai 50 ans, quel âge a ma sœur ? ", "47"));
        Malfaiteur malfaiteur2 = new Malfaiteur("Abd",new Enigme("Quel est le plus grand chiffre du monde ?","9"));
        Malfaiteur malfaiteur3 = new Malfaiteur("Thai",new Enigme("Verticalement : les deuxièmes lettres de chaque mot se suivent avec 2 rangs d’écart. "
                + " \n Horizontalement : les premières lettres de chaque mot se suivent avec 3 rangs d’écart. "
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
     * Fonction appelé lors de l'instanciation du GUI.
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
     * Permet de traiter les commandes par rapport aux entrées utilisateurs.
     * @param commandeLue
     * @return l'excutions de la fonction associé à la commande lue.
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
     * Permet d'activer la machine à gonfler les ballons.
     * Possible seulement dans la zone principale et si le joueur
     * possède 3 pièces.
     */
    private void gonfler() {
        if(zoneCourante == zones[0]) {
            if(joueur.getNbrDePieces() < 3) {
                gui.afficher("Vous n'avez pas assez de pièces pour activer la machine");
                gui.afficher();
                gui.afficher("Il te manque " + (3-(joueur.getNbrDePieces())) + " pieces.");
            }else {
                joueur.setNbrPieces(0);
                gui.afficheImage("win.jpg");
                zoneCourante.setImage("win.jpg");
            }
        }else {
            gui.afficher("Vous devez être dans la zone principale pour activer la machine");
        }
        
    }
    
    /**
     * Permet de désamorcer la bombe.
     * Si le code fournit par le joueur est correct, on ajoute son score 
     * dans la BDD, et on lance la WebView de la victoire.
     * Sinon pas d'insertion en BDD et Webview de la defaite.
     * @param str, le code pour désamorcer la bombe.
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
            gui.afficher("Vous devez être dans la zone principale pour désamorcer la bombe");
        }
    }
    
    /**
     * Permet de repondre à une énigme.
     * Pour cela la zone doit contenir un malfaiteur, et
     * il est impossible de répondre à une énigme déjà résolu.
     * Si le joueur répond une mauvaise réponse, il est alors 
     * téléporté dans la zone principale afin de lui faire perdre
     * du temps.
     * @param str la reponse à l'énigme.
     */
    private void reponse(String str) {
        if(zoneCourante.getMalfaiteur() != null) {
            if(zoneCourante.getMalfaiteur().getEnigme().getReponse().equals(str)) {
                if(zoneCourante.getMalfaiteur().getEnigme().getIsDone()) {
                    gui.afficher("Vous avez déjà répondu à cette énigme.");
                }else {
                    gui.afficher("Bravo tu as gagné une pièce !");
                    zoneCourante.getMalfaiteur().getEnigme().setDone();
                    joueur.addPiece();
                }
            }else {
                gui.afficher("MAUVAISE REPONSE , téléportation à la zone principale :( ");
                Zone nouvelle = zones[0];
                zoneCourante = nouvelle;
                gui.afficheImage("centre.jpg");
            }
        }else gui.afficher("Pas d'enigme ici");
    }
    
    /**
     * Permet de savoir combien le joueur possède
     * de crédit/pièces.
     */
    private void credit() {
        gui.afficher("Nombre de pièces : " +joueur.getNbrDePieces());
    }
    
    /**
     * Permet d'afficher l'énigme de la salle courante.
     * Possible si la salle contient un malfaiteur et que l'énigme
     * n'a pas déjà été faite.
     */
    private void raconteEnigme() {
        if(zoneCourante.getMalfaiteur() != null) {
            if(zoneCourante.getMalfaiteur().getEnigme().getIsDone()) {
                gui.afficher("Vous avez déjà répondu à cette énigme.");
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
        gui.afficher("Les commandes autorisÃ©es sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }

    /**
     * Permet de changer de salle courante.
     * @param direction où l'utilisateur souhaite aller.
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
     * Permet de revenir dans la salle précédente.
     */
    private void retour() {
        if(!zonePrecedente.isEmpty()) {
            gui.afficher(zonePrecedente.get(zonePrecedente.size()-1).descriptionLongue());
            gui.afficher();
            gui.afficheImage(zonePrecedente.get(zonePrecedente.size()-1).nomImage());
            zoneCourante = zonePrecedente.get(zonePrecedente.size()-1);
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
     * Commande à lancer des le debut du jeu après la saisie
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
