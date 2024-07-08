package com.intuit.blackjack.adapter.in.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.intuit.blackjack.domain.Game;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlackjackController {

    //what should be a class field here??
    // Game!!
    private final Game game;

    // Spring will pass in the game object
    // for you here because we
    // defined it as a Bean in BlackjackGameApplication.java
    @Autowired
    public BlackjackController(Game game) {
       this.game = game;
    }

    @PostMapping("/start-game")
    public String startGame() {
        game.initialDeal();
        return redirectBasedOnStateOfGame();
        //what is the String that we return here?
        // it's the URL that we want to go to

        // after calling initial deal what do we need to do??
        //could do a redirect if the player is done

    }


    @PostMapping("/hit")
    public String hit() {
        game.playerHits();  //with Hex we can reuse the same domain object
        //across different adapters - because our Console adapter
        // can call the domain object the same way
        return redirectBasedOnStateOfGame();
    }


    @GetMapping("/done")
    public String doneView(Model model) {
        populateWithGameView(model);
        model.addAttribute("outcome", game.determineOutcome().displayString());
        return "done";
    }

    @PostMapping("/stand")
    public String standCommand() {
        game.playerStands();
        return redirectBasedOnStateOfGame();
    }

    @GetMapping("/game")
    public String gameView(Model model) {

        //this updates the model which then gets consumed by the view
        // which is Thymeleaf templates in this case
        populateWithGameView(model);
        return "blackjack";

    }

    private void populateWithGameView(Model model) {
        model.addAttribute("gameView", GameView.of(game));
    }

//    @GetMapping("/done")
//    public String doneView(Model model) {
//        populateWIthGameView(model)
//        model.addAttribute()
//        // after calling initial deal what do we need to do??
//        //could do a redirect if the player is done
//
//    }

    private String redirectBasedOnStateOfGame(){
        if(game.isPlayerDone()) {
            return "redirect:/done";
        }
        // if the player is still playing
        // we want to be on a page called "game"
        return "redirect:/game";
    }





    // you will need to update the Model and then reference that model
    // from the Thymeleaf templates...

    //how would we update the view
    // we would return an html page?
    // how is the view getting the updated data ie the update player cards/dealer
    // cards...


}
