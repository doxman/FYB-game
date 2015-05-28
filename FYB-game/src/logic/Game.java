package logic;

import java.util.Arrays;

public class Game {

	public static void main(String[] args) {
		
		int numPlayers = 4; // TODO: Get input for this number
		Player [] players = new Player[4];
		Arrays.fill(players, new Player());
		int dealerNumber = numPlayers - 1; // Dealer starts as last player
		
		for (int i = 1; i <= 16; i++) {
			Round current = new Round(i, numPlayers, dealerNumber, players);
			dealerNumber = (dealerNumber + 1) % numPlayers;
		}

	}

}
