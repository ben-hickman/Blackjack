package blackjack;

/**
 * A class representing the in game balance of a player. Can currently go negative.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class Balance {
    
    private double balance;
    private double gambledBalance;
    private double wager;
    
    /**
     * Player balance initialized to $1000.00
     */
    public Balance() {
        balance = 1000.00;
        gambledBalance = 0.00;
    }
    
    /**
     * Temporarily stores the wager by removing it from players balance.
     * @param gambleAmount is the amount of the wager.
     */
    public void gamble(double gambleAmount) {
        balance -= gambleAmount;
        gambledBalance += gambleAmount;
    }
    
    /**
     * Sets the amount wagered to 0 indicating a loss.
     */
    public void loss() {
        gambledBalance = 0.00;
    }
    
    /**
     * Updates the players balance indicating a win.
     */
    public void payout() {
        balance += gambledBalance * 2;
        gambledBalance = 0.00;
    }
    
    /**
     * Updates the players balance indicating a win from a blackjack.
     * Pays out 2.5x instead of 2.0x.
     */
    public void blackjackPayout() {
        balance += gambledBalance * 2.5;
        gambledBalance = 0.00;
    }
    
    /**
     * Returns the player their balance on a draw.
     */
    public void push() {
        balance += gambledBalance;
        gambledBalance = 0.00;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public double getGambledBalance() {
        return gambledBalance;
    }
    
    public void setGambledBalance(double gambledBalance) {
        this.gambledBalance = gambledBalance;
    }
    
    public double getWager() {
        return wager;
    }
    
    public void setWager(double wager) {
        this.wager = wager;
    }
}