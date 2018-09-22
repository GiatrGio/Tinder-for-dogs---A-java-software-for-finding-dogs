package model;

/**
 * SoundControl class has pressplay method which allows music to play whenever
 * called.
 * 
 * @return sound
 */

public class SoundControl {
	// Singleton pattern
	private static SoundControl soundControl;

	// constructor
	protected SoundControl() {
	};

	public static SoundControl getInstance() {
		if (soundControl == null) {
			soundControl = new SoundControl();
		}
		return soundControl;
	}

	public boolean soundOn = true;

	

	/**
	 * dogBarkControl is controlled by the 'sound off' switch in loggedinmainpanel.
	 * If the soundOn is true, it will become false and vice versa.
	 */
	public void dogBarkControl() {
		if (soundOn = true) {
			soundOn = false;
		}
		if (soundOn = false) {
			soundOn = true;
		}

	}

	public boolean getSound() {
		return soundOn;
	}

}