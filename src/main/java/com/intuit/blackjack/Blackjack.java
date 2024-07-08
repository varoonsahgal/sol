package com.intuit.blackjack;

import com.intuit.blackjack.adapter.in.console.ConsoleGame;
import com.intuit.blackjack.domain.Deck;
import com.intuit.blackjack.domain.Game;

public class Blackjack {

    // Assembling & Configuring objects (bootstrap or initialize)
    // Transient
    public static void main(String[] args) {
        Game game = new Game(new Deck()); // Entity-like object
        ConsoleGame consoleGame = new ConsoleGame(game); // in general: Entities aren't directly passed in to Adapters
        consoleGame.start();
    }

}
