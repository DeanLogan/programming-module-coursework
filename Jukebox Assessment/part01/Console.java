package part01;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
	
	// defines a Scanner 
	// Justification: this is static so that I do not have to create an instance of scanner every time I want to get the user to enter something
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		// defines an instance of AudioPlayed
		AudioPlayer player = new AudioPlayer();
		
		// defines an instance of Jukebox
		Jukebox jukebox = new Jukebox(player);
		
		// title for menu
		String title = "Jukebox menu.";
		
		// list of options for menu
		String options[] = {"Display All Tracks", "Display Tracks By Artist", "Add New Track", "Play a Track", "Create a Playlist", "Shuffle Play", "Display the Top 10", "Exit"};
		
		// defines a Menu
		Menu menu = new Menu(title, options);
		
		//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\
		//The following 2 lines are methods which can be uncommented if you wish to test the code using them what each one does is explained towards the bottom of the code starting at line 516
		//add5AudioTracks(jukebox);
		//play10(jukebox);
		//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\
		
		// Initialises playlist
		ArrayList<AudioTrack> playlist = null;
		
		boolean quit = false;
		
		do {
			int choice = menu.getUserChoice();
			if (choice == 8) {
				System.out.println("Goodbye");
				quit = true;
			}
			else {
				// returns playlist here to avoid making playlist a global variable 
				playlist = processChoice(choice,jukebox,playlist);
			}
		} while(!quit);
	}

	/**
	 * this will take the choice the user selected and run the correct method based on their choice
	 * @param choice
	 * @param jukebox
	 * @param playlist
	 * @return playlist
	 */
	public static ArrayList<AudioTrack> processChoice(int choice, Jukebox jukebox, ArrayList<AudioTrack> playlist) {
		switch (choice) {
		case 1:
			displayAllTracks(jukebox);
			break;
		case 2:
			displayByArtist(jukebox);
			break;
		case 3:
			addNewTrack(jukebox);
			break;
		case 4:
			playTrack(jukebox);
			break;
		case 5:
			playlist = createPlaylist(jukebox);
			break;
		case 6:
			shufflePlay(jukebox,playlist);
			break;
		case 7:
			displayTop10(jukebox);
			break;
		}
		System.out.println();
		return playlist;
	}
	
	/**
	 * This will return an Array of type AudioTrack sorted in alphabetical order by title
	 * @param trks - an Array of type AudioTrack 
	 * @return trksSorted - this is an Array of type AudioTrack which has been sorted in alphabetical order by title
	 */
	public static AudioTrack[] sortTracks(AudioTrack[] trks) {
		
		int numOfTrks = trks.length;
		String trksTitle[] = new String[numOfTrks];
		
		// this for loop is getting the title of the track so that it can be sorted in alphabetical order 
		for(int i=0; i<numOfTrks; i++) {
			trksTitle[i] = trks[i].getTitle() + " - " + trks[i].getArtist();
		}
		
		AudioTrack trksSorted[] = new AudioTrack[numOfTrks];
		
		// below is an insertion sort algorithm which orders trksTitle in alphabetical order
		for(int i = 1; i < trksTitle.length; i++) {
			String currentTrk = trksTitle[i]; //stores unsorted element (the one that is currently being checked)
			int j = i-1; 
			// checks if the currentTrk is bigger than any other element in the array, if it is then all elements in 
			// the array are shifted to the right 
			while ((j > -1 && trksTitle[j].compareToIgnoreCase(currentTrk) > 0 )) {
				trksTitle[j+1] = trksTitle[j];
				j--;
			}
			trksTitle[j+1] = currentTrk; //inserts unsorted element into the correct position
		}

		
		// below will match the track title with the corresponding AudioTrack and order the list 
		for(int i=0; i<numOfTrks; i++) {
			String id = trks[i].getTitle() + " - " + trks[i].getArtist(); // the title and artist name can be combined to be a unique identifier as if the title and artist is the same then it is considered as a duplicate
			for(int j=0; j<numOfTrks; j++) {
				if(id.equals(trksTitle[j])) {
					trksSorted[j] = trks[i];
				}
			}
		}
		return trksSorted;
	}
	
	/**
	 * This will display all of the tracks within jukebox
	 * @param jukebox
	 */
	public static void displayAllTracks(Jukebox jukebox) {
		
		AudioTrack[] trks = jukebox.getTracks();
		AudioTrack[] trksSorted = sortTracks(trks);
		
		if(trksSorted.length == 0) {
			System.err.print("ERROR: ");
			System.out.println("There are no tracks in the jukebox");
		}
		else {
			for(int i=0; i<trksSorted.length; i++) {
				System.out.println(trksSorted[i].getDetails());
			}
		}
	}
	
	/**
	 * This will allow the user to enter an artist to filter the list of the tracks in jukebox
	 * @param jukebox
	 */
	public static void displayByArtist(Jukebox jukebox) {
		AudioTrack[] trks = jukebox.getTracks();
		AudioTrack[] trksSorted = sortTracks(trks);
		
		System.out.print("Enter the tracks artist: ");
		String artistName = input.nextLine();
		artistName.trim();
		int notFound = 0;
		
		// linear search for the artists name within jukebox
		for(int i=0; i<trksSorted.length; i++) {
			if(trksSorted[i].getArtist().compareToIgnoreCase(artistName) == 0) {
				System.out.println(trksSorted[i].getDetails());
			}
			else {
				notFound ++;
			}
		}
		if (notFound == trksSorted.length) {
			// this will display a different message depending on if the user enters nothing or an artist which is not in the jukebox
			int count = 0; 
			for(int i = 0; i<artistName.length(); i++) {
				if(artistName.charAt(i) == ' ') {
					count ++;
				}
			}
			if(count == artistName.length()) {
				System.err.print("ERROR: ");
				System.out.println("You must enter an artists name to filter the list of tracks.");
			}
			else {
				System.err.print("ERROR: ");
				System.out.println("There are no songs by "+ artistName);
			}
		}
	}
	
	/**
	 * This allows the user to add a track to jukebox which is not a duplicate 
	 * (a duplicate is considered by anything which has the same title and artist)
	 * @param jukebox
	 */
	public static void addNewTrack(Jukebox jukebox) {
		// validates trksTitle (make sure the user enters something)
		String trksTitle;
		while(true) {
			System.out.print("Enter the tracks title: ");
			trksTitle = input.nextLine();
			trksTitle.trim();
			int count = 0;
			for(int i=0; i<trksTitle.length(); i++) {
				if(trksTitle.charAt(i) == ' ') {
					count ++;
				}
			}
			if(trksTitle.length() != count) {
				break;
			}
			else {
				System.err.print("ERROR: ");
				System.out.println("You must enter something for the track title.");
			}
		}
		
		// validates trksArtist (make sure the user enters something)
		String trksArtist;
		while(true) {
			System.out.print("Enter the tracks artist: ");
			trksArtist = input.nextLine();
			trksArtist.trim();
			int count = 0;
			for(int i=0; i<trksArtist.length(); i++) {
				if(trksArtist.charAt(i) == ' ') {
					count ++;
				}
			}
			if(trksArtist.length() != count) {
				break;
			}
			else {
				System.err.print("ERROR: ");
				System.out.println("You must enter something for the track artist.");
			}
		}
		
		// validation for track duration
		int trksDuration;
		while(true) {
			try {
				System.out.print("Enter the tracks duration: ");
				trksDuration = input.nextInt();
				break;
			}
			catch (Exception e) {
				input.nextLine();
				System.err.print("\nERROR "); //this is just used to print the string "ERROR " in the colour red to make it easier for the user to see.
				System.out.println("Please enter an integer value for the duration of the track.\n");
			}
		}
		input.nextLine(); 
		
		// below is the validation for style. 
		Genre style;
		while(true) {
			System.out.print("Enter the tracks style/genre: ");
			String trksStyle = input.nextLine();
			trksStyle = trksStyle.trim();
	
			if(trksStyle.compareToIgnoreCase("Rock") == 0 || trksStyle.compareToIgnoreCase("Rock and Roll") == 0) {
				style = Genre.ROCK;
				break;
			}
			else if(trksStyle.compareToIgnoreCase("Pop") == 0 || trksStyle.compareToIgnoreCase("Easy Listening Pop") == 0 ) {
				style = Genre.POP;
				break;
			}
			else if(trksStyle.compareToIgnoreCase("Dance") == 0 || trksStyle.compareToIgnoreCase("Techno Dance") == 0) {
				style = Genre.DANCE;
				break;
			}
			else if(trksStyle.compareToIgnoreCase("Jazz") == 0 || trksStyle.compareToIgnoreCase("Smooth Jazz") == 0) {
				style = Genre.JAZZ;
				break;
			}
			else if(trksStyle.compareToIgnoreCase("Classical") == 0) {
				style = Genre.CLASSICAL;
				break;
			}
			else if(trksStyle.compareToIgnoreCase("Other") == 0 || trksStyle.compareToIgnoreCase("Unknown") == 0 || trksStyle.compareToIgnoreCase("Unknown Genre") == 0) {
				style = Genre.OTHER;
				break;
			}
			else {
				System.err.print("\nERROR "); 
				System.out.println("Please enter the genre from the following list: ");
				System.out.println("---------");
				System.out.println("Rock");
				System.out.println("Pop");
				System.out.println("Dance");
				System.out.println("Jazz");
				System.out.println("Classical");
				System.out.println("Other");
				System.out.println("---------");
			}
		}
		
		// validation for encoding
		String trksEncoding;
		while(true) {
			System.out.print("Enter the tracks encoding (as either a mp3 or wav): ");
			trksEncoding = input.nextLine();
			trksEncoding = trksEncoding.trim();
			if(trksEncoding.compareToIgnoreCase("mp3") == 0 || trksEncoding.compareToIgnoreCase("wav") == 0) {
				break;
			}
			else {
				System.err.print("ERROR: "); 
				System.out.println("Please enter either mp3 or wav!");
			}
		}
		
		// this will check if the user is trying to add a duplicate or not
		// if it is a duplicate then the system will print a message and if it is not then it will be added to jukebox
		AudioTrack trkList[] = jukebox.getTracks();
		boolean found = false;
		String inAllTracks;
		String toBeAdded = trksTitle +" - "+ trksArtist;
		// linear search to checking for toBeAdded
		for(int i=0; i<trkList.length; i++) {
			inAllTracks = trkList[i].getTitle() +" - "+ trkList[i].getArtist();
			if(inAllTracks.compareToIgnoreCase(toBeAdded) == 0) {
				found = true;
				break;
			}
		}
		if (!found) {
			AudioTrack trk = new AudioTrack(trksTitle, trksArtist, trksDuration, style, trksEncoding);
			System.out.println("The track details have been added.");
			jukebox.addTrack(trk);
		}
		else {
			System.err.print("ERROR: ");
			System.out.println("This track has already been added!");
		}
	}
	
	/**
	 * This will display all the tracks to the user then the user can play a track within that list by entering the track code
	 * @param jukebox
	 */
	public static void playTrack(Jukebox jukebox) {
		displayAllTracks(jukebox);
		int option = 0; // option is the track code the user decided to play
		boolean error = false;
		try {
			System.out.print("Enter the track code for the song you wish to play: ");
			option = input.nextInt();
		}
		catch (Exception e) {
			input.nextLine();
			System.err.print("ERROR: ");
			System.out.println("The track code must be an integer value!");
			error = true;
		}
		AudioTrack[] trks = jukebox.getTracks();
		
		boolean found = false;
		// linear search to find the track code within trks
		for(int i=0; i<trks.length; i++) {
			if(trks[i].getTrackCode() == option) {
				found = true;
				// checks if the track selected is playable if it is not a suitable message if displayed
				if(jukebox.playTrack(trks[i])) {
					System.out.println(trks[i].getTitle()+" by "+trks[i].getArtist() + " is now playing.");
					break;
				}
				else {
					System.err.print("ERROR: ");
					System.out.println("The track "+ trks[i].getTitle()+" by "+trks[i].getArtist() + " with track code " + trks[i].getTrackCode() + " is not playable.");
					break;
				}
			}
		}
		if(!found && !error) {
			System.err.print("ERROR: ");
			System.out.println("The track code you have selected was not found!");
		}
	}


	/**
	 * This will display all the tracks to the user and allow them to create a playlist from the tracks
	 * @param jukebox
	 */
	public static ArrayList<AudioTrack> createPlaylist(Jukebox jukebox) {
		ArrayList<AudioTrack> playlist = new ArrayList<AudioTrack>();
		//AudioTrack trk = createTrack();
		
		AudioTrack[] trks = jukebox.getTracks();
		AudioTrack[] trksSorted = sortTracks(trks);
		
		while(true) {
			// displays all of the tracks
			for(int i=0; i<trks.length; i++) {
				if( trks[i] != null) {
					System.out.println(trks[i].getDetails());
				}
			}
			
			int trkCode = 0; // initialize's the variable trkCode
			
			// catches any exceptions if the user enters something which is not an int
			while(true) {
				try {
					System.out.print("Enter the track code for the song you want to add or 0 to quit: ");
					trkCode = input.nextInt();
					break;
				}
				catch (Exception e) {
					input.nextLine();
					System.err.print("ERROR: ");
					System.out.println("The track code must be an integer value");
				}
			}
			input.nextLine();
			
			// checks if the user has selected to quit (by entering 0) this will then print the playlist which they created
			if(trkCode == 0) {
				if(playlist.size() != 0) {
					System.out.print("This is your playlist: ");
					for(int i=0; i<playlist.size(); i++) {
						System.out.print(playlist.get(i).getTitle()+" by "+playlist.get(i).getArtist());
						// this will make sure that the last track does not have a comma after it
						if(i != playlist.size() - 1) {
							System.out.print(", ");
						}
					}
					System.out.println("");
				}
				else {
					System.out.println("There were no songs selected.");
					return null; // this line makes sure shufflePlay will not crash if the user does not select any songs in the playlist
				}
				break;
			}
			// linear search to find the track code that the user entered, if it is found it will add the song to the playlist
			boolean found = false;
			for(int i=0; i<trks.length; i++) {
				if (trks[i] != null) {
					if(trks[i].getTrackCode() == trkCode) {
						found = true;
						System.out.println("The track "+trks[i].getTitle()+" by "+trks[i].getArtist()+" has been added to the playlist.");
						System.out.println("");
						playlist.add(trks[i]);
						trks[i] = null;
						break;
					}
				}
			}
			if(!found) {
				System.err.print("ERROR: ");
				System.out.println("The track with code "+trkCode+" was not found.");
			}
		}
		return playlist;
	}
	
	/**
	 * This will display songs in a random order which have been played from a playlist
	 * If a song was not able to be played then a suitable message will be displayed
	 * @param jukebox
	 */
	public static void shufflePlay(Jukebox jukebox, ArrayList<AudioTrack> playlist) {
		if(playlist != null) {
			System.out.println(jukebox.shuffle(playlist));
		}
		else {
			System.out.println("Please create a playlist before trying to shuffle");
		}
	}
	
	/**
	 * This will display the top 10 most played songs within the jukebox
	 * @param jukebox
	 */
	public static void displayTop10(Jukebox jukebox) {
		AudioTrack[] trks = jukebox.getTracks();
		
		// below creates a list containing the 10 most played songs
		AudioTrack[] mostPlayedTrks = new AudioTrack[10];
		int indexAt = 0; 
		// the below code is used to place the top 10 most played songs into the mostPlayedTrks array
		for(int i=0; i<10; i++) {
			int mostPlayed = 0; // resets mostPlayed
			for(int j=0; j<trks.length; j++) {
				// makes sure that if an element is null it is not checked 
				if(trks[j] != null) { 
					// this will get the track with the most plays within the list
					int numOfPlays = trks[j].getNumOfPlays(); 
					// checks to see if the current track has the most number of plays in the array trks
					if(numOfPlays >= mostPlayed) { 
						mostPlayed = numOfPlays; //makes the current number of plays the highest number of plays
						indexAt = j; 
						mostPlayedTrks[i] = trks[j]; // copies the highest played track to the mostPlayedTrks array
					}
				}
			}
			trks[indexAt] = null; //removes the highest value from the array trks so that the next highest value can be found
		}

		// displays the top 10 tracks
		for(int i=0; i<mostPlayedTrks.length; i++) {
			if(mostPlayedTrks[i] != null) {
				System.out.println("Number of Plays: "+mostPlayedTrks[i].getNumOfPlays());
				System.out.println(mostPlayedTrks[i].getDetails());
			}
		}
	}
	
	// --------------------------------------------- below is methods that were created for test purposes -----------------------------------------------------------
	

	
	/**
	 * this will add 5 audiotracks to jukebox
	 * @param jukebox
	 */
	public static void add5AudioTracks(Jukebox jukebox) {
		AudioTrack trk1 = new AudioTrack("Turn up the Radio", "OK Go", 3, Genre.POP, "mp3");
		AudioTrack trk2 = new AudioTrack("Cold as Ice", "Foreigner", 3, Genre.ROCK, "mp3");
		AudioTrack trk3 = new AudioTrack("I Want to Know What Love is", "Foreigner", 5, Genre.ROCK, "mp3");
		AudioTrack trk4 = new AudioTrack("Reelin' in The Years", "Steely Dan", 4, Genre.ROCK, "mp3");
		AudioTrack trk5 = new AudioTrack("What Is Love", "Haddaway", 4, Genre.DANCE, "wav");
		
		jukebox.addTrack(trk1);
		jukebox.addTrack(trk2);
		jukebox.addTrack(trk3);
		jukebox.addTrack(trk4);
		jukebox.addTrack(trk5);
	}

	/**
	 * this will add 15 audio tracks to jukebox
	 * @param jukebox
	 */
	public static void add15AudioTracks(Jukebox jukebox) {
		AudioTrack trk1 = new AudioTrack("Turn up the Radio", "OK Go", 3, Genre.POP, "mp3");
		AudioTrack trk2 = new AudioTrack("Cold as Ice", "Foreigner", 3, Genre.ROCK, "mp3");
		AudioTrack trk3 = new AudioTrack("I Want to Know What Love is", "Foreigner", 5, Genre.ROCK, "mp3");
		AudioTrack trk4 = new AudioTrack("Reelin' in The Years", "Steely Dan", 4, Genre.ROCK, "mp3");
		AudioTrack trk5 = new AudioTrack("What Is Love", "Haddaway", 4, Genre.DANCE, "wav");
		
		AudioTrack trk6 = new AudioTrack("Everybody Wants to Rule the World", "Tears For Fears", 4, Genre.ROCK, "wav");
		AudioTrack trk7 = new AudioTrack("Rebel Yell", "Bily Idol", 5, Genre.ROCK, "wav");
		AudioTrack trk8 = new AudioTrack("You're Only Human (Second Wind)", "Billy Joel", 5, Genre.ROCK, "wav");
		AudioTrack trk9 = new AudioTrack("It's Mine", "Mystery Skulls", 3, Genre.DANCE, "wav");
		AudioTrack trk10 = new AudioTrack("Carry on Wayward Son", "Kansas", 5, Genre.ROCK, "wav");
		
		AudioTrack trk11 = new AudioTrack("Symphony No.2", "Beethoven", 1, Genre.CLASSICAL, "mp3");
		AudioTrack trk12 = new AudioTrack("ATLiens", "OutKast", 3, Genre.OTHER, "wav");
		AudioTrack trk13 = new AudioTrack("L.A Jazz Song", "Booker T. & the M.G's", 5, Genre.JAZZ, "mp3");
		AudioTrack trk14 = new AudioTrack("Heybb!", "binki", 3, Genre.OTHER, "wav");
		AudioTrack trk15 = new AudioTrack("Summer Nights", "SIAMES", 5, Genre.ROCK, "mp3");

		
		jukebox.addTrack(trk1);
		jukebox.addTrack(trk2);
		jukebox.addTrack(trk3);
		jukebox.addTrack(trk4);
		jukebox.addTrack(trk5);
		
		jukebox.addTrack(trk6);
		jukebox.addTrack(trk7);
		jukebox.addTrack(trk8);
		jukebox.addTrack(trk9);
		jukebox.addTrack(trk10);
		
		jukebox.addTrack(trk11);
		jukebox.addTrack(trk12);
		jukebox.addTrack(trk13);
		jukebox.addTrack(trk14);
		jukebox.addTrack(trk15);
		
	}
	
	/**
	 * This is used to play songs a certain number of times.
	 * @param jukebox
	 */
	public static void play10(Jukebox jukebox) {
		add15AudioTracks(jukebox);
		AudioTrack[] trks = jukebox.getTracks();
		// plays track 8, 2 times
		for(int i=0; i<2; i++) {
			jukebox.playTrack(trks[7]);
		}
		// plays track 1, 18 times
		for(int i=0; i<18; i++) {
			jukebox.playTrack(trks[0]);
		}
		// plays track 3, 14 times
		for(int i=0; i<14; i++) {
			jukebox.playTrack(trks[2]);
		}
		// plays track 15, 10 times
		for(int i=0; i<10; i++) {
			jukebox.playTrack(trks[14]);
		}
		// plays track 4, 3 times
		for(int i=0; i<3; i++) {
			jukebox.playTrack(trks[3]);
		}
		// plays track 7, 8 times
		for(int i=0; i<8; i++) {
			jukebox.playTrack(trks[6]);
		}
		// plays track 9, 11 times
		for(int i=0; i<11; i++) {
			jukebox.playTrack(trks[8]);
		}
		// plays track 10, 16 times
		for(int i=0; i<16; i++) {
			jukebox.playTrack(trks[9]);
		}
		// plays track 11, 4 times
		for(int i=0; i<4; i++) {
			jukebox.playTrack(trks[10]);
		}
		// plays track 5, 2 times
		for(int i=0; i<2; i++) {
			jukebox.playTrack(trks[4]);
		}
	}

}
