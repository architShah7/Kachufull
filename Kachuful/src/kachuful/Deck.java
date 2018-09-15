package kachuful;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Deck {
	private Card card;
	private ArrayList<Card> cards = new ArrayList<>();
	Stack<Card> stack = new Stack<Card>();

	public Deck() {

	}

	public void createDeck(Card card) {
		cards.add(card);
	}

	public void shuffleDeck() {
		int random = 0;
		Random rn = new Random();

		for (int i = 0; i < 52; i++) {
			random = rn.nextInt(cards.size());
			stack.push(cards.get(random));
			cards.remove(random);
		}
	}

	public Card dealACard() {
		return stack.pop();
	}

	public void printStack() {
		System.out.println(stack + "\n");
	}

	public String toString() {
		return String.valueOf(cards);
	}

}
