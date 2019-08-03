package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Point;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class AICoordinateGetter implements ICoordinateGetter {



    private Point[] diag1 = {   new Point(0,0),
                                new Point(1,1),
                                new Point(2,2)};


    private Point[] diag2 = {   new Point(1,1),
                                new Point(2,0),
                                new Point(0,2)};

    private Point[][] mass = {  diag1,diag2,
                                formLine(0),formLine(1),formLine(2),
                                formRow(0),formRow(1),formRow(2)};

    private boolean[] flags = new boolean[8];

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
        Random r = new Random();
        Point res;

        if (field.isEmpty()) {
            return new Point(1, 1);
        }
        ArrayList<Point> os = checkOs(field);
        ArrayList<Point> xs = checkXs(field);

        for (int k=0; k<8; k++) {
            flagsChange(os, mass[k], k);
        }

        for (int i=0; i<xs.size(); i++){
            for(int j=0; j<8; j++) {
                if (flags[j]) {
                    if (Arrays.asList(mass[j]).contains(xs.get(i))) {
                        int randomCell = r.nextInt(3);
                        while (field.getFigure(mass[j][randomCell]) != null) {
                            return mass[j][randomCell];
                        }
                    }
                }
            }
        }










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


    private void flagsChange(ArrayList<Point> os, Point[] item, int j) {
        for (int i=0; i<os.size(); i++) {
            if (!Arrays.asList(item).contains(os.get(i))) {
                this.flags[j] = true;
            }
        }
    }

}
