package com.intuit.blackjack.domain.port;

import com.intuit.blackjack.domain.Game;


//concrete implementation of this
// will live in an adapter package
// because we do NOT currently what we want to do when a round of blackjack
// is completed - and ultimately we want to send the data over to a database using JPA
// but when we are INSIDE the domain we do not want to know anything about the outside world
//
public interface GameMonitor {
    void roundCompleted(Game game);
}