package milkycode.reversi.io;

public enum MenuEntry {
    EXIT("Exit"),
    GAME_EASY("Single player easy mode"),
    GAME_HARD("Single player hard mode"),
    GAME_MULTIPLAYER("Two player mode"),
    BEST_SCORE("Show best scores");

    private final String description;

    MenuEntry(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
