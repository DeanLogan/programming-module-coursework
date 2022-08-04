package part03;

import part01.AudioPlayer;
import part01.AudioTrack;
import part01.Genre;

public class AudioPlayerTester {

	public static void main(String[] args) {
		AudioPlayer player = new AudioPlayer();
		testPlay1(player);
		System.out.println("");
		testPlay2(player);
	}
	
	public static void testPlay1(AudioPlayer player) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_1: Checking what happens when a AudioTrack is passed into play");
		AudioTrack trk = new AudioTrack("Reelin' in The Years", "Steely Dan", 4, Genre.ROCK, "mp3");
		boolean result = player.play(trk); 
		System.out.println("Expected Result: true");
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testPlay2(AudioPlayer player) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_2: Checking what happens when a null value is passed into play");
		AudioTrack trk = null;
		boolean result = player.play(trk); 
		System.out.println("Expected Result: false");
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}

}
