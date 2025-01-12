/****************************************************************************
Final Project
Due date: 05/11/2022
Author: Andrew Hoang
Description: MusicHandler handles all music events
*****************************************************************************/
import java.io.File;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class MusicHandler extends Application {		//handle music play
		
		public void start(Stage stage) { //required for MusicHandler
			stage.show();
		}
		public void soundPlayer(File file, AudioClip audioClip, boolean repeat, double vol) { //method to upload file and play music
				audioClip = new AudioClip(file.toURI().toString()); //gets audioclip
				if (repeat) {
				    audioClip.setVolume(vol);  //sets volume double from 0-1
					audioClip.setCycleCount(10); //sets repeat amount
					audioClip.play(); //play
					
				}
				else audioClip.play();
		}
		
	}