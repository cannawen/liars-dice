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
        Map<Integer, Integer> freq = defaultDiceFrequencies();
        myDiceFrequencies().forEach((face, count) -> freq.merge(face, count, Integer::sum));
        return freq;
    }
    
    // public Map<Integer, Integer> theirDiceFrequencies() {
    //     Map<Integer, Integer> freq = new HashMap<>();
    //     for (GameState.HistoryEntry entry : state.history) {
    //         if (entry.playerId != state.myId && entry.move.action == Move.Action.BID) {
    //             freq.merge(entry.move.face, entry.move.quantity, Integer::max);
    //         }
    //     }
    //     return freq;
    // }

    public Map<Integer, Integer> defaultDiceFrequencies() {
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(1, 1);
        for (int face = 2; face <= GameConstants.MAX_FACE; face++) {
            freq.put(face, 2);
        }
        return freq;
    }
    
    public Map<Integer, Integer> myDiceFrequencies() {
        Map<Integer, Integer> freq = new HashMap<>();
        int wilds = 0;
        for (int die : state.myDice) {
            if (die == 1) {
                wilds++;
                freq.merge(1, 1, Integer::sum);
            } else {
                freq.merge(die, 1, Integer::sum);
            }
        }
        for (int face = GameConstants.MIN_FACE + 1; face <= GameConstants.MAX_FACE; face++) {
            freq.merge(face, wilds, Integer::sum);
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

    public Optional<Move> myLastMove() {
        for (int i = state.history.size() - 1; i >= 0; i--) {
            GameState.HistoryEntry entry = state.history.get(i);
            if (entry.playerId == state.myId) {
                return Optional.of(entry.move);
            }
        }
        return Optional.empty();
    }

    public abstract Boolean triggered();

    public abstract Move nextMove();
}
