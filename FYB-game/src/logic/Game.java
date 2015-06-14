package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
	
	public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); // Global console input

	public static void main(String[] args) {
		
		System.out.print("Enter number of players: ");
		int numPlayers = 4; // Default for try-catch
		try {
			numPlayers = Integer.parseInt(input.readLine());
		} catch(IOException i) {
			i.printStackTrace();
		}
		Player [] players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player();
		}
		int dealerNumber = numPlayers - 1; // Dealer starts as last player
		
		for (int i = 1; i <= 16; i++) {
			new Round(i, numPlayers, dealerNumber, players);
			dealerNumber = (dealerNumber + 1) % numPlayers;
		}

	}

}
