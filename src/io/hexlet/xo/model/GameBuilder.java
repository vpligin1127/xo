package io.hexlet.xo.model;

public class GameBuilder {

    private  Player player1;

    private  Player player2;

    private  Field field;

    private  String name;

    public GameBuilder player1(final Player player1) {
        this.player1 = player1;
        return this;
    }

    public GameBuilder player2(final Player player2) {
        this.player2 = player2;
        return this;
    }

    public GameBuilder field(final Field field) {
        this.field = field;
        return this;
    }

    public GameBuilder name(final String name) {
        this.name = name;
        return this;
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

    public Game build() {
        return new Game(this);
    }
}
