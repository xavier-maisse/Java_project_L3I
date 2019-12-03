package jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Bombe {
	private int initSecond;
	private int initMinute;
	private Timer timer;
	
	public Bombe(int minute, int sec) {
		this.initSecond = sec;
		this.initMinute = minute;
		this.timer = new Timer(1000,taskPerformer);
		this.timer.start();
	}
	
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
	
	public String tempsRestant() {
		return "Temps restant : "+ this.initMinute + ":" 
				+ (this.initSecond <= 10 ? "0" : "" ) + this.initSecond+"s";
	}
	
}
