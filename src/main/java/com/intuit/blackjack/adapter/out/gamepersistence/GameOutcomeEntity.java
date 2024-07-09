package com.intuit.blackjack.adapter.out.gamepersistence;



//this class will be the table that we want JPA to use
//so when a round of Blackjack is completed we will want to write
// to this Entity...

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class GameOutcomeEntity {

    //remember these are the columns in our table
    // and an object of this class represents a single row in the table!
    private  String playerName;
    private String outcome;
    private  String playerHandValue;
    private String dealerHandValue;

    @Id
    @GeneratedValue
    private Long id;

    public GameOutcomeEntity(String playerName, String outcome, String playerHandValue, String dealerHandValue) {
        this.playerName = playerName;
        this.outcome = outcome;
        this.playerHandValue = playerHandValue;
        this.dealerHandValue = dealerHandValue;
    }

    public GameOutcomeEntity() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
