package strategies;

import game.GameConstants;
import game.GameState;
import game.Move;

public class IncreaseFace extends Strategy {

    public IncreaseFace(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return lastMove().isPresent() && lastMove().get().face < GameConstants.MAX_FACE;
    }

    @Override
    public Move nextMove() {
        Move move = lastMove().get();
        return Move.bid(move.quantity, move.face + 1);
    }
}
