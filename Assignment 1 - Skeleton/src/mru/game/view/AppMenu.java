package mru.game.view;
import java.util.ArrayList;
import java.util.Scanner; //added

import mru.game.model.Player; 


public class AppMenu {
	
	Scanner input;
	public AppMenu() {
		input = new Scanner(System.in);
	}
	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 * @return 
	 */
	
	public char showMainMenu() {
		System.out.println("Select one of these options:\n");
		System.out.println("\t(P) Play game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Exit\n");
		System.out.print("Enter a choice: ");
		char option = input.nextLine().toLowerCase().charAt(0);
		
		//input.nextLine();	//Flush out the Scanner buffer
		
		return option;
	}
	
	public char showSubMenu() {
		System.out.println("Select one of these options:\n");
		System.out.println("\t(T) Top player (Most number of wins)");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to Main menu\n");
		System.out.println("Enter a choice: ");
		char option = input.nextLine().toLowerCase().charAt(0);
		return option;
	}
	
	public String promtName() {
		System.out.println("What is your name: ");
		String name = input.nextLine();
		return name;
	}
	public void showPlayer(Player ply) {
		System.out.println(ply);
	}
	public String promptBetChoice() {
		System.out.println("How much would you like to bet this round: ");
		String betChoice = input.nextLine();
        return betChoice;
		
	}
	public double promptBetAmount() {
		System.out.println("Please enter your bet amount:");
        double betAmount = input.nextDouble();
        return betAmount;
	}
}
