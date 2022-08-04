package part02;

import part01.AudioPlayer;
import part01.AudioTrack;

public class MP3Player extends AudioPlayer {
	
	public MP3Player() {
		
	}
	
	@Override
	public boolean play(AudioTrack currentTrack) {
		if(currentTrack.getEncoding().compareToIgnoreCase("mp3") == 0 && currentTrack != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
