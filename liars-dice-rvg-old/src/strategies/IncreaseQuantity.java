package strategies;

import game.GameState;
import game.Move;

public class IncreaseQuantity extends Strategy {

    public IncreaseQuantity(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return myLastMove().isPresent();
    }

    @Override
    public Move nextMove() {
        Move move = myLastMove().get();
        Move lastMove = lastMove().get();
        return Move.bid(lastMove.quantity + 1, move.face);
    }
}
