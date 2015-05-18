package logic;

public class Card {

	private int suit;
	private int rank;
	
	/**
	 * 
	 * @return Numeric value of card suit
	 */
	protected int getSuit() {
		return suit;
	}
	
	/**
	 * 
	 * @return Numeric value of card rank
	 */
	protected int getRank() {
		return rank;
	}
	
	/**
	 * 
	 * @return Symbolic value of card suit
	 */
	public String getSuitSymbol() {
		if (suit == 1) {
			return "\u2663";
		}
		if (suit == 2) {
			return "\u2666";
		}
		if (suit == 3) {
			return "\u2665";
		}
		return "\u2660";
	} 
	
	/**
	 * 
	 * @return String value of card rank ('J', 'Q', 'K', 'A' for high ranks)
	 */
	public String getRankString() {
		if (rank > 1 && rank < 11) {
			return "" + rank; // String representation of integer
		}
		if (rank == 11) {
			return "J";
		}
		if (rank == 12) {
			return "Q";
		}
		if (rank == 13) {
			return "K";
		}
		return "A";
	}
	
	/**
	 * 
	 * @return String representation of the card (suit symbol and rank)
	 */
	public String toString () {
		return this.getRankString() + this.getSuitSymbol();
	}
	
	/**
	 * Creates a new Card with the given suit and rank.
	 * @param suit 1=Clubs, 2=Diamonds, 3=Hearts, 4=Spades
	 * @param rank A value between 2 and 14; 11=Jack, 12=Queen, 13=King, 14=Ace 
	 */
	public Card(int suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
}
