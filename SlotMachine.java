/*
 * File name: SlotMachine.java 
 *
 * Programmer: Abe Ramseyer
 * ULID: aramsey
 *
 * Date: Jan 28, 2016
 *
 * Class: IT 179
 * Lecture Section: 01
 * Lecture Instructor: Cathy Holbrook
 */
package edu.ilstu;

import java.util.Random;
import java.util.Scanner;

/**
 * Contains all the methods and variables for a slot machine instance
 * 
 * @author Abe Ramseyer
 *
 */
public class SlotMachine
{
	private final String[][] wheels = {{"Cherry", "Cherry", "Cherry", "Cherry", "Plum", "Bell", "Bar", "Cherry", "Cherry"},
									   {"Cherry", "Cherry", "Cherry", "Plum",   "Plum", "Bell", "Bar", "Cherry", "Cherry"},
									   {"Cherry", "Cherry", "Plum",   "Plum",   "Bell", "Bell", "Bar", "Cherry", "Cherry"}};		// Includes a repetition of the first 2 rows for easier processing

	Random generator = null;
	private String[][] currentRoll = null;
	private int coins;
	private int winnings;

	/**
	 * constructor to intitialize variables to slot machine defaults
	 */
	public SlotMachine()
	{
		generator = new Random();
		currentRoll = new String[3][3];
		coins = 10;
		winnings = 0;
	}

	/**
	 * returns the coins
	 */
	public int getCoins()
	{
		return coins;
	}
	
	/**
	 * Runs the algorithm for playing one roll of the slot machine
	 * @param keyboard a scanner object utilizing System.in
	 */
	public void play(Scanner keyboard)
	{
		int bet = 1;
		int errorlevel = 1;
		
		while (errorlevel == 1)
		{
			if (0 < bet && bet < 5)
			{
				System.out.print("Please enter your bet: ");
				bet = readInput(keyboard);
				if (0 > bet || bet > 5)
				{
					errorlevel = 1;
					bet = 1;
					System.out.println("Invalid choice, try again.");
				}
				else if (bet > coins) {
					errorlevel = 1;
					bet = 1;
					System.out.println("You don't have that many coins!");
				}
				else
				{
					errorlevel = 0;
					coins -= bet;
					rollSlots();
					printRoll();
					winnings = getWinnings(bet);
					System.out.println("You win " + winnings + " coin(s).");
					coins += winnings;
				}
			} 
		}
	}
	
	/*
	 * Calculates the winnings of a game based on bet amount
	 * @param bet the bet amount
	 * @return the total winnings
	 */
	private int getWinnings(int bet)
	{
		winnings = 0;
		switch (bet) {
		case 5:
			winnings += checkFruit(2, -1);
		case 4:
			winnings += checkFruit(0, 1);
		case 3:
			winnings += checkFruit(2, 0);
		case 2:
			winnings += checkFruit(0, 0);
		case 1:
			winnings += checkFruit(1, 0);
			break;
		}
	return winnings;
	}

	/*
	 * calulates the winnings on a per-row (or diagonal) basis
	 * @param row the starting row
	 * @param modifier a number to modify the indecies (used for diagonals)
	 * @return int the winnings based on this specifc row/diagonal
	 */
	private int checkFruit(int row, int modifier)
	{
		switch (currentRoll[row][0])
		{
		case "Cherry":
			return 0;
		case "Plum":
			return (currentRoll[Math.abs(row + modifier)][1] != "Plum") ? 0 :
					(currentRoll[Math.abs(row + modifier)][2] != "Plum") ? 1 :
						2;
		case "Bell":
			return (currentRoll[Math.abs(row + modifier)][1] != "Bell") ? 1 :
					(currentRoll[Math.abs(row + modifier)][2] != "Bell") ? 2 :
						3;
		case "Bar":
			return (currentRoll[Math.abs(row + modifier)][1] != "Bar") ? 2 :
					(currentRoll[Math.abs(row + modifier)][2] != "Bar") ? 3 :
						5;
		default:
			return 0;
		}
	}

	/*
	 * simulates a roll of the slot machine, fills an array with the result
	 * @return a formatted String of what was rolled
	 */
	private void rollSlots()
	{
		int startingPosition = -1;
		for(int i = 0; i < currentRoll.length; i++) {		
			startingPosition = generator.nextInt(6);	
			for(int j = 0; j < currentRoll[i].length; j++) {
				currentRoll[i][j] = wheels[i][j + startingPosition];
			}
		}
	}

	/**
	 * Prints the slot machine menu
	 */
	public void printMenu()
	{
		
		System.out.println("\nSlot Machine Menu\n");
		System.out.println("1. Play a Game");
		System.out.println("2. Check Balance");
		System.out.println("3. Quit");
		System.out.print("\nPlease enter your choice: ");
	}
	
	/**
	 * Reads the menu choice from the user
	 */
	public int readInput(Scanner keyboard)
	{
		return keyboard.nextInt();
	}
	
	/**
	 * prints the values of the current roll, used for debugging purposes
	 */
	public void printRoll()
	{
		for(int i = 0; i < currentRoll.length; i++) {
			for(int j = 0; j < currentRoll[i].length; j++) {
				System.out.print(currentRoll[i][j] + " ");
				System.out.print("\t\t");
			}
			System.out.println();
		}
	}

}
