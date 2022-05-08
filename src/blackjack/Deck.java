package blackjack;

import java.util.LinkedList;
import java.util.Collections;

/**
 * A class representing a deck compromised of several cards.
 * Number of decks combined into 1 LinkedList, and specified in main.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class Deck {
    private LinkedList<Card> deck;
    
    /**
     * Initialize the deck with cards.
     * @param numberOfDecks to include in overall deck.
     */
    public Deck(short numberOfDecks) {
        deck = new LinkedList<>();
        
        for (short i = 0; i < numberOfDecks; i++)
            for (short j = 0; j < 4; j++)
                for (short k = 1; k <= 13; k++) {
                    Card card = new Card(k, j);
                    deck.add(card);
                }
        
        shuffle();
    }
    
    /**
     * @return a card removed from the deck.
     */
    public Card drawCard() {
        return deck.remove();
    }
    
    /**
     * Shuffles the order of the deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    /**
     * Takes a player's or dealer's cards and returns it to the deck.
     * @param hand to be returned.
     */
    public void returnToDeck(LinkedList<Card> hand) {
        deck.addAll(hand);
        shuffle();
    }
}