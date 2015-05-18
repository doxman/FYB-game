package logic;

import java.util.Arrays;

public class Round {
	
	private int roundNumber;
	private int numPlayers;
	private int dealerNumber;
	private int cardNumber;
	private int trumpSuit;
	
	private boolean inverseRound;
	private boolean noTrump;
	
	private int tricksTaken [];
	private int betsMade [];
	
	private Deck roundDeck;
	
	
	private void dealHands () {
		for (int i = 0; i < cardNumber; i++) {
			for (int j = 0; j < numPlayers; j++) {
				Card next = roundDeck.draw();
				// Add card to player j's hand
			}
		}
		
		if (inverseRound) {
			// Each player sees opponents' cards, not their own
		} else {
			// Each player sees only their own card and the trump
		}
		
		if (!noTrump) {
			Card trump = roundDeck.draw();
			trumpSuit = trump.getSuit();
			// Display trump card
		}
		
	}
	
	
	private void collectBets() {
		int betTotal = 0;
		int i = (dealerNumber + 1) % numPlayers;
		do {
			int bet = 0;
			// Present bet options
			if (i == dealerNumber) {
				// Disallow the option that would make betTotal == numCards, if such an option exists
			}
			betsMade[i] = bet;
			betTotal += bet;
			i = (i+1) % numPlayers;
		} while (i != dealerNumber); // Loop through players until we reach the dealer, who is last
	}
	
	
	private void playOutTricks() {
		for (int i = 0; i < cardNumber; i++) {
			int winningPlayer = 0;
			for (int j = 0; j < numPlayers; j++) {
				// Everyone plays one card in order, previously played cards are shown
			}
			// After trick plays out, resolve who gets it
			tricksTaken[winningPlayer] += 1;
		}
	}
	
	
	private void resolveScores() {
		for (int i = 0; i < numPlayers; i++) {
			int score = (tricksTaken[i] == betsMade[i] ? 10 + (int)Math.pow(tricksTaken[i], 2): 0);
			// Return this value to the respective player
		}
	}
	
	
	private void playRound () {
		dealHands();
		collectBets();
		playOutTricks();
		resolveScores();
	}
	
	/**
	 * Create and start a new round
	 * @param roundNumber The number of the round; this determines gameplay details for that round
	 * @param numPlayers  The number of players in the round
	 * @param dealerNumber The player that is dealer for the current round (determines play order)
	 */
	public Round (int roundNumber, int numPlayers, int dealerNumber) {
		this.roundNumber = roundNumber;
		this.numPlayers = numPlayers;
		this.dealerNumber = dealerNumber;
		this.cardNumber = (roundNumber > 8 ? 17 - roundNumber : roundNumber);
		this.trumpSuit = 0; // Defaults to no suit in case round is no-trump
		
		this.inverseRound = (roundNumber == 16);
		this.noTrump = (roundNumber == 9);
		
		this.tricksTaken = new int[numPlayers];
		this.betsMade = new int[numPlayers];
		
		this.roundDeck = new Deck(); // Deck always starts fresh 
		
		Arrays.fill(tricksTaken, 0); // Start with zero tricks taken per player
		
		this.playRound();
	}

}
