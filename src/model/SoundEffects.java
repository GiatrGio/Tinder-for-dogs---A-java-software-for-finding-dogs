package model;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/** 
 * Play the following sounds when is called: PlayWoof, playWoofGroove, playBark, playDogFight, playHoundDog
 */
public class SoundEffects {
	/*
	 * MH JD SoundEffects which play sounds. DogWoof single sound, dog yelping
	 * sound, dogs barking aggressively in sounds folder 
	 */

	public void playWoof() {

		pressPlay("bark1");

	}

	public void playWoofGroove() {
		pressPlay("bark2");
	}

	public void playBark() {
		pressPlay("bark3");

	}

	public void playDogFight() {
		pressPlay("bark4_agg");
	}

	public void playHoundDog() {
		pressPlay("dogs crying");
	}

	/*
	 * pressPlay gets string input and gts from sounds the corresponding sound.
	 */
	public void pressPlay(String name) {

		if ((SoundControl.getInstance().getSound())) {
			try {
				String bip = "sounds/" + name + ".wav";
				Clip clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(bip));
				clip.open(inputStream);
				clip.start();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			name = null;
		}
	}
}