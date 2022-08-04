package part01;
/**
 * This is the AudioTrack class
 * @author Dean
 *
 */
public class AudioTrack implements ITrack{
	private int trackCode; // this will be the unique identifier for the AudioTrack
	private static int nextCode = 1;  
	private String title; 
	private String artist; 
	private int duration; 
	private Genre style; 
	private String encoding; 
	private int numOfPlays; // I have added this attribute to count the number of times this audio track has been played
	
	/**
	 * This is the constructor for AudioTrack
	 * @param title
	 * @param artist
	 * @param duration
	 * @param style
	 * @param encoding
	 */
	public AudioTrack(String title, String artist, int duration, Genre style, String encoding) {
		this.trackCode = useNextCode();
		this.title = title;
		this.artist = artist;
		this.duration = duration;
		this.style = style;
		this.encoding = encoding;
		this.numOfPlays = 0;
	}
	/**
	 * This will return a formated string of all the attributes (apart from numOfPlays) for this AudioTrack
	 */
	public String getDetails() {
		String details = "";
		details += "Track Code: " + this.getTrackCode() + "\n";
		details += "Title: " + this.getTitle() + "\n";
		details += "Artist: " + this.getArtist() + "\n";
		details += "Duration: " + this.getDuration() + "\n";
		details += "Style: " + this.getStyle() + "\n";
		details += "Encoding: " + this.getEncoding() + "\n";
		return details;
	}
	/**
	 * This will return the next code to be used by the AudioTrack
	 * @return nextCode - the next code to be used
	 */
	private int useNextCode() {
		if (nextCode == 1) {
			nextCode ++;
			return 1;
		}
		else {
			return nextCode ++;
		}
	}
	
	// ------- below is all of the getters and setters ------- \\
	
	/** Justification for adding getTrackCode
	 * This getter for track code is necessary to make sure that the track code 
	 * the user selects is the one that is being used. e.g making sure when a user 
	 * selects track code 1 that the track with track code 1 is the one that is played
	 * the getter is used in this place to compare the track code to the number the user
	 * has selected.
	 */
	public int getTrackCode() {
		return trackCode;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getDuration() {
		return duration;
	}

	public String getStyle() {
		return style.toString();
	}

	public String getEncoding() {
		return encoding;
	}
	
	/** Justification for getNumOfPlays
	 * I need to add this getter for numOfPlays so that I can check how many times the track has been played for the displayTop10 method
	 */
	public int getNumOfPlays() {
		return numOfPlays;
	}
	
	/** Justification for setNumOfPlays
	 * I need to add this setter for numOfPlays so that it can be incremented whenever the track has been played
	 */
	public void setNumOfPlays(int numOfPlays) {
		// this if statement makes sure numOfPlays will not be below 0 or less than what it currently is
		if(numOfPlays > 0 && numOfPlays >= this.numOfPlays) {
			this.numOfPlays = numOfPlays;
		}
	}
}
