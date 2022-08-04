package part01;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the Jukebox class
 * @author Dean
 *
 */
public class Jukebox implements IJukebox {
	private ArrayList<AudioTrack> allTracks; 
	private AudioPlayer player;
	
	/**
	 * this is the constructor for Jukebox
	 * @param player - this is an instance of the AudioPlayer class
	 */
	public Jukebox(AudioPlayer player) {
		this.player = player;
		this.allTracks = new ArrayList<AudioTrack>();
	}
	
	/**
	 * This will get a comma separated list of the track codes and track titles in the allTrack ArrayList
	 * e.g 1, Turn up the Radio, 2, Kings Steely Dan, etc.
	 * @return trackList - this is a string of a comma separated list or null if allTracks is empty
	 */
	public String getTrackList() {
		String trackList = "";
		//makes sure allTracks is not empty
		if(this.allTracks.size() != 0) {
			for(int i=0; i<this.allTracks.size(); i++) {
				trackList += this.allTracks.get(i).getTrackCode() + ", "+ this.allTracks.get(i).getTitle() + ", ";
			}
			trackList = trackList.substring(0, trackList.length() - 2); 
			return trackList;
		}
		else {
			return null;
		}
	}
	/**
	 * This will check if a track can be played and it will increment numOfPlays if the track is playable
	 * it will return true if the track can be played and false if it cannot.
	 * @param trk - an instance of AudioTrack
	 * @return played - this is a boolean value which will return true if the track has been played and false otherwise
	 */
	public boolean playTrack(AudioTrack trk) {
		boolean played = player.play(trk);
		if(played) {
			int numOfPlays = trk.getNumOfPlays() + 1;
			trk.setNumOfPlays(numOfPlays);
		}
		return played;
	}
	/**
	 * This will take an ArrayList of AudioTracks, put them in a random order then play the tracks in this random order.
	 * It will also return a String with a message saying which tracks were able to be played and which were not.
	 * @param list - this is an ArrayList of type AudioTrack
	 * @return shuffleList - this is a formatted string which will return if the songs in the playlist have been played or not
	 */
	public String shuffle(ArrayList<AudioTrack> list) {
		int size = list.size();
		int songsOrder[] = new int[size];
		// create instance of Random class
		Random rand = new Random();
		
		// need this array filled with unique random numbers between 1 and the size
		int count  = 0;
		do {
			// generates a random number in range 0 to length of the ArrayList called list - 1
			int num = rand.nextInt(size);
			// increment to bring number in range 1 to size
			num ++;
			
			boolean contains = false;
			
			//This will insure that all numbers in the list are unique
			for(int i = 0; i<songsOrder.length; i ++) {
				if( songsOrder[i] == num) {
					contains = true;
				}
			}
			
			if (!contains) {
				songsOrder[count] = num;
				count++;
			}
		}
		// this will match the random number with the index of a AudioTrack
		while(count < size);
		String shuffleList = "";
		for(int i = 0; i<songsOrder.length; i ++) {
			int indexOfSong = songsOrder[i];
			AudioTrack trk = list.get(indexOfSong-1);
			// this will check if the AudioTrack at the given index is playable then add the appropriate message to shuffleList 
			if (playTrack(trk)) {
				shuffleList += "Song "+(i+1)+": "+trk.getTitle()+", "+trk.getArtist()+" Is now playing"+ "\n";
			}
			else {
				shuffleList += "Track is not playable\n";
			}
		}
		return shuffleList;
	}
	
	/**
	 * This will add an AudioTrack to allTracks if the AudioTrack is not null
	 * @param trk - an instance of AudioTrack
	 */
	public void addTrack(AudioTrack trk) {
		if( trk != null ) {
			this.allTracks.add(trk);
		}
	}
	
	/**
	 * This will get the contents of allTracks and return it as an Array of AudioTracks
	 * @return trks - an Array of type AudioTrack
	 */
	public AudioTrack[] getTracks() {
		int numOfTracks = this.allTracks.size();
		AudioTrack trks[] = new AudioTrack[numOfTracks];
		for(int i = 0; i<numOfTracks; i++) {
			trks[i] = this.allTracks.get(i);
		}
		return trks;
	}
}
