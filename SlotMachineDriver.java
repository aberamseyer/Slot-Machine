/*
 * File name: SlotMachineDriver.java 
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

import java.util.Scanner;

/**
 * Drives the slot machine program. Simulates a slot machine game with betting and earnings of coins
 * 
 * @author Abe Ramseyer
 *
 */
public class SlotMachineDriver
{

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		SlotMachine game = new SlotMachine();
		int choice = 0;
	
		System.out.println("Thank you for choosing the Redbird IT Slot Machines. " +
							"\nOn the following screen, you will find a menu with three choices." +
							"\nThe first choice will be to play the game." +
							"\nThe second choice will be to check your balance." +
							"\nThe third choice will be to quit playing the game." +
							"\nPlaying the game:" +
							"\nYou will begin with 10 coins." +
							"\nYou can bet 1 - 5 coins." +
							"\nRows evaluated to determine winnings are determined by the number of coins bet." +
							"\n\t1 coin - second row" +
							"\n\t2 coins - first and second rows" +
							"\n\t3 coins - first, second, and third rows" +
							"\n\t4 coins - first, second, third rows, and diagonal top left to bottom right" +
							"\n\t5 coins - all rows and both diagonals" +
							"\nYour balance cannot be negative." +
							"\nYou cannot bet more coins than what is in your balance." +
							"\nPress enter to continue");
		keyboard.nextLine();
		
		while(choice != 3) {
			if(game.getCoins() <= 0) {
				System.out.println("Game Over, thanks for playing");
				choice = 3;
				continue;
			}
			game.printMenu();
			choice = game.readInput(keyboard);
				switch(choice) {
				case 1:
					game.play(keyboard);
					break;
				case 2:
					System.out.println("Current balance: " + game.getCoins());
					break;
				case 3:
					System.out.println("Thanks for playing!");
					break;
				default:
					System.out.println("Invlid menu choice, try again.");
			}		
		}
	}

}
