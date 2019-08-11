package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Point;
import io.hexlet.xo.model.Figure;

public interface ICoordinateGetter {

    Point getMoveCoordinate(final Field field, Figure f);

}
