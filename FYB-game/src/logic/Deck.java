package logic;

import java.util.Collections;
import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> cards = new ArrayList<Card> ();
	
	/**
	 * Shuffles the cards in the deck
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Simulates drawing a card
	 * @return The top card in the deck
	 * @throws IllegalStateException if deck is empty
	 */
	public Card draw() throws IllegalStateException {
		if (cards.size() == 0)
			throw new IllegalStateException();
		return cards.remove(0);
	}
	
	/**
	 * 
	 * @return The number of Cards left in the deck
	 */
	public int getNumCardsInDeck() {
		return cards.size();
	}
	
	/**
	 * Initializes the deck with 52 cards and shuffles it
	 */
	public Deck() {
		for (int suit = 1; suit <= 4; suit++) {
			for (int rank = 2; rank <= 14; rank++) {
				cards.add(new Card(suit,rank));
			}
		}
		this.shuffle();		
	}
}
