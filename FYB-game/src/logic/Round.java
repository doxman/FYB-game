package logic;

import java.util.Arrays;

public class Round {
	
	//private int roundNumber;
	private int numPlayers;
	private int dealerNumber;
	private int cardNumber;
	private int trumpSuit;
	
	//private boolean inverseRound;
	private boolean noTrump;
	
	private int tricksTaken [];
	private int betsMade [];
	
	private Deck roundDeck;
	
	private Player[] players;
	
	
	// True if arg2 is greater, false if arg1 is greater
	private boolean compareCards(Card arg1, Card arg2) {
		// arg1 is the card that was played earlier, thus has suit priority
		if (noTrump) {
			return (arg1.getSuit() != arg2.getSuit() ? false : arg1.getRank() < arg2.getRank());
		}
		return (arg2.getSuit() == trumpSuit && arg1.getSuit() != trumpSuit ? true
				: (arg1.getSuit() != arg2.getSuit() ? false : arg1.getRank() < arg2.getRank()));
	}
	
	
	private void dealHands () {
		for (int i = 0; i < numPlayers; i++) {
			players[i].dealHand(cardNumber, roundDeck);
		}
		
		// TODO: Implement this
		/*
		if (inverseRound) {
			// Each player sees opponents' cards, not their own
		} else {
			// Each player sees only their own card and the trump
		}*/
		
		if (!noTrump) {
			Card trump = roundDeck.draw();
			trumpSuit = trump.getSuit();
			// TODO: Display this graphically
			System.out.println("Trump card this round is: " + trump);
		}
		
	}
	
	
	private void collectBets() {
		int betTotal = 0;
		int i = (dealerNumber + 1) % numPlayers;
		do {
			System.out.println("Player " + i + "'s turn to bet");
			int bet = 0;
			if (i == dealerNumber) {
				// Disallow the option that would make betTotal == cardNumber
				bet = players[i].makeBet(cardNumber - betTotal);
			} else {
				bet = players[i].makeBet(-1);
			}
			betsMade[i] = bet;
			betTotal += bet;
			i = (i+1) % numPlayers;
		} while (i != (dealerNumber + 1) % numPlayers); // Loop through players until we reach the dealer, who is last
	}
	
	
	private void playOutTricks() {
		int startingPlayer = (dealerNumber + 1) % numPlayers;
		for (int i = 0; i < cardNumber; i++) {
			System.out.println("Player " + startingPlayer + "'s turn to play");
			Card winningCard = players[startingPlayer].playCard(-1);
			System.out.println("Player " + startingPlayer + " played " + winningCard);
			int winningPlayer = startingPlayer;
			
			int roundSuit = winningCard.getSuit(); // Don't change this in the loop; it last the whole trick
			
			int j = (startingPlayer + 1) % numPlayers;
			while (j != startingPlayer) {
				System.out.println("Player " + j + "'s turn to play");
				Card next = players[j].playCard(roundSuit);
				System.out.println("Player " + j + " played " + next);
				if (compareCards(winningCard, next)) {
					winningCard = next; // This is now the current highest card
					winningPlayer = j;
				}
				
				j = (j+1) % numPlayers;
			} 
			// After trick plays out, resolve who gets it
			tricksTaken[winningPlayer] += 1;
			startingPlayer = winningPlayer; // Winning player starts next trick
		}
	}
	
	
	private void resolveScores() {
		for (int i = 0; i < numPlayers; i++) {
			int score = (tricksTaken[i] == betsMade[i] ? 10 + (int)Math.pow(tricksTaken[i], 2): 0);
			players[i].scoreRound(score);
			System.out.println("Player " + i + " has current score: " + players[i].getScore());
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
	public Round (int roundNumber, int numPlayers, int dealerNumber, Player[] players) {
		//this.roundNumber = roundNumber;
		this.numPlayers = numPlayers;
		this.dealerNumber = dealerNumber;
		this.cardNumber = (roundNumber > 8 ? 17 - roundNumber : roundNumber);
		this.trumpSuit = 0; // Defaults to no suit in case round is no-trump
		
		//this.inverseRound = (roundNumber == 16);
		this.noTrump = (roundNumber == 9);
		
		this.tricksTaken = new int[numPlayers];
		this.betsMade = new int[numPlayers];
		
		this.roundDeck = new Deck(); // Deck always starts fresh
		
		this.players = players;
		
		Arrays.fill(tricksTaken, 0); // Start with zero tricks taken per player
		
		this.playRound();
	}

}
