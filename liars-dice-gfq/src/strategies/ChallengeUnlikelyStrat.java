package strategies;

import game.GameState;
import game.Move;

public class ChallengeUnlikelyStrat extends Strategy {
    public ChallengeUnlikelyStrat(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        Move move = lastMove().get();
        return move.quantity > diceFrequencies().get(move.face);
    }

    @Override
    public Move nextMove() {
        return Move.challenge();
    }
}
