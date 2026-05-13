package game;

import java.util.Arrays;
import java.util.List;

import strategies.ChallengeStrat;
import strategies.IncreaseFace;
import strategies.IncreaseQuantity;
import strategies.NoHistoryStrat;
import strategies.Strategy;

public class Game {

    static GameState state;

    public static void init(GameState _state) {
        Game.state = _state;
    }

    public static Move play() {
        List<Strategy> strategies = Arrays.asList(
            new NoHistoryStrat(state),
            new ChallengeStrat(state),
            new IncreaseFace(state),
            new IncreaseQuantity(state)
        );

        return strategies.stream()
            .filter(Strategy::triggered)
            .findFirst()
            .map(Strategy::nextMove)
            .orElseThrow();
    }

}
