package blackjack;

import java.util.LinkedList;

/**
 * A class representing the player's hand.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class PlayerHand {
    private LinkedList<Card> hand;
    private String id; //Used to distinguish player's hands when hands are split.
    private boolean busted; //Flag true when hand > than 21.
    private boolean blackJack; //Flag true when hand = 21 on deal.
    
    public PlayerHand(String id) {
        this.id = id;
        busted = false;
        blackJack = false;
        hand = new LinkedList<>();
    }
    
    /**
     * Adds a new card to the player's hand.
     * Update to busted if 21 is exceeded.
     * @param card is the card to be added.
     */
    public void addToHand(Card card) {
        hand.add(card);
        if (visibleValue() == -1)
            busted = true;
        
        if (hand.size() == 2 && visibleValue() == 21)
            blackJack = true;
    }
    
    /**
     * Returns the player's cards to the deck and clears their hand.
     * @param deck to return the cards to.
     */
    public void clearHand(Deck deck) {
        deck.returnToDeck(hand);
        hand.clear();
        busted = false;
        blackJack = false;
    }
    
    /**
     * @return the console view of the player's hand while visible.
     */
    @Override
    public String toString() {        
        String handString = id + ":\n";
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
            if (hand.get(i).getDisplayValue().equals("10")) {
                handString += "| " + hand.get(i).getDisplayValue() + "    |";
            } else {
                handString += "| " + hand.get(i).getDisplayValue() + "     |";
            }
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|       |";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|   " + hand.get(i).getDisplaySuit() + "   |";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            handString += "|       |";
        }
        handString += "\n";
        
        for (int i = 0; i < handLength; i++) {
            if (hand.get(i).getDisplayValue().equals("10")) {
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
     * Used internally to calculate the values of a player's hand. Handles
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
            //return (short)(value + 10); //This seems less efficient than the above code
        }
        
        return value;
    }
    
    /**
     * Visual representation of the correct value of a player's hand.
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
            return value + " [BUST]"; //Maybe change to display number anyway. Somewhat redundant check for > 21 because that happens in Play.java
        } else if (softHand && value <= 11) {
            return value + "/" + (value + 10);
        }
        
        return value + "";
    }
    
    /**
     * Returns the second card in a hand for splitting purposes.
     * @return the split card.
     */
    public Card split() {
        return hand.remove(1);
    }
    
    public LinkedList<Card> getHand() {
        return hand;
    }
    
    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }
    
    public boolean getBusted() {
        return busted;
    }
    
    public void setBusted(boolean busted) {
        this.busted = busted;
    }
    
    public boolean getBlackJack() {
        return blackJack;
    }
    
    public void setBlackJack(boolean blackJack) {
        this.blackJack = blackJack;
    }
}