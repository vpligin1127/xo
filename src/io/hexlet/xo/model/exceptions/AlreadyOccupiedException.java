package io.hexlet.xo.model.exceptions;


import java.io.PrintStream;

public class AlreadyOccupiedException extends AbstractXOException {
    public AlreadyOccupiedException() {
        super("Not a valid cell! The cell is busy.");
    }
}
