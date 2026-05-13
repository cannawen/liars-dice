package game;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    public final int myId;
    public final int[] myDice;
    public final List<HistoryEntry> history;

    public GameState(JSONObject obj) {
        myId = obj.getInt("my-id");

        JSONArray diceArray = obj.getJSONArray("my-dice");
        myDice = new int[diceArray.length()];
        for (int i = 0; i < diceArray.length(); i++) {
            myDice[i] = diceArray.getInt(i);
        }

        history = new ArrayList<>();
        JSONArray historyArray = obj.getJSONArray("history");
        for (int i = 0; i < historyArray.length(); i++) {
            history.add(new HistoryEntry(historyArray.getJSONObject(i)));
        }
    }

    public static class HistoryEntry {
        public final int playerId;
        public final Move move;

        public HistoryEntry(JSONObject obj) {
            playerId = obj.getInt("player-id");
            JSONObject moveObj = obj.getJSONObject("move");
            if ("challenge".equals(moveObj.getString("action"))) {
                move = Move.challenge();
            } else {
                move = Move.bid(moveObj.getInt("quantity"), moveObj.getInt("face"));
            }
        }
    }
}
