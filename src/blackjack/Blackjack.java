package blackjack;

import java.util.ArrayList;

/**
 * A command line based Blackjack application.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class Blackjack {
    
    public static void main(String[] args) {
        /* Sets the number of 52 card decks to be played. More decks creates a
        greater house advantage. */
        short numOfDecks = 4;
        
        Deck deck = new Deck(numOfDecks);
        Balance balance = new Balance();
        DealerHand dealer = new DealerHand();
        PlayerHand player = new PlayerHand("Hand 1");
        
        //Amount to be gambled each hand.
        double wager;
        //Controls the sleep duration for cards being displayed to console.
        int dealSpeed = 500;
        //Players can have multiple hands by spliting. Stores when applicable.
        ArrayList<PlayerHand> playerHands = new ArrayList<>();
        
        //Display Title
        Output.displayTitleScreen();
        Output.displayBalance(balance);
        
        //Amount to be wagered each hand
        balance.setWager(Play.wager());
        
        //Logic for controlling the duration of 1 complete hand. Loops if player wishes to continue game.
        do {
            //Draw 2 cards for dealer and for player
            Play.dealCards(player, dealer, deck, balance, dealSpeed);

            //Initiates the player decisiion phase. Recursively called when hand split.
            Play.userPlayPhase(player, dealer, deck, 0, playerHands, balance);
            
            //Initiates the dealer phase if there hasn't been a blackjack, or if player hasn't busted for each hand.
            for (PlayerHand hand : playerHands)
                if (!hand.getBusted() && !hand.getBlackJack()) {
                    Play.dealerPlayPhase(player, dealer, deck, balance, dealSpeed); //This won't deal the dealer more cards if he's already at 17+, but will still display win/loss condition
                }
            //Removes cards from each hand and returns them to the deck.
            for (PlayerHand hand : playerHands)
                hand.clearHand(deck);
            
            dealer.clearHand(deck);
            playerHands.clear();
        } while (Play.continueGame(balance));
    }
}