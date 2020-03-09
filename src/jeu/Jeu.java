package jeu;

import java.util.ArrayList;

public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
	protected Bombe bombe;
	protected Joueur joueur;
	private Zone [] zones;
	private ArrayList<Zone>  zonePrecedente = new ArrayList<Zone>();

    
    public Jeu(Joueur joueur) {
        creerCarte();
        gui = null;
        bombe = new Bombe(5,0);
        this.joueur = joueur;
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    
    private void creerCarte() {
    	Malfaiteur malfaiteur1 = new Malfaiteur("Xavier",
    			new Enigme("Quand j�avais 6 ans, ma s�ur avait la moiti� de mon �ge. Aujourd�hui j�ai 50 ans, quel �ge a ma s�ur ? ", "47"));
        Malfaiteur malfaiteur2 = new Malfaiteur("Abd",new Enigme("Quel est le plus grand chiffre du monde ?","9"));
        Malfaiteur malfaiteur3 = new Malfaiteur("Tai",new Enigme("Verticalement : les deuxi�mes lettres de chaque mot se suivent avec 2 rangs d��cart. "
        		+ " \n Horizontalement : les premi�res lettres de chaque mot se suivent avec 3 rangs d��cart. "
        		+ "\n Donner la suite des 3 lettres.","PNI"));
        
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
    


    private void afficherLocalisation() {
            gui.afficher( zoneCourante.descriptionLongue());
            gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
    	gui.afficher("Bienvenue !");
    	gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        afficherLocalisation();
        gui.afficheImage(zoneCourante.nomImage());
    }
    
    public void traiterCommande(String commandeLue) {
    	gui.afficher( "> "+ commandeLue + "\n");
    	String[] result = commandeLue.split(" ");    	
        switch (result[0].toUpperCase()) {
        case "?" : case "AIDE" : 
            afficherAide(); 
        	break;
        case "Z": case "NORD" :
        	allerEn( "NORD"); 	
        	break;
       case "S" : case "SUD" :
        	allerEn( "SUD"); 
        	break;
        case "D" : case "EST" :
        	allerEn( "EST"); 
        	break;
        case "Q" : case "OUEST" :
        	allerEn( "OUEST"); 
        	break;
        case "P" : case "QUITTER" :
        	terminer();
        	break;
        case "DEMANDER":
        	raconteEnigme();
        	break;
        case "CREDIT":
        	credit();
        	break;
        case "REPONSE":
        	if(result.length == 1) gui.afficher("Syntaxe: REPONSE + SOLUTION");
        	else reponse(result[1]);
        	break;
        case "DEFUSE":
        	if(result.length == 1) gui.afficher("Syntaxe: DEFUSE + SOLUTION");
        	else defuse(result[1]);
        	break;
        case "GONFLER":
        	gonfler();
        	break;
        case "NOM":
        	getNomJoueur();
        	break;
        case "R" : case "RETOUR":
        	retour();
        	break;
        case "SOLUTION":
        	solution();
       	default : 
            gui.afficher("Commande inconnue");
            break;
        }
    }

    private void getNomJoueur() {
    	gui.afficher(joueur.getNom());
    }
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
    private void defuse(String str) {
    	if(zoneCourante == zones[0]) {
    		if(Integer.parseInt(str) == bombe.getCode()) {
    			GUI.win();
    		}else {
    			GUI.gameOver();
    		}
    	}else {
    		gui.afficher("Vous devez �tre dans la zone principale pour d�samorcer la bombe");
    	}
    }
    
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
    
    private void credit() {
    	gui.afficher("Nombre de pi�ces : " +joueur.getNbrDePieces());
    }
    
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
    
    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }

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
        	raconteEnigme();
        }
    }
    
    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }
    
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
    
    private void solution() {
    	//Debut de la solution
    	gui.afficher( "Debut de la solution");
    	
    	//Resolution premiere Egnime au nord
    	gui.afficher("Direction nord");
    	allerEn( "NORD"); 	
    	raconteEnigme();

    	reponse("47");
    	
    	//Resolution deuxieme egnime a l'ouest
    	gui.afficher("Direction ouest");
    	allerEn("SUD");
    	allerEn("OUEST");
    	raconteEnigme();
    	reponse("PNI");
    	
    	//Resolution troisieme egnime a l'est
    	gui.afficher("Direction est");
    	allerEn("EST");
    	allerEn("EST");
    	raconteEnigme();
    	reponse("9");
    	
    	//Retour au menu principal + gonflement de ballon
    	allerEn("OUEST");
    	gui.afficher("Gonflement ballon");
    	gonfler();
    	gui.afficher("desamorcage");
    	defuse("196"); 	
    }
}