package blackjack;

import java.util.LinkedList;

/**
 * A class representing the dealer's hand.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class DealerHand {
    private LinkedList<Card> hand;
    
    public DealerHand() {
        hand = new LinkedList<>();
    }
    
    /**
     * Adds a new card to the dealer's hand.
     * @param card is the card to be added.
     */
    public void addToHand(Card card) {
        hand.add(card);
    }
    
    /**
     * Returns the dealer's cards to the deck and clears their hand.
     * @param deck to return the cards to.
     */
    public void clearHand(Deck deck) {
        deck.returnToDeck(hand);
        hand.clear();
    }
    
    /**
     * Creates a concealed view of the dealers hand (not showing first card).
     * @return the customized card drawing for console view.
     */
    public String viewConcealedHand() {
        String handString = "";
        int handLength = hand.size();
        
        for (int i = 0; i < handLength; i++) {
            handString += " _______ ";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|       |";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == 0) {
                handString += "| # # # |";
            } else if (hand.get(i).getDisplayValue().equals("10")) {
                handString += "| " + hand.get(i).getDisplayValue() + "    |";
            } else {
                handString += "| " + hand.get(i).getDisplayValue() + "     |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == 0) {
                handString += "|# # # #|";
            } else {
                handString += "|       |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == 0) {
                handString += "| # # # |";
            } else {
                handString += "|   " + hand.get(i).getDisplaySuit() + "   |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == 0) {
                handString += "|# # # #|";
            } else {
                handString += "|       |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == 0) {
                handString += "| # # # |";
            } else if (hand.get(i).getDisplayValue().equals("10")) {
                handString += "|    " + hand.get(i).getDisplayValue() + " |";
            } else {
                handString += "|     " + hand.get(i).getDisplayValue() + " |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|_______|";
        }
        handString += "\n";
        
        return handString;
    }
    
    /**
     * @return the console view of the dealer's hand while visible.
     */
    @Override
    public String toString() {
        String handString = "";
        int handLength = hand.size();
        
        for (int i = 0; i < handLength; i++) {
            handString += " _______ ";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|       |";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == -2) {
                handString += "| # # # |";
            } else if (hand.get(i).getDisplayValue().equals("10")) {
                handString += "| " + hand.get(i).getDisplayValue() + "    |";
            } else {
                handString += "| " + hand.get(i).getDisplayValue() + "     |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == -2) {
                handString += "|# # # #|";
            } else {
                handString += "|       |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == -2) {
                handString += "| # # # |";
            } else {
                handString += "|   " + hand.get(i).getDisplaySuit() + "   |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == -2) {
                handString += "|# # # #|";
            } else {
                handString += "|       |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (i == -2) {
                handString += "| # # # |";
            } else if (hand.get(i).getDisplayValue().equals("10")) {
                handString += "|    " + hand.get(i).getDisplayValue() + " |";
            } else {
                handString += "|     " + hand.get(i).getDisplayValue() + " |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|_______|";
        }
        handString += "\n";
        
        return handString;
    }
    
    /**
     * @return the value of the second card in a dealer's hand.
     */
    public short concealedValue() {
        if (hand.size() == 1)
            return 0;
        
        return hand.peekLast().getValue();
    }
    
    /**
     * Used internally to calculate the values of a dealer's hand. Handles
     * soft/hard hands. Not to be confused with display value.
     * @return the calculated value.
     */
    public short visibleValue() {
        short value = 0;
        boolean softHand = false;
        
        for (Card card : hand) {
            if (card.getValue() == 1) {
                softHand = true;
            }
            value += card.getValue();
        }
        
        if (value > 21) {
            return -1; //Bust
        } else if (softHand && value <= 11) {
            value += 10;
        }
        
        return value;
    }
    
    /**
     * Visual representation of the correct value of a dealer's hand.
     * @return the value corrected for soft/hard hands.
     */
    public String viewVisibleValue() {
        short value = 0;
        boolean softHand = false;
        
        for (Card card : hand) {
            if (card.getValue() == 1) {
                softHand = true;
            }
            value += card.getValue();
        }
        
        if (value > 21) {
            return value + " [BUST]";
        } else if (softHand && value <= 11) {
            return value + "/" + (value + 10);
        }
        
        return value + "";
    }
}