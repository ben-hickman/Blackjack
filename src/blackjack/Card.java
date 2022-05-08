package blackjack;

/**
 * A class representing a playing card.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class Card {
    private short value; //Numeric value of the card
    private String displayValue; //Visual value of the card
    private short suit; //Numeric value of suit, used only to initialize displaySuit
    private String displaySuit; //Visual value of the suit
    
    /**
     * @param value is used to specify display. Face cards are initialized and
     * their values are set to 10.
     * @param suit of the card.
     */
    public Card(short value, short suit) {
        switch(value) {
            case 1:
                displayValue = "A";
                break;
            case 11:
                displayValue = "J";
                value = 10;
                break;
            case 12:
                displayValue = "Q";
                value = 10;
                break;
            case 13:
                displayValue = "K";
                value = 10;
                break;
            default:
                displayValue = String.valueOf(value);
        }
        
        switch (suit) {
            case 0:
                displaySuit = "S";
                break;
            case 1:
                displaySuit = "H";
                break;
            case 2:
                displaySuit = "C";
                break;
            case 3:
                displaySuit = "D";
                break;
            default:
                displaySuit = "ERROR";
        }
        
        this.value = value;
        this.suit = suit;
    }
    
    public short getValue() {
        return value;
    }
    
    public void setValue(short value) {
        this.value = value;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
    
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplaySuit() {
        return displaySuit;
    }
    
    public void setDisplaySuit(String displaySuit) {
        this.displaySuit = displaySuit;
    }
}