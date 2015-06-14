package logic;

import java.io.IOException;
import java.util.ArrayList;

public class Player {
	
	private int handSize;
	private ArrayList<Card> hand;
	
	private int score;
	
	// Methods protected to be only called by Round
	
	/**
	 * Deal a fresh hand of cards
	 * @param handSize Size of hand to be dealt; to be passed by current round
	 * @param currentDeck Deck to deal hand from
	 */
	protected void dealHand (int handSize, Deck currentDeck) {
		this.handSize = handSize;
		this.hand = new ArrayList<Card>(handSize);
		for (int i = 0; i < handSize; i++) {
			hand.add(currentDeck.draw());
		}
	}
	
	/**
	 * Gives the player a choice of bets and returns their selected option
	 * Should be run after hand is dealt but before any cards are played
	 * @param illegalBet If player is dealer, a certain bet may not be allowed
	 * @return The bet chosen by this player
	 */
	protected int makeBet(int illegalBet) {
		// TODO: Make graphical implementation
		System.out.print("Your current hand is: ");
		for (int i = 0; i < handSize; i++) {
			System.out.print(hand.get(i));
			if (i != handSize - 1)
				System.out.print(", ");
			else
				System.out.println();
		}
		
		System.out.println("Available bets are: ");
		for (int i = 0; i <= handSize; i++) {
			if (i == illegalBet) // If all bets legal then illegalBet is -1
				continue;
			System.out.print(i);
			if (i < handSize)
				System.out.print(", ");
			else
				System.out.println();
		}
		int bet = -1;
		while (bet == -1) {
			try {
				bet = Integer.parseInt(Game.input.readLine());
			} catch (IOException i) {
				i.printStackTrace();
			}
			if (bet == illegalBet) {
				bet = -1;
				System.out.println("You cannot make that bet");
			}
		}
		return bet; // Default for try-catch
	}
	
	/**
	 * Play a card from this player's hand
	 * @param currentSuit Suit of the first card played this trick; -1 if this is the first card
	 * @throws IllegalStateException when hand is empty
	 * @return The card played
	 */
	protected Card playCard (int currentSuit) {
		if (handSize == 0)
			throw new IllegalStateException();
		
		// TODO: Graphical implementation
		System.out.print("Your current hand is: ");
		boolean hasCurrentSuit = false; // Set to true if user is limited by current suit
		for (int i = 0; i < handSize; i++) {
			System.out.print(hand.get(i));
			if (i != handSize - 1)
				System.out.print(", ");
			else
				System.out.println();
			if (hand.get(i).getSuit() == currentSuit)
				hasCurrentSuit = true;
		}
		System.out.println("Choose a card to play within [0," + (handSize - 1) + "]: ");
		int index = -1;
		while (index == -1) {
			try {
				index = Integer.parseInt(Game.input.readLine());
			} catch(IOException i) {
				i.printStackTrace();
			}
			if (hasCurrentSuit && hand.get(index).getSuit() != currentSuit) {
				index = -1;
				System.out.println("You must pick a card that matches the current suit, if you have any");
			}
		}
		Card played = this.hand.remove(index);
		this.handSize--;
		
		// Resolve card being played
		return played;
	}
	
	/**
	 * Increment player's score by what they got for the current round
	 * @param score Score from the current round
	 */
	protected void scoreRound(int score) {
		this.score += score;
	}
	
	/**
	 * Shows the player's total score to this point
	 * @return The player's total score
	 */
	public int getScore() {
		return this.score;
	}
	
	/** 
	 * Construct the player with an empty hand and no score
	 */
	public Player() {
		this.handSize = 0;
		this.hand = null;
		
		this.score = 0;
	}

}
