package part03;

import part01.AudioTrack;
import part01.Genre;

public class AudioTrackTester {

	public static void main(String[] args) {
		AudioTrack trk = new AudioTrack("Reelin' in The Years", "Steely Dan", 4, Genre.ROCK, "mp3");
		
		testGetDetails(trk);
		testGetTrackCode(trk);
		testUseNextCode(trk);
		testGetTitle(trk);
		testGetArtist(trk);
		testGetDuration(trk);
		testGetStyle(trk);
		testGetEncoding(trk);
		testGetNumOfPlays(trk);
		
		testSetNumOfPlays1(trk);
		testSetNumOfPlays2(trk);
		testSetNumOfPlays3(trk);
		
		testSetNumOfPlays4(trk);
		testSetNumOfPlays5(trk);

	}
	public static void testGetDetails(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_15: Check if getDetails returns a formatted string including the tracks details");
		System.out.println("Expected Result: \n"
				+ "Track Code: 1\n"
				+ "Title: Reelin' in The Years\n"
				+ "Artist: Steely Dan\n"
				+ "Duration: 4\n"
				+ "Style: Rock and Roll\n"
				+ "Encoding: mp3");
		
		String result = trk.getDetails();
		
		System.out.println(" ");
		System.out.println("Result: \n"+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testGetTrackCode(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_16: Checking to see if the getter for track code returns the track code");
		System.out.println("Expected Result: 1");
		int result = trk.getTrackCode();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testUseNextCode(AudioTrack trk) {
		AudioTrack trk2 = new AudioTrack("Cold as Ice", "Foreigner", 3, Genre.ROCK, "mp3");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_17: Checking to see if useNextCode increments the track code");
		System.out.println("Expected Result: 2");
		int result = trk2.getTrackCode();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testGetTitle(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_18: Checking to see if the getter for title returns the title");
		System.out.println("Expected Result: Reelin' in the Years");
		String result = trk.getTitle();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testGetArtist(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_19: Checking to see if the getter for artist returns the artist");
		System.out.println("Expected Result: Steely Dan");
		String result = trk.getArtist();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	public static void testGetDuration(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_20: Checking to see if the getter for duration returns the duration");
		System.out.println("Expected Result: 4");
		int result = trk.getDuration();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	public static void testGetStyle(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_21: Checking to see if the getter for style returns the style");
		System.out.println("Expected Result: Rock and Roll");
		String result = trk.getStyle();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	public static void testGetEncoding(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_22: Checking to see if the getter for encoding returns the encoding");
		System.out.println("Expected Result: mp3");
		String result = trk.getEncoding();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	public static void testGetNumOfPlays(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_23: Checking to see if the getter for numOfPlays returns the numOfPlays");
		System.out.println("Expected Result: 0");
		int result = trk.getNumOfPlays();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testSetNumOfPlays1(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_24: Checking to see if the setter will allow numOfPlays to set a number bigger than it currently is");
		System.out.println("Expected Result: 3");
		trk.setNumOfPlays(3);
		int result = trk.getNumOfPlays();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testSetNumOfPlays2(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_25: Checking to see if the setter will not allow numOfPlays to set it to a negative number");
		System.out.println("Expected Result: 0");
		trk.setNumOfPlays(-1);
		int result = trk.getNumOfPlays();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testSetNumOfPlays3(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_26: Checking to see if the setter will not allow numOfPlays to set a number smaller than it currently is");
		System.out.println("Expected Result: 2");
		trk.setNumOfPlays(2);
		trk.setNumOfPlays(1);
		int result = trk.getNumOfPlays();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testSetNumOfPlays4(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_46: Checking to see if the setter will not allow numOfPlays to set it to a negative number");
		System.out.println("Expected Result: 0");
		trk.setNumOfPlays(-1);
		int result = trk.getNumOfPlays();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testSetNumOfPlays5(AudioTrack trk) {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_47: Checking to see if the setter will not allow numOfPlays to set a number smaller than it currently is");
		System.out.println("Expected Result: 2");
		trk.setNumOfPlays(2);
		trk.setNumOfPlays(1);
		int result = trk.getNumOfPlays();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
}
