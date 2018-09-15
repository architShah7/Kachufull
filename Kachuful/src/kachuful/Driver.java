package kachuful;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String dominant = "";
		int playerTurn = 0;
		
		Deck deck = new Deck();

		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		
		Player players[] = new Player[4];
		players[0] = player1;
		players[1] = player2;
		players[2] = player3;
		players[3] = player4;
		
		System.out.println("Welcome to the Kachuful Game!");
		for (int gameNum = 1; gameNum < 10; gameNum++) {
			if(playerTurn == 4){
				playerTurn = 0;
			}
			createDeck(deck);
			// System.out.println(deck+"\n");
			deck.shuffleDeck();
			// deck.printStack();

			if (gameNum == 1 || gameNum == 5 || gameNum == 9) {
				dominant = "spade";
			} else if (gameNum == 2 || gameNum == 6) {
				dominant = "diamond";
			} else if (gameNum == 3 || gameNum == 7) {
				dominant = "club";
			} else {
				dominant = "heart";
			}

			if (gameNum <= 5) {
				for (int i = 0; i < gameNum; i++) {
					player1.setHand(deck.dealACard());
					player2.setHand(deck.dealACard());
					player3.setHand(deck.dealACard());
					player4.setHand(deck.dealACard());
				}
			} else {
				for (int i = gameNum - 5; i < 5; i++) {
					player1.setHand(deck.dealACard());
					player2.setHand(deck.dealACard());
					player3.setHand(deck.dealACard());
					player4.setHand(deck.dealACard());
				}
			}
		
			
			System.out.println("");
			int totalBet = 0;
			playerTurn=(gameNum%4)-1;
			for(int z=0; z<4; z++){
				if(playerTurn == 4){
					playerTurn = 0;
				}
				
				if(players[playerTurn].equals(player1)){
					System.out.println("\nThese are your cards:\n" + player1.getHand() + "\nThe dominant suit is " + dominant + "s");
					System.out.print("Please Enter Your Bet: "); //Bet according to turn
					player1.setBet(in.nextInt());
				}else{
					players[playerTurn].setBet(players[playerTurn].bet(dominant));
					System.out.println(players[playerTurn].getBet());
				}
				totalBet = totalBet + players[playerTurn].getBet();
				System.out.println("Now Total Bet is "+totalBet);
				
				if(totalBet == players[playerTurn].getHandSize() && z == 3){
					totalBet = totalBet - players[playerTurn].getBet();
					players[playerTurn].setBet(validate(playerTurn, players[playerTurn].getHandSize(), totalBet));
					System.out.println(validate(playerTurn, players[playerTurn].getHandSize(), totalBet));
					totalBet = totalBet + players[playerTurn].getBet();
				}
				playerTurn++;
			}

			System.out.println("Total Bet: "+totalBet);
			//Start Playing
			int handSize = player1.getHandSize();
			for(int i=0; i<handSize; i++){
				
				String mainSuit = "";
				Card[] roundCards = new Card[4]; //Make this an arraylist
				playerTurn=(gameNum%4)-1;
				for(int k=0; k<4; k++){
					Card cardPlaced = new Card();
					if(playerTurn == 4){
						playerTurn = 0;
					}
					
					if(players[playerTurn].equals(player1)){
						System.out.println("\nThese are your cards:\n" + player1.getHand() + " The dominant suit is " + dominant + "s");
						System.out.print("(Turn #"+(k+1)+") Please Place a Card: ");
						int card = in.nextInt();
						cardPlaced = player1.getCard(card);
						player1.removeCard(cardPlaced);
					}else{
						cardPlaced = players[playerTurn].makeMove(mainSuit, dominant);
						System.out.println("\n"+players[playerTurn].getHand() + "\n");
						players[playerTurn].removeCard(cardPlaced);
						
					}
					
					if(k==0){
						mainSuit = cardPlaced.getSuit();
					}
					roundCards[k] = cardPlaced;
					playerTurn++;
				}
				
				for(Card card: roundCards){
					System.out.println(card);
				}
			}
			
			
			player1.clearHand();
			player2.clearHand();
			player3.clearHand();
			player4.clearHand();

			playerTurn++;
		}

	}

	private static int validate(int playerTurn, int handSize, int totalBet) {
		Scanner in = new Scanner(System.in);
		if(playerTurn == 0){
			boolean valid = false;
			int newBet=0;
			System.out.println("You cannot bet that number as: total bet = hand size");
			while(!valid){
				System.out.print("Please bet again: ");
				String temp = in.nextLine();
				try{
					newBet = Integer.valueOf(temp);
					if((newBet >=0 && newBet<=handSize) && (newBet+totalBet != handSize)){
						return newBet;
					}
				}catch(Exception e){
					System.out.println("Please Enter a Valid Input!");
				}
			}
		}
		int bet = handSize - totalBet + 1;
		return bet;
	}

	public static void createDeck(Deck deck) {
		for (int i = 0; i < 52; i++) {
			String suit = "";
			if (i < 13) {
				suit = "heart";
				deck.createDeck(new Card(suit, i + 1));
			} else if (i < 26) {
				suit = "diamond";
				deck.createDeck(new Card(suit, i + 1 - 13));
			} else if (i < 39) {
				suit = "spade";
				deck.createDeck(new Card(suit, i + 1 - 26));
			} else if (i < 52) {
				suit = "club";
				deck.createDeck(new Card(suit, i + 1 - 39));
			}
		}
	}

}
