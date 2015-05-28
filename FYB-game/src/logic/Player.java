package logic;

import java.util.ArrayList;

public class Player {
	
	private int handSize;
	private ArrayList<Card> hand;
	
	private int score;
	
	
	public void dealHand (int handSize, Deck currentDeck) {
		this.handSize = handSize;
		this.hand = new ArrayList<Card>(handSize);
		for (int i = 0; i < handSize; i++) {
			hand.add(currentDeck.draw());
		}
	}
	
	
	public Player() {
		this.handSize = 0;
		this.hand = null;
		
		this.score = 0;
	}

}
