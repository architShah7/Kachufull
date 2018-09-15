package kachuful;

import java.util.ArrayList;

public class Player {
	private String name;
	private int totalScore;
	private int roundScore;
	private int bet;
	private ArrayList<Card> hand = new ArrayList<>();

	public Player() {
		totalScore = 0;
		roundScore = 0;
	}

	public Player(String name) {
		this.name = name;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	private int getRoundScore() {
		return roundScore;
	}

	private void setRoundScore(int score) {
		this.roundScore = score;
	}

	public void setHand(Card card) {
		hand.add(card);
	}

	public String getHand() {
		return String.valueOf(hand);
	}
	
	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	
	public int getHandSize() {
		return hand.size();
	}

	public void clearHand() {
		hand.clear();
	}
	
	public void removeCard(Card card) {
		hand.remove(card);
	}
	
	public Card getCard(int num){
		return hand.get(num-1);
	}
	
	// Make a highest and lowest card method
		public Card highestCard() {
			int higherNum = 0;
			Card Highest = new Card();
			for (Card card : hand) {
				if (card.getNumber() > higherNum) {
					if (card.getNumber() == 1) {
						Highest = card;
						return Highest;
					}else {
						higherNum = card.getNumber();
						Highest = card;
					}
				}
			}
			return Highest;
		}

		public Card lowestCard() {
			int lowerNum = 14;
			Card Lowest = new Card();
			for (Card card : hand) {
				if (card.getNumber() < lowerNum) {
					if (card.getNumber() == 1 && hand.size() == 1) {
						return card;
					}else if (card.getNumber() == 1){
						
					}
					else {
						lowerNum = card.getNumber();
						Lowest = card;
					}
				}
			}
			return Lowest;
		}

	public int bet(String dominant) {
		int bet = 0;
		for (Card card : hand) {
			if (card.getSuit().equals(dominant)) {
				bet++;
			}
		}
		return bet;
	}
	
	public Card makeMove(String mainSuit, String dominant){
		if(mainSuit == ""){
			System.out.println(highestCard() + " Highest");
			return highestCard();
		}else{
			for(Card card: hand){
				if(card.getSuit().equals(mainSuit)){
					return card;
				}
			}
		}
		
		for(Card card: hand){
			if(card.getSuit().equals(dominant)){
				return card;
			}
		}
		
		Card fish = new Card();
		fish = lowestCard();
		System.out.println(lowestCard() + " Lowest");
		return fish;
	}
}
