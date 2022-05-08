package blackjack;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class containing static methods to handle game logic.
 * This implementation simulates dealing using Thread.sleep() in a looping structure.
 * This is inefficient, and should be improved upon.
 * @author Ben Hickman
 * @date 2022-04-26
 * @version 1.0.0
 */
public class Play {
    
    /**
     * Deals the first 2 cards to both the dealer and the player.
     * @param player represents the first player hand.
     * @param dealer represents the dealers hand.
     * @param deck represents the full deck with internally specified number of cards.
     * @param balance represents the internally specified starting balance.
     * @param dealSpeed represents the sleep duration for displaying cards to console.
     */
    public static void dealCards(PlayerHand player, DealerHand dealer, Deck deck, Balance balance, int dealSpeed) {
        //Sets the gambled amount to be equal to the wager. Can be increased by spliting or doubling as play continues later.
        balance.gamble(balance.getWager());
        
        for (int i = 0; i < 2; i++) {
            if (i > 0) //Skip sleep on first dealt card
                try { Thread.sleep(dealSpeed); } catch (InterruptedException e) { }
            //Add to dealer hand and display a concealed view
            dealer.addToHand(deck.drawCard());
            Output.displayConcealedDeal(player, dealer, balance);
            
            try { Thread.sleep(dealSpeed); } catch (InterruptedException e) { }
            //Add to player hand and display a concealed view
            player.addToHand(deck.drawCard());
            Output.displayConcealedDeal(player, dealer, balance);
        }
    }
    
    /**
     * Check to see if the dealer has been dealt a blackjack. This check occurs
     * before a player blackjack, meaning the dealer wins a blackjack standoff.
     * @param player represents the players initial hand.
     * @param dealer represents the dealers initial hand.
     * @return true if dealer blackjack, false otherwise.
     */
    public static boolean checkForDealerBlackjack(PlayerHand player, DealerHand dealer) {
        if (dealer.visibleValue() == 21)
            return true;
        return false;
    }
    
    /**
     * Check to see if the player has been dealt a blackjack. This check occurs
     * after a dealer blackjack. The player loses on dual blackjack, but is awarded
     * 250% payout on blackjack win.
     * @param player represents the players initial hand.
     * @param dealer represents the dealers initial hand.
     * @return true if player blackjack, false otherwise.
     */
    public static boolean checkForPlayerBlackjack(PlayerHand player, DealerHand dealer) {
        if (player.visibleValue() == 21)
            return true;
        return false;
    }
    
    /**
     * Represents the logic for performing a split when a player hand is initially
     * dealt 2 cards of the same value (different numeric values i.e J and K cannot
     * be split.) Splits can occur on hands that have already been split.
     * @param oldHand represents the hand to be split from. The hand that had the pair.
     * @param newHand represents the new hand that will contain 1 of the split cards.
     * @param dealer represents the dealer hand to allow for recursive method call.
     * @param deck represents the game deck of predetermined number of cards.
     * @param splitCounter splits can only occur 3 times (4 total hands). Counter.
     * @param hands a dataset storing each player hand.
     * @param balance a representation of the players current balance.
     */
    public static void performSplit(PlayerHand oldHand, PlayerHand newHand, DealerHand dealer, Deck deck, int splitCounter, ArrayList<PlayerHand> hands, Balance balance) {
        balance.gamble(balance.getWager());
        newHand.addToHand(oldHand.split());
        oldHand.addToHand(deck.drawCard());
        newHand.addToHand(deck.drawCard());
        userPlayPhase(newHand, dealer, deck, splitCounter, hands, balance);
    }
    
