package strategies;

import game.GameConstants;
import game.GameState;
import game.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class Strategy {
    public GameState state;

    public Strategy(GameState state) {
        this.state = state;
    }

    public Map<Integer, Integer> diceFrequencies() {
        return merge(myDiceFrequencies(), defaultDiceFrequencies());
    }

    public Map<Integer, Integer> merge(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        Map<Integer, Integer> result = new HashMap<>(a);
        b.forEach((key, value) -> result.merge(key, value, Integer::sum));
        return result;
    }
    
    // public Map<Integer, Integer> theirDiceFrequencies() {
    //     Map<Integer, Integer> freq = new HashMap<>();
    //     Map<Integer, Integer> myFreq = myDiceFrequencies();
    //     int opponentMoveCount = 0;
    //     for (GameState.HistoryEntry entry : state.history) {
    //         if (entry.playerId != state.myId && entry.move.action == Move.Action.BID) {
    //             if (opponentMoveCount >= 3) break;
    //             opponentMoveCount++;
    //             int adjustedQty = entry.move.quantity - myFreq.getOrDefault(entry.move.face, 0);
    //             if (adjustedQty > 0) {
    //                 freq.merge(entry.move.face, adjustedQty, Integer::max);
    //             }
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

    public List<Move> legalMoves() {
        List<Move> moves = new ArrayList<>();
        Optional<Move> last = lastMove();

        Map<Integer, Integer> freq = diceFrequencies();
        for (int face = GameConstants.MIN_FACE; face <= GameConstants.MAX_FACE; face++) {
            int maxQty = freq.getOrDefault(face, 0);
            for (int qty = 1; qty <= maxQty; qty++) {
                if (isHigherBid(qty, face, last)) {
                    moves.add(Move.bid(qty, face));
                }
            }
        }

        return moves;
    }

    private boolean isHigherBid(int qty, int face, Optional<Move> last) {
        if (!last.isPresent() || last.get().action != Move.Action.BID) {
            return true;
        }
        Move lastBid = last.get();
        return qty > lastBid.quantity || (qty == lastBid.quantity && face > lastBid.face);
    }

    public abstract Boolean triggered();

    public abstract Move nextMove();
}
