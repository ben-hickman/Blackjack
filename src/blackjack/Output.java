package blackjack;

/**
 * A class containing all of the print to console logic.
 * The screen will be cleared when ran in a shell terminal.
 * @author Ben Hickman
 * @date 2022-04-29
 * @version 1.0.0
 */
public class Output {
    //Logo to be displayed at top of console.
    public static void displayTitleScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("....................................................................................................\n" +
                            ": ................................................................................................ :\n" +
                            ": :                                                                                              : :\n" +
                            ": :   /$$$$$$$  /$$        /$$$$$$   /$$$$$$  /$$   /$$       /$$  /$$$$$$   /$$$$$$  /$$   /$$  : :\n" +
                            ": :  | $$__ /$$| $$       /$$__ /$$ /$$__ /$$| $$  /$$       | $$ /$$__ /$$ /$$__ /$$| $$  /$$   : :\n" +
                            ": :  | $$  | $$| $$      | $$  | $$| $$  \\/_/| $$ /$$        | $$| $$  | $$| $$  \\/_/| $$ /$$    : :\n" +
                            ": :  | $$$$$$$/| $$      | $$$$$$$$| $$      | $$$$$         | $$| $$$$$$$$| $$      | $$$$$     : :\n" +
                            ": :  | $$__ /$$| $$      | $$__ /$$| $$      | $$_ $$   /$$  | $$| $$__ /$$| $$      | $$_ $$    : :\n" +
                            ": :  | $$  | $$| $$      | $$  | $$| $$   /$$| $$ \\ $$ | $$  | $$| $$  | $$| $$   /$$| $$ \\ $$   : :\n" +
                            ": :  | $$$$$$$/| $$$$$$$$| $$  | $$|/ $$$$$$/| $$  \\ $$|/ $$$$$$/| $$  | $$|/ $$$$$$/| $$  \\ $$  : :\n" +
                            ": :  |/______/ |/_______/|/_/  |/_/ \\/_____/ |/_/   \\// \\/_____/ |/_/  |/_/ \\/_____/ |/_/   \\//  : :\n" +
                            ": :                Developed by: Ben Hickman - Date: 2022/04/25 - Version: 1.0.0                 : :\n" +
                            ": :..............................................................................................: :\n" +
                            ":..................................................................................................:");
    }
    
    /**
     * Output for when the dealer scores a blackjack.
     * @param player represents the first player hand.
     * @param dealer represents the dealers hand.
     * @param balance represents the internally specified starting balance.
     */
    public static void displayDealerBlackjack(PlayerHand player, DealerHand dealer, Balance balance) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        displayTitleScreen();
        displayBalance(balance);
        System.out.println(dealer + dealer.viewVisibleValue());
        System.out.println(player + player.viewVisibleValue());
        System.out.println("Dealer Blackjack. Dealer Wins.");
    }
    
    /**
     * Output for when the player scores a blackjack.
     * @param player represents the first player hand.
     * @param dealer represents the dealers hand.
     * @param balance represents the internally specified starting balance.
     */
    public static void displayPlayerBlackjack(PlayerHand player, DealerHand dealer, Balance balance) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        System.out.println("Blackjack! You win $" + balance.getGambledBalance() * 2.5 + "!");
    }
    
    //Generic message for illegal input.
    public static void invalidInput() {
        System.out.println("Unknown input.");
    }
    
    /**
     * Displays commands a player can run during first decision.
     * @param canSplit determines if splitting is legal.
     */
    public static void displayFirstTurnPlayerOptions(boolean canSplit) {
        if (canSplit)
            System.out.print("[Hit] [Double] [Split] [Stand]: ");
        else
            System.out.print("[Hit] [Double] [Stand]: ");
    }
    
    //Displays commands a player can run during additional decisions (past first.)
    public static void displayAdditionalTurnPlayerOptions() {
        System.out.print("[Hit] [Stand]: ");
    }
    
    /**
     * Print a view where the first dealer card is concealed.
     * @param player represents the first player hand.
     * @param dealer represents the dealers hand.
     * @param balance represents the internally specified starting balance.
     */
    public static void displayConcealedDeal(PlayerHand player, DealerHand dealer, Balance balance) {
        displayTitleScreen();
        displayBalance(balance);
        
        System.out.print("\n" + dealer.viewConcealedHand());
        System.out.print(dealer.concealedValue());
        if (dealer.concealedValue() == 1) //If dealer soft hand
            System.out.print("/11");
        System.out.println();
        //Display player's hand and value
        System.out.print(player);
        System.out.println(player.viewVisibleValue());
    }
    
    /**
     * Print a view where the first dealer card is visible.
     * @param player represents the first player hand.
     * @param dealer represents the dealers hand.
     * @param balance represents the internally specified starting balance.
     */
    public static void displayVisibleDeal(PlayerHand player, DealerHand dealer, Balance balance) {
        displayTitleScreen();
        displayBalance(balance);
        
        System.out.print("\n" + dealer); //This should be a println
        System.out.print(dealer.viewVisibleValue() + "\n");
        //Display player's hand and value
        System.out.print(player);
        System.out.println(player.viewVisibleValue());
    }
    
    //Generic message when dealer wins.
    public static void displayDealerWin() {
        System.out.println("Dealer wins.");
    }
    //Generic message when player wins.
    public static void displayPlayerWin(Balance balance) {
        System.out.println("You win $" + balance.getGambledBalance() * 2);
    }
    //Generic message on a draw.
    public static void displayPush(Balance balance) {
        System.out.println("Push. The hand ends in a draw. $" + balance.getGambledBalance() + " returned.");
    }
    //Generic message after the playing phase has finished prompting continuation.
    public static void displayContinueGame() {
        System.out.print("New Hand? Or, New Wager? [Y/N/W]: ");
    }
    
    /**
     * Displays current balance and the amount wagered.
     * @param balance to be displayed.
     */
    public static void displayBalance(Balance balance) {
        System.out.println("Balance: $" + balance.getBalance() + " Wager: [$" + balance.getGambledBalance() + "]");
    }
    //Generic initial wager display at start of the game.
    public static void displayWagerPrompt() {
        System.out.print("Please enter a ($) amount to wager: $");
    }
}