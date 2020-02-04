package jeu;
import java.util.HashMap;

/**
 * 
 * @author xavier
 *
 */
public class Zone 
{
	/**
	 * Description de la zone
	 */
    private String description;
    
    /**
     * Image de la zone
     */
    private String nomImage;
    
    /**
     * Contient les differentes sorties pour une instance de zone
     */
    private HashMap<String,Zone> sorties;   
    
    /**
     * Chaque zone contient un malfaiteur
     */
    private Malfaiteur malfaiteur;
    
    /**
     * Constructeur de zone
     * @param description
     * @param image
     * @param mal
     */
    public Zone(String description, String image, Malfaiteur mal) {
        this.description = description;
        this.malfaiteur = mal;
        nomImage = image;
        sorties = new HashMap<>();
    }
    
    /**
     * Constructeur de zone
     * @param description
     * @param image
     */
    public Zone(String description, String image) {
        this(description,image,null);
    }

    /**
     * Retourne le malfaiteur de la zone
     * @return malfaiteur
     */
    public Malfaiteur getMalfaiteur() {
    	return this.malfaiteur;
    }
    
    /**
     * Ajoute des sorties à la zone
     * @param sortie
     * @param zoneVoisine
     */
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }

    /**
     * Retourne le nom de l'image
     * @return nomImage
     */
    public String nomImage() {
        return nomImage;
    }
     
    /**
     * Retourne la description de la zone
     */
    public String toString() {
        return description;
    }

    /**
     * Retourne la description longue de la zone
     * @return
     */
    public String descriptionLongue()  {
        return "Vous êtes dans " + description + "\nSorties : " + sorties();
    }

    /**
     * Retourne les sorties de la zone
     * @return
     */
    private String sorties() {
        return sorties.keySet().toString();
    }

    /**
     * Retourne la zone par rapport à la direction passé en parametre
     * @param direction
     * @return
     */
    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }
    
    /**
     * Permet de définir l'image d'une zone
     * @param str
     */
    public void setImage(String str) {
    	this.nomImage = str;
    }
}

