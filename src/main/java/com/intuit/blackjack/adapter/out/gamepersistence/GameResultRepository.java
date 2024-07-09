package com.intuit.blackjack.adapter.out.gamepersistence;


import org.springframework.data.jpa.repository.JpaRepository;


//what is a repository?
// it's the direct-line to the actual database
// but the beauty of SPring is such that we do NOT have to
// provide a concrete implementation of this interface
// we just have to reference it and Spring will auto-wire it for us!
// Spring will detect that we are configured to use h2
// and then it will connect to h2 and expose some CRUD operations on the database...

public interface GameResultRepository extends JpaRepository<GameOutcomeEntity, Long> {


}