import org.json.JSONObject;
import game.Game;
import game.GameState;

public class Main {
    static public void main(String[] Argv) {
        String input = new java.util.Scanner(System.in).useDelimiter("\\A").next();
        JSONObject obj = new JSONObject(input);

        String pingValue = obj.optString("ping");
        if (pingValue != null && pingValue.length() > 0) {
            System.out.println("{\"pong\":\"" + pingValue + "\"}");
        } else {
            System.out.println(run(obj));
        }
    }

    static String run(JSONObject obj) {
        GameState state = new GameState(obj);
        return String.valueOf(Game.play(state));
    }
}

