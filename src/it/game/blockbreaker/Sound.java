package it.game.blockbreaker;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip clip;
	
	public Sound (Game g) {
		//start song
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(
			new File(".//music/start.wav")));
		} catch (Exception e) {
			System.out.print(e);
		}
		
		while(g.getGraphics().getState() == "START") {
			try {
				clip.start();
				//clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		
		clip.stop();
		
		//play song
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(
			new File(".//music/music.wav")));
		} catch (Exception e) {
			System.out.print(e);
		}
		
		while(g.getGraphics().getState() == "RUNNING") {
			try {
				clip.start();
				//clip.loop(Clip.LOOP_CONTINUOUSLY);
			} catch (Exception e) {
				System.out.print(e);
			}
		}
		
		clip.stop();
		
		//game over
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(
			new File(".//music/end.wav")));
		} catch (Exception e) {
			System.out.print(e);
		}
		
		try {
			clip.start();
			//clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.print(e);
		}
		//clip.close();
	}
}
