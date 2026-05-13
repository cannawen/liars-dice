package strategies;

import game.GameState;
import game.Move;
import java.util.Optional;

public abstract class Strategy {
    public GameState state;

    public Strategy(GameState state) {
        this.state = state;
    }

    public Optional<Move> lastMove() {
        if (state.history.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(state.history.get(state.history.size() - 1).move);
        }
    }

    public abstract Boolean triggered();

    public abstract Move nextMove();
}
