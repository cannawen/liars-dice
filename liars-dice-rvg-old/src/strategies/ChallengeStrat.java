package strategies;

import game.GameState;
import game.Move;

public class ChallengeStrat extends Strategy {
    public ChallengeStrat(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return true;
    }

    @Override
    public Move nextMove() {
        return Move.challenge();
    }
}
