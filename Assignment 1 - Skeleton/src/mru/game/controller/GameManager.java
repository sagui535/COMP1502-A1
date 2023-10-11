package mru.game.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList; //added
import java.util.Scanner;	//added

import mru.game.model.Player;	//added
import mru.game.view.AppMenu;


public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	private final String FILE_PATH = "res/CasinoInfo.txt";
	ArrayList<Player> players;
	AppMenu appMen;
	private Scanner enter;
	
	public GameManager() throws Exception {
		players = new ArrayList<>();
		appMen = new AppMenu();
		enter = new Scanner(System.in);
		loadData();
		
		launchApp();
	}
	
	private void launchApp() throws Exception {
		boolean flag = true;
		char option = appMen.showMainMenu();
		
		while (flag) {
			switch (option) {
			case 'p':
				playGame();
				break;
			case 's':
				Search();
				break;
			case 'e':
				Save();
				flag = false;
			}
			
		}
	}
		
		
	private void playGame() {
		// TODO Auto-generated method stub
		
	}


	

	private void Search() {
		char option = appMen.showSubMenu();
		
		switch (option) {
		case 't':
			FindTopPlayer();
			break;
		case 'n':
			Player ply = searchByName();
			appMen.showPlayer(ply);
			break;
		case 'b':
			appMen.showMainMenu();
			break;
			
		}
		
		
	}

	private Player searchByName() {
		String name = appMen.promtName().toLowerCase();
		Player ply = null;
		
		for (Player p: players) {
			if (p.getName().toLowerCase().equals(name)) {
				ply = p;
				break;
			}
		}
		
		//not from video
		if (ply != null) {
	        System.out.println("   		    -PLAYER INFO-");
	        System.out.println("+==================+==================+==================+");
	        System.out.println("|NAME              |# WINS            |BALANCE           |");
	        System.out.println("+==================+==================+==================+");
	        System.out.println("|" + formatString(ply.getName(), 18) + "|" +
	                formatString(String.valueOf(ply.getNumOfWins()), 18) + "|" +
	                formatString(String.valueOf(ply.getBalance()), 18) + "|");
	        System.out.println("+==================+==================+==================+");
	  
	        System.out.println("Press \"Enter\" to continue...");
		    enter.nextLine();
		    appMen.showMainMenu();
		
		} else {
	        System.out.println("Player does not exist. Try again!");
	    }
		//end
		
		return ply;
		
	}

	private void FindTopPlayer() {
		// not from video
		if(players.isEmpty()) {
			System.out.println("Player not found. Try again!");
			return;
		}
		
		int maxWins = Integer.MIN_VALUE;
		ArrayList<Player> topPlayers = new ArrayList<>();
		
		for (Player p: players) {
			if (p.getNumOfWins() > maxWins) {
				maxWins = p.getNumOfWins();
				topPlayers.clear();
				topPlayers.add(p);
			} else if (p.getNumOfWins() == maxWins) {
				topPlayers.add(p);
			}
		}
		
		System.out.println("	     -TOP PLAYERS-");
	    System.out.println("+==================+==================+");
	    System.out.println("|NAME              |# WINS            |");
	    System.out.println("+==================+==================+");
	    
	    for (Player topPlayer : topPlayers) {
	    	System.out.println("|" + formatString(topPlayer.getName(), 18) + "|" +
	    	formatString(String.valueOf(topPlayer.getNumOfWins()), 18) + "|");
	        System.out.println("+==================+==================+");
	    }
	    
	    System.out.println("Press \"Enter\" to continue...");
	    enter.nextLine();
	    appMen.showMainMenu();
	    
	    
	}
	//added method to format string to specific length
	private String formatString(String input, int length) {
	    if (input.length() >= length) {
	        return input.substring(0, length);
	    } else {
	        StringBuilder formattedString = new StringBuilder(input);
	        while (formattedString.length() < length) {
	            formattedString.append(" ");
	        }
	        return formattedString.toString();
	    }
	}
	
	        
	
		
	

	private void Save() throws IOException {
		System.out.println("Saving...");
		System.out.println("Done! Please visit us again!");
		
		File db = new File(FILE_PATH);
		PrintWriter pw = new PrintWriter(db);
		
		for (Player p: players) {
			pw.println(p.format());
		}
		pw.close();
	}

	private void loadData() throws Exception {
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splitLine;
		
		
		if (db.exists()) {
			Scanner fileReader = new Scanner(db);
			while (fileReader.hasNextLine()) {
				currentLine = fileReader.nextLine();
				splitLine = currentLine.split(",");
				Player p = new Player(splitLine[0], Double.parseDouble(splitLine[1]), Integer.parseInt(splitLine[2]));
				players.add(p);
			}
		fileReader.close();
		}
		
	}
}
