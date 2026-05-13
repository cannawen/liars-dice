package strategies;

import game.GameConstants;
import game.GameState;
import game.Move;
import java.util.Arrays;

public class NoHistoryStrat extends Strategy {

    public NoHistoryStrat(GameState state) {
        super(state);
    }

    @Override
    public Boolean triggered() {
        return lastMove().isEmpty();
    }

    @Override
    public Move nextMove() {
        for (int face = GameConstants.MAX_FACE; face >= GameConstants.MIN_FACE; face--) {
            int f = face;
            boolean haveIt = Arrays.stream(state.myDice).anyMatch(d -> d == f);
            if (!haveIt) {
                return Move.bid(1, face);
            }
        }
        return Move.bid(1, 2);
    }
}
