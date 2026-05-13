package game;

public class Move {
    public enum Action { BID, CHALLENGE }

    public final Action action;
    public final int quantity;
    public final int face;

    private Move(Action action, int quantity, int face) {
        this.action = action;
        this.quantity = quantity;
        this.face = face;
    }

    public static Move bid(int quantity, int face) {
        return new Move(Action.BID, quantity, face);
    }

    public static Move challenge() {
        return new Move(Action.CHALLENGE, 0, 0);
    }

    @Override
    public String toString() {
        if (action == Action.CHALLENGE) {
            return "{\"action\":\"challenge\"}";
        }
        return "{\"action\":\"bid\",\"quantity\":" + quantity + ",\"face\":" + face + "}";
    }
}
