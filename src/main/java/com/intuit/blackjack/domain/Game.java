package com.intuit.blackjack.domain;

import com.intuit.blackjack.domain.port.GameMonitor;

public class Game {

    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    private boolean playerDone;

    public Game(Deck deck) {
        this.deck = deck;
    }

    private GameMonitor gameMonitor;

    public Game(Deck deck, GameMonitor gameMonitor) {
        this.deck = deck;
        this.gameMonitor = gameMonitor;
        // assign Deck & GameMonitor to private final fields
    }


    public void reset(){
        playerHand.clear();
        dealerHand.clear();
        playerDone = false;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
        if (playerHand.hasBlackjack()) {
            playerDone = true;
        }
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public GameOutcome determineOutcome() {
        if (playerHand.isBusted()) {
            return GameOutcome.PLAYER_BUSTED;
        } else if (playerHand.hasBlackjack()) {
            return GameOutcome.PLAYER_WINS_BLACKJACK;
        } else if (dealerHand.isBusted()) {
            return GameOutcome.DEALER_BUSTED;
        } else if (playerHand.beats(dealerHand)) {
            return GameOutcome.PLAYER_BEATS_DEALER;
        } else if (playerHand.pushes(dealerHand)) {
            return GameOutcome.PLAYER_PUSHES;
        } else {
            return GameOutcome.PLAYER_LOSES;
        }
    }

    public void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
        else
            gameMonitor.roundCompleted(this);


    }

    // QUERY METHOD RULE
    // ? -> Snapshot (point-in-time)
    // ? -> PREVENT illegal change to state of Game
    // Need: Hand's value, Hand's list of cards, and Hand's "face up" card
    // 0. Hand - NO - it's mutable, and is not a snapshot
    // 1. Copy/Clone - maybe - Is a snapshot, Prevent Illegal Access, but is Misleading
    // 2. Limited Interface that Hand implements: only expose needed info "ReadOnlyHand"
    //                  Solves illegal change, but is not a snapshot
    // 3. DTO ("HandView") - data transfer object - NO - not allowed in Domain
    // 3a. VALUE OBJECT ("HandView") - belongs in the domain
    //              Created by Game (used only thru Game) or Hand (used elsewhere)
    public Hand playerHand() {
        return playerHand;
    }

    public Hand dealerHand() {
        return dealerHand;
    }


    public void playerHits() {
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
        if(playerDone)
            gameMonitor.roundCompleted(this);

    }

    public void playerStands() {
        playerDone = true;
        dealerTurn();
        //at this point we KNOW that one round of blackjack has finished
        gameMonitor.roundCompleted(this);
    }

    public boolean isPlayerDone() {
        return playerDone;
    }


}
