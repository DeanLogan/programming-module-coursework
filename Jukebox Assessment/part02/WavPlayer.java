package part02;

import part01.AudioPlayer;
import part01.AudioTrack;

public class WavPlayer extends AudioPlayer{
	
	public WavPlayer() {
		
	}
	
	@Override
	public boolean play(AudioTrack currentTrack) {
		if(currentTrack.getEncoding().compareToIgnoreCase("wav") == 0 && currentTrack != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
