package part01;

/**
 * This is the class for AudioPlayer
 * @author Dean 
 *
 */
public class AudioPlayer implements IPlayer {
	private AudioTrack currentTrack;
	
	/**
	 * this is the constructor for AudioPlayer it does not need 
	 */
	public AudioPlayer() {
		this.currentTrack = currentTrack;
	}
	
	/**
	 * This will check if an AudioTrack is equal to null if it is returns false if it isn't it will return true
	 * @param currentTrack - an instance of AudioTrack
	 * @return true or false 
	 */
	public boolean play(AudioTrack currentTrack) {
		if( currentTrack == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
