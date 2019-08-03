package io.hexlet.xo.model;

public class Game {

    private final Player player1;

    private final Player player2;

    private final Field field;

    private final String name;

    Game(final GameBuilder builder) {
        this.player1 = builder.getPlayer1();
        this.player2 = builder.getPlayer2();
        this.field = builder.getField();
        this.name = builder.getName();
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    public Field getField() {
        return field;
    }

    public String getName() {
        return name;
    }

}
