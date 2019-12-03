package jeu;
public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
	protected Bombe bombe;
	protected Joueur joueur;
	private Zone [] zones;
    
    public Jeu() {
        creerCarte();
        gui = null;
        bombe = new Bombe(5,0);
        joueur = new Joueur("Xavier");
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    
    private void creerCarte() {
    	Malfaiteur malfaiteur1 = new Malfaiteur("Jean",
    			new Enigme("Quand j’avais 6 ans, ma sœur avait la moitié de mon âge. Aujourd’hui j’ai 50 ans, quel âge a ma sœur ? ", "47"));
        zones = new Zone [5];
        zones[0] = new Zone("centrale", "centre.jpg");
        zones[1] = new Zone("sud", "sud.jpg" );
        zones[2] = new Zone("nord", "nord.jpg",malfaiteur1 );
        zones[3] = new Zone("est", "est.jpg",malfaiteur1 );
        zones[4] = new Zone("ouest", "ouest.jpg",malfaiteur1 );
        
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
        case "Z" : case "NORD" :
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
       	default : 
            gui.afficher("Commande inconnue");
            break;
        }
    }

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
    private void defuse(String str) {
    	if(zoneCourante == zones[0]) {
    		if(Integer.parseInt(str) == bombe.getCode()) {
    			GUI.win();
    		}else {
    			GUI.gameOver();
    		}
    	}else {
    		gui.afficher("Vous devez être dans la zone principale pour désamorcer la bombe");
    	}
    }
    
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
    
    private void credit() {
    	gui.afficher("Nombre de pièces : " +joueur.getNbrDePieces());
    }
    
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
    
    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisÃ©es sont :");
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
        	zoneCourante = nouvelle;
        	gui.afficher(zoneCourante.descriptionLongue());
        	gui.afficher();
        	gui.afficheImage(zoneCourante.nomImage());
        }
    }
    
    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }
}
