package part03;

import java.util.ArrayList;

import part01.AudioPlayer;
import part01.AudioTrack;
import part01.Genre;
import part01.Jukebox;

public class JukeboxTester {
	
	// these are just used for testing 
	private static AudioTrack trk1 = new AudioTrack("Turn up the Radio", "OK Go", 3, Genre.POP, "mp3");
	private static AudioTrack trk2 = new AudioTrack("Cold as Ice", "Foreigner", 3, Genre.ROCK, "mp3");
	private static AudioTrack trk3 = new AudioTrack("I Want to Know What Love is", "Foreigner", 5, Genre.ROCK, "mp3");
	private static AudioTrack trk4 = new AudioTrack("Reelin' in The Years", "Steely Dan", 4, Genre.ROCK, "mp3");
	private static AudioTrack trk5 = new AudioTrack("What Is Love", "Haddaway", 4, Genre.DANCE, "wav");
	
	
	public static void main(String[] args) {
		
		testGetTrackList1();
		testGetTrackList2();
		testGetTrackList3();
		
		testPlayTrack1();
		testPlayTrack2();
		
		testShuffle1();
		testShuffle2();
		testShuffle3();
		
		testAddTrack1();
		testAddTrack2();
		
		testGetTracks1();
		testGetTracks2();
	}
	// testing getTrackList
	public static void testGetTrackList1() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-------------------------------------------------------------------");
		System.out.println("TCase_3: Checking the result of getTrackList with AudioTracks added");
		add5AudioTracks(jukebox);
		String result =  jukebox.getTrackList();
		System.out.println("Expected Result: \n"
				+ "1, Turn up the Radio, 2, Cold as Ice, 3, I Want to Know What Love is, 4, Reelin' in The Years, 5, What Is Love");
		System.out.println("Result: \n"+result);
		System.out.println("-------------------------------------------------------------------");
	}
	
	public static void testGetTrackList2() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_4: Checking the result of getTrackList with no AudioTracks");
		String result =  jukebox.getTrackList();
		System.out.println("Expected Result: null");
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void testGetTrackList3() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox1 = new Jukebox(player);
		System.out.println("--------------------------------------------------------------------");
		System.out.println("TCase_5: Checking the result of getTrackList with a null value in it");
		System.out.println("Expected Result: \n"
				+ "1, Turn up the Radio, 2, Cold as Ice, 3, I Want to Know What Love is, 4, Reelin' in The Years, 5, What Is Love");
		
		add5AudioTracks(jukebox1);
		
		AudioTrack trk = null;
		jukebox1.addTrack(trk);
		
		String result =  jukebox1.getTrackList();
		System.out.println("Result: "+result);
		System.out.println("--------------------------------------------------------------------");
	}
	
	// testing playTrack
	
	public static void testPlayTrack1() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------");
		System.out.println("TCase_6: Checking the result of playTrack");
		System.out.println("Expected Result for return: true");
		System.out.println("Expected Result for numOfPlays: 1");
		
		AudioTrack trk = new AudioTrack("Turn up the Radio", "OK Go", 3, Genre.POP, "mp3");
		boolean result = jukebox.playTrack(trk);
		int plays = trk.getNumOfPlays();
		
		System.out.println("Result for return: "+result);
		System.out.println("Result for numOfPlays: "+plays);
		System.out.println("-----------------------------------------");
	}
	
	public static void testPlayTrack2() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------");
		System.out.println("TCase_7: Checking the result of playTrack");
		System.out.println("Expected Result: false");
		
		boolean result = jukebox.playTrack(null);
		
		System.out.println("Result for return: "+result);
		System.out.println("-----------------------------------------");
	}
	
	public static void testShuffle1() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("------------------------------------------------------------------------");
		System.out.println("TCase_8: Checking that an ArrayList of songs is played in a random order");
		System.out.println("Expected Result: Displays a message saying that the songs have been played, in a random order");
		
		ArrayList<AudioTrack> playlist = new ArrayList<AudioTrack>();
		
		playlist.add(trk1);
		playlist.add(trk2);
		playlist.add(trk3);
		playlist.add(trk4);
		playlist.add(trk5);
		
		String result = jukebox.shuffle(playlist);
		
		System.out.print("Result for return:\n"+result);
		System.out.println("------------------------------------------------------------------------");
	}
	
	public static void testShuffle2() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("TCase_9: Checking that if a AudioTrack is equal to null it will not be played");
		System.out.println("Expected Result: Displays a message saying that the songs have been played, apart from 1");
		
		ArrayList<AudioTrack> playlist = new ArrayList<AudioTrack>();
		AudioTrack trk6 = null;
		playlist.add(trk1);
		playlist.add(trk2);
		playlist.add(trk3);
		playlist.add(trk4);
		playlist.add(trk5);
		playlist.add(trk6);
		
		String result = jukebox.shuffle(playlist);
		
		System.out.print("Result for return:\n"+result);
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public static void testShuffle3() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("TCase_10: Checking that if a AudioTrack is equal to null it will not be played");
		System.out.println("Expected Result: Displays a message saying that the songs have been played, apart from 1");
		
		ArrayList<AudioTrack> playlist = new ArrayList<AudioTrack>();
		AudioTrack trk6 = null;
		AudioTrack trk7 = null;
		AudioTrack trk8 = null;
		playlist.add(trk6);
		playlist.add(trk7);
		playlist.add(trk8);
		
		
		String result = jukebox.shuffle(playlist);
		
		System.out.print("Result for return:\n"+result);
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public static void testAddTrack1() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("TCase_11: Checking that a valid AudioTrack can be added to allTracks ");
		System.out.println("Expected Result:\nTrack Code: 2\n"
				+ "Title: Cold as Ice\n"
				+ "Artist: Foreigner\n"
				+ "Duration: 3\n"
				+ "Style: Rock and Roll\n"
				+ "Encoding: mp3");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		jukebox.addTrack(trk2);
		
		AudioTrack[] trks = jukebox.getTracks();
		
		String result = "";
		
		for(int i=0; i< trks.length; i++) {
			result += trks[i].getDetails();
		}
		
		System.out.print("Result:\n"+result);
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public static void testAddTrack2() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("TCase_12: Checking that a valid AudioTrack can be added to allTracks ");
		System.out.println("Expected Result: nothing will be returned");
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		AudioTrack trk6 = null;
		jukebox.addTrack(trk6);
		
		AudioTrack[] trks = jukebox.getTracks();
		String result = "";
		
		for(int i=0; i< trks.length; i++) {
			result += trks[i].getDetails();
		}
		
		System.out.print("Result:\n"+result);
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public static void testGetTracks1() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("---------------------------------------------------");
		System.out.println("TCase_13: Checking that getTracks returns allTracks");
		System.out.println("Expected Result: \nTrack Code: 1\n"
				+ "Title: Turn up the Radio\n"
				+ "Artist: OK Go\n"
				+ "Duration: 3\n"
				+ "Style: Easy Listening Pop\n"
				+ "Encoding: mp3\n"
				+ "\n"
				+ "Track Code: 2\n"
				+ "Title: Cold as Ice\n"
				+ "Artist: Foreigner\n"
				+ "Duration: 3\n"
				+ "Style: Rock and Roll\n"
				+ "Encoding: mp3");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		jukebox.addTrack(trk1);
		jukebox.addTrack(trk2);
		AudioTrack[] trks = jukebox.getTracks();
		String result = "";
		
		for(int i=0; i< trks.length; i++) {
			result += trks[i].getDetails();
		}
		
		System.out.print("Result:\n"+result);
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	public static void testGetTracks2() {
		AudioPlayer player = new AudioPlayer();
		Jukebox jukebox = new Jukebox(player);
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("TCase_14: Checking that getTracks returns an empty string when allTracks is empty");
		System.out.println("Expected result: nothing will be displayed");
		AudioTrack[] trks = jukebox.getTracks();
		String result = "";
		
		for(int i=0; i< trks.length; i++) {
			result += trks[i].getDetails();
			System.out.println("");
		}
		
		System.out.print("Result:\n"+result);
		System.out.println("-----------------------------------------------------------------------------");
	}

	//needed for testing
	private static void add5AudioTracks(Jukebox jukebox) {
		jukebox.addTrack(trk1);
		jukebox.addTrack(trk2);
		jukebox.addTrack(trk3);
		jukebox.addTrack(trk4);
		jukebox.addTrack(trk5);
	}

}