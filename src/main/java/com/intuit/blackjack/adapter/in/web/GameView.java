package com.intuit.blackjack.adapter.in.web;
import com.intuit.blackjack.domain.Game;
import com.intuit.blackjack.domain.Card;
import com.intuit.blackjack.domain.Hand;



import java.util.ArrayList;
import java.util.List;


//WHat's the point of this class
// To grab relevant info from the Game pure domain class
// and store it in a view that we can easily consume..
public class GameView {

    private List<String> dealerCards;
    private List<String> playerCards;

    //this gives me a view of the domain class
    // which i can then pass to my Model!!
    public static GameView of(Game game){
        GameView gameView = new GameView();
        gameView.dealerCards = new ArrayList<>();
        stringListOf(game.dealerHand(), gameView.dealerCards);
        gameView.playerCards = new ArrayList<>();
        stringListOf(game.playerHand(), gameView.playerCards);
        return gameView;
    }

    private static void stringListOf(Hand hand, List<String> playerCards) {
        for (Card card : hand.cards()) {
            playerCards.add(displayOf(card));
        }
    }

    private static String displayOf(Card card) {
        return card.rank().display() + card.suit().symbol();
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<String> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<String> playerCards) {
        this.playerCards = playerCards;
    }
}