    /**
     * Represents the decision and logic of a players turn. This method is called
     * recursively to deal with split hands.
     * @param player represents the player hand being played on currently.
     * @param dealer represents the dealer hand to allow for recursive method call.
     * @param deck represents the game deck of predetermined number of cards.
     * @param splitCounter splits can only occur 3 times (4 total hands). Counter.
     * @param hands a dataset storing each player hand.
     * @param balance a representation of the players current balance.
     */
    public static void userPlayPhase(PlayerHand player, DealerHand dealer, Deck deck, int splitCounter, ArrayList<PlayerHand> hands, Balance balance) {
        Scanner input = new Scanner(System.in);
        boolean canSplit = false; //Flag for valid split
        String decision; //Represents the players action
        
        PlayerHand playerSecond; //Used for the next split in a recursive instance.
        
        hands.add(player); //Adds the current hand to the ArrayList of hands
        
        if (Play.checkForDealerBlackjack(player, dealer)) {
            Output.displayConcealedDeal(player, dealer, balance);
            Output.displayDealerBlackjack(player, dealer, balance);
            balance.loss();
            return;
        } else if (Play.checkForPlayerBlackjack(player, dealer)) {
            Output.displayConcealedDeal(player, dealer, balance);
            Output.displayPlayerBlackjack(player, dealer, balance);
            balance.blackjackPayout();
            return;
        }
        
        while (true) { //Will loop until valid input. Represents first player decision (double and split can only occur on first decision.)
            if (player.getHand().get(0).getDisplayValue().equals(player.getHand().get(1).getDisplayValue()) && splitCounter <= 4) //If both player cards have same rank; can only split 3 times (4 hands)
                canSplit = true;
            
            Output.displayConcealedDeal(player, dealer, balance);
            Output.displayFirstTurnPlayerOptions(canSplit);
            
            decision = input.nextLine();
            
            if (decision.equalsIgnoreCase("hit")) {
                player.addToHand(deck.drawCard());
                break;
            } else if (decision.equalsIgnoreCase("stand")) {
                break;
            } else if (decision.equalsIgnoreCase("double")) {
                player.addToHand(deck.drawCard());
                balance.gamble(balance.getWager());
                break;
            } else if (decision.equalsIgnoreCase("split") && canSplit) {
                playerSecond = new PlayerHand("Hand " + splitCounter);
                performSplit(player, playerSecond, dealer, deck, splitCounter, hands, balance);
                canSplit = false;
            } else {
                Output.invalidInput();
            }
        }
        
        //Represents additional decisions after first.
        while (decision.equalsIgnoreCase("hit") || decision.equalsIgnoreCase("split")) {
            Output.displayConcealedDeal(player, dealer, balance);
            //Check for player bust
            if (player.visibleValue() == -1) { //-1 represents a bust
                balance.loss();
                return;
            }
            
            Output.displayAdditionalTurnPlayerOptions();
            decision = input.nextLine();
            //Invalid input case
            while (!(decision.equalsIgnoreCase("stand") || decision.equalsIgnoreCase("hit"))) {
                Output.invalidInput();
                Output.displayAdditionalTurnPlayerOptions();
                decision = input.nextLine();
            }
                
            if (decision.equalsIgnoreCase("stand"))
                break; //Ends adding cards because no more cards are added after a double or a stand
            else if (decision.equalsIgnoreCase("hit")) {
                player.addToHand(deck.drawCard());
            }
        }
    }
    
    /**
     * Represents the dealer phase to draw additional cards if < a value of 17
     * @param player represents the player hand being played on currently.
     * @param dealer represents the dealer hand to allow for recursive method call.
     * @param deck represents the game deck of predetermined number of cards.
     * @param balance a representation of the players current balance.
     * @param dealSpeed represents the artifical sleep timer to delay dealing cards.
     */
    public static void dealerPlayPhase(PlayerHand player, DealerHand dealer, Deck deck, Balance balance, int dealSpeed) {
        Output.displayVisibleDeal(player, dealer, balance);
        
        while (dealer.visibleValue() < 17 && dealer.visibleValue() > 0) { //-1 Means bust
            try { Thread.sleep(dealSpeed); } catch (InterruptedException e) { }
            
            dealer.addToHand(deck.drawCard());
            Output.displayVisibleDeal(player, dealer, balance);
        }
        
        if (dealer.visibleValue() > player.visibleValue()) {
            Output.displayDealerWin();
            balance.loss();
        } else if (dealer.visibleValue() < player.visibleValue()) {
            Output.displayPlayerWin(balance);
            balance.payout();
        } else {
            Output.displayPush(balance);
            balance.push();
        }
    }
    
    /**
     * Contains logic where the player can decide if they want continue playing.
     * @param balance a representation of the players current balance.
     * @return true if game continued, false otherwise.
     */
    public static boolean continueGame(Balance balance) {
        Scanner input = new Scanner(System.in);
        Output.displayContinueGame();
        
        String continueGame = input.next();
        //'Y' indicates continue, 'N' indicates stop game, 'W' indicates change wager.
        while (!(continueGame.equalsIgnoreCase("Y") || continueGame.equalsIgnoreCase("N") || continueGame.equalsIgnoreCase("W"))) {
            Output.invalidInput();
            continueGame = input.next();
        }
        
        if (continueGame.equalsIgnoreCase("Y"))
            return true;
        else if (continueGame.equalsIgnoreCase("W")) {
            balance.setWager(wager());
            return true;
        }
        return false;
    }
    
    /**
     * Handles the input for setting the base wager for each hand.
     * @return the amount wagered.
     */
    public static double wager() {
        Scanner input = new Scanner(System.in);
        Output.displayWagerPrompt();
        return input.nextDouble();
    }
}