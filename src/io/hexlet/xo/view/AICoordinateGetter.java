package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Point;
import java.util.Random;
import java.util.ArrayList;

public class AICoordinateGetter implements ICoordinateGetter {

    private Point[] diag1 = {   new Point(0,0),
                                new Point(1,1),
                                new Point(2,2)};


    private Point[] diag2 = {   new Point(1,1),
                                new Point(2,0),
                                new Point(0,2)};

    private Point[] formRow(int i){
        Point[] res = new Point[3];
        for (int j=0; j<3; j++){
            res[j] = new Point(j,i);
        }
        return res;
    }

    private Point[] formLine(int i){
        Point[] res = new Point[3];
        for (int j=0; j<3; j++){
            res[j] = new Point(i,j);
        }
        return res;
    }

    @Override
    public Point getMoveCoordinate(final Field field) {
        // BEGIN (write your solution here) (write your solution here)
        if (field.isEmpty()) {
            return new Point(1, 1);
        }
        ArrayList<Point> os = checkOs(field);
        ArrayList<Point> xs = checkXs(field);









        // END

        return null;
    }

    private ArrayList<Point> checkOs(Field field) {
        ArrayList<Point> res = new ArrayList<Point>();
        for (int i=0; i<field.getSize(); i++) {
            for (int j=0; j<field.getSize(); j++) {
                Point p = new Point(i,j);
                if (field.getFigure(p) == Figure.O) {
                    res.add(p);
                }
            }
        }
        return res;
    }

    private ArrayList<Point> checkXs(Field field) {
        ArrayList<Point> res = new ArrayList<Point>();
        for (int i=0; i<field.getSize(); i++) {
            for (int j=0; j<field.getSize(); j++) {
                Point p = new Point(i,j);
                if (field.getFigure(p) == Figure.X) {
                    res.add(p);
                }
            }
        }
        return res;
    }



}
