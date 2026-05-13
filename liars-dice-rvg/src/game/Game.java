package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
    
    public static Move play(GameState state) {
        return Move.challenge();
    }

    public static List<Move> escelate(GameState state) {
        Move move = state.history.get(state.history.size() - 1).move;

        List<Move> escalations = new ArrayList<>();

        for (int face = move.face + 1; face <= GameConstants.MAX_FACE; face++) {
            escalations.add(Move.bid(move.quantity, face));
        }
        for (int face = GameConstants.MIN_FACE; face <= GameConstants.MAX_FACE; face++) {
            escalations.add(Move.bid(move.quantity + 1, face));
        }

        return escalations;
    }
}
