package strategies;

import game.GameState;
import game.Move;

public class ChallengeStrat extends Strategy {

    public ChallengeStrat(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return state.history.size() > 4;
        // TODO see if they raised my bullshit
    }

    @Override
    public Move nextMove() {
        return Move.challenge();
    }
}
