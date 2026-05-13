package strategies;

import game.GameState;
import game.Move;

public class IncreaseQuantity extends Strategy {

    public IncreaseQuantity(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return lastMove().isPresent();
    }

    @Override
    public Move nextMove() {
        Move move = lastMove().get();
        return Move.bid(move.quantity + 1, move.face);
    }
}
