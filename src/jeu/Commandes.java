package jeu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Commandes {

	public static enum Config{
		;
		private String key;
		private String value;
		private Config(String key,String value) {
			this.key = key;
			this.value = value;
		}
		
	}
	private static String loc = "src/jeu/config/";
	private String nomFichier;
	private static Path path;
	private static HashMap<String,String> commandes;
	
	public Commandes(String nomFichier) {
		this.nomFichier = nomFichier;
		Commandes.path = Paths.get(loc, nomFichier);
		try {
			readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile() throws IOException {

		commandes = new HashMap<String, String>();
        String[] keyVals = Files.readString(path).split("\r\n");
        for(String keyVal:keyVals)
        {
          String[] parts = keyVal.split(":",2);
          commandes.put(parts[0],parts[1]);
        }
		//System.out.println(Files.readString(path));
	}
	
	public String getMapConfig(String param){
		return commandes.get(param);
	}
	
	public static void main(String[] args) throws IOException {
		//Commandes test = new Commandes("config.txt");
		//test.readFile();
		//System.out.println(commandes.get("NORD"));
		
	}

}
