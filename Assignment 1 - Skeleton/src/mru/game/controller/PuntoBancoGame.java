package mru.game.controller;

import mru.game.model.Player;

public class PuntoBancoGame {
    private CardDeck deck;

    public PuntoBancoGame() {
        deck = new CardDeck();
    }

    public void play(Player player, String betChoice, double betAmount) {
        // Get two cards for the player and banker
        Card playerCard1 = deck.getDeck().remove(0);
        Card playerCard2 = deck.getDeck().remove(0);
        Card bankerCard1 = deck.getDeck().remove(0);
        Card bankerCard2 = deck.getDeck().remove(0);

        // Calculate the initial totals
        int playerTotal = calculateTotal(playerCard1, playerCard2);
        int bankerTotal = calculateTotal(bankerCard1, bankerCard2);

        // Check for natural win or tie
        if (playerTotal >= 8 || bankerTotal >= 8) {
            checkWin(player, playerTotal, bankerTotal, betChoice, betAmount);
            return;
        }

        // Player draws a card if their total is 0-5
        if (playerTotal <= 5) {
            Card playerCard3 = deck.getDeck().remove(0);
            playerTotal += calculateCardValue(playerCard3);
        }

        // Banker draws a card based on complex rules...
        if (shouldBankerDraw(bankerTotal, playerTotal)) {
            Card bankerCard3 = deck.getDeck().remove(0);
            bankerTotal += calculateCardValue(bankerCard3);
        }

        // Determine the winner and adjust player's balance
        checkWin(player, playerTotal, bankerTotal, betChoice, betAmount);
    }

    private int calculateTotal(Card card1, Card card2) {
        return calculateCardValue(card1) + calculateCardValue(card2);
    }

    private int calculateCardValue(Card card) {
        int rank = card.getRank();
        if (rank >= 10) {
            return 0;
        } else {
            return rank;
        }
    }

    private boolean shouldBankerDraw(int bankerTotal, int playerTotal) {
        // Banker draws a card 
    	
    	    
    	    if (bankerTotal <= 2) {
    	        return true;
    	    } else if (bankerTotal == 3) {
    	        return playerTotal != 8;
    	    } else if (bankerTotal == 4) {
    	        return playerTotal >= 2 && playerTotal <= 7;
    	    } else if (bankerTotal == 5) {
    	        return playerTotal >= 4 && playerTotal <= 7;
    	    } else if (bankerTotal == 6) {
    	        return playerTotal == 6 || playerTotal == 7;
    	    } else {
    	        return false;
    	    }
    	}
    

    private void checkWin(Player player, int playerTotal, int bankerTotal, String betChoice, double betAmount) {
    
        String winner;
        if (playerTotal > bankerTotal) {
            winner = "player";
        } else if (bankerTotal > playerTotal) {
            winner = "banker";
        } else {
            winner = "tie";
        }
    
        if (betChoice.equals(winner)) {
            // Player wins the bet
            double winnings = betChoice.equals("tie") ? betAmount * 5 : betAmount;
            player.setBalance(player.getBalance() + winnings);
            System.out.println("You won! Your new balance is: " + player.getBalance());
        } else {
            // Player loses the bet
            player.setBalance(player.getBalance() - betAmount);
            System.out.println("You lost. Your new balance is: " + player.getBalance());
        }
    }
}
