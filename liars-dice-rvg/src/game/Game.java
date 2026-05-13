package game;

import java.util.Arrays;
import java.util.List;

import strategies.ChallengeStrat;
import strategies.ChallengeUnlikelyStrat;
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
            new ChallengeUnlikelyStrat(state),
            new IncreaseQuantity(state),
            new IncreaseFace(state),
            new ChallengeStrat(state)
        );

        return strategies.stream()
            .filter(Strategy::triggered)
            .findFirst()
            .map(Strategy::nextMove)
            .orElseThrow();
    }

}
