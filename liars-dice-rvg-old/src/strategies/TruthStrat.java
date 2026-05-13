package strategies;

import java.util.List;

import game.GameConstants;
import game.GameState;
import game.Move;

public class TruthStrat extends Strategy {
    public TruthStrat(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return state.history.size() > GameConstants.TRUTH_ROUND && legalMoves().size() > 0;
    }

    @Override
    public Move nextMove() {
        List<Move> moves = legalMoves();
        return moves.get((int) (Math.random() * moves.size()));
    }
}

