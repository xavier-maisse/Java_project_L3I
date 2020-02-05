package jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * 
 * @author xavier
 * 
 */
public class Bombe {
	private int initSecond;
	private int initMinute;
	private Timer timer;
	private static int code = 196;
	
	/**
	 * Constructeur classe bombe
	 * @param minute
	 * @param sec
	 */
	public Bombe(int minute, int sec) {
		this.initSecond = sec;
		this.initMinute = minute;
		this.timer = new Timer(1000,taskPerformer);
		this.timer.start();
	}
	
	/**
	 * ActionListener qui permet de décrémenter le compteur
	 */
	ActionListener taskPerformer = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(initSecond == 0) {
				--initMinute;
				initSecond = 60;
			}
			--initSecond;
			GUI.changeTitle(tempsRestant());
			if(initSecond == 0 && initMinute == 0) GUI.gameOver();
		}
	};
	
	/**
	 * Retourne le temps restant
	 * @return
	 */
	public String tempsRestant() {
		return "Temps restant : "+ this.initMinute + ":" 
				+ (this.initSecond <= 10 ? "0" : "" ) + this.initSecond+"s";
	}
	
	/**
	 * Retourne le code de la bombe
	 * @return
	 */
	public int getCode() {
		return Bombe.code;
	}
	
	public int scoreBombe() {
		return initMinute == 0 ? initSecond : (initMinute*100)+initSecond;
	}
}
