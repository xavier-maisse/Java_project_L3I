package jeu;
import java.util.HashMap;

public class Zone 
{
    private String description;
    private String nomImage;
    private HashMap<String,Zone> sorties;   
    private Malfaiteur malfaiteur;
    
    public Zone(String description, String image, Malfaiteur mal) {
        this.description = description;
        this.malfaiteur = mal;
        nomImage = image;
        sorties = new HashMap<>();
    }
    
    public Zone(String description, String image) {
        this(description,image,null);
    }

    public Malfaiteur getMalfaiteur() {
    	return this.malfaiteur;
    }
    
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }

    public String nomImage() {
        return nomImage;
    }
     
    public String toString() {
        return description;
    }

    public String descriptionLongue()  {
        return "Vous êtes dans " + description + "\nSorties : " + sorties();
    }

    private String sorties() {
        return sorties.keySet().toString();
    }

    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }
    
    public void setImage(String str) {
    	this.nomImage = str;
    }
}

