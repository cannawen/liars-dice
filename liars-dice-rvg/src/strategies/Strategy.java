package strategies;

import game.GameConstants;
import game.GameState;
import game.Move;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Strategy {
    public GameState state;

    public Strategy(GameState state) {
        this.state = state;
    }

    public Map<Integer, Integer> diceFrequencies() {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(1, 1);
        for (int face = 2; face <= GameConstants.MAX_FACE; face++) {
            freq.put(face, 2);
        }
        int wilds = 0;
        for (int die : state.myDice) {
            if (die == 1) {
                wilds++;
                freq.merge(1, 1, Integer::sum);
            } else {
                freq.merge(die, 1, Integer::sum);
            }
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getKey() != 1) {
                entry.setValue(entry.getValue() + wilds);
            }
        }
        return freq;
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
