package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Point;

public class ConsoleView implements ICoordinateGetter {

    public Point getMoveCoordinate(final Field field) {
        return new Point(0, 0);
    }

    public void show(final Field field) {
        final StringBuilder fieldBuilder = new StringBuilder();
        for (int x = 0; x < field.getSize(); x++) {
            if (x != 0)
                generateSeparator(fieldBuilder);
            generateLine(field, x, fieldBuilder);
        }
        System.out.println(fieldBuilder.toString());
    }

    void generateLine(final Field field,
                      final int x,
                      final StringBuilder sb) {
        for (int y = 0; y < field.getSize(); y++) {
            if (y != 0)
                sb.append("|");
            sb.append(" ");
            final Figure figure;
            //Ошибка в выводе фигур!!!
            figure = field.getFigure(new Point(x, y));
            sb.append(figure != null ? figure : " ");
            sb.append(" ");
        }
        sb.append("\n");
    }

    void generateSeparator(final StringBuilder sb) {
        sb.append("~~~~~~~~~~~\n");
    }
}
