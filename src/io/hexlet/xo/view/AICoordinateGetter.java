package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Point;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class AICoordinateGetter implements ICoordinateGetter {

    private RandomCoordinateGetter randomPoint = new RandomCoordinateGetter();

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
            //System.out.println("Changing flag "+k);
            flagsChange(os, mass[k], k);
        }

        for (int i=0; i<8; i++){
            System.out.println(flags[i]);
            //System.out.println(mass[i][0].getX()+" "+mass[i][0].getY());
        }

/*
        for (int i=0; i<8; i++) {
            System.out.println("mass line: "+i);
            for (int j=0;j<3;j++) {
                System.out.println(mass[i][j].getX()+" "+ mass[i][j].getY());
            }
        }
*/

        for (int i=0; i<xs.size(); i++){
            for(int j=0; j<8; j++) {

                if (flags[j]) {
                    String[] massString = new String[mass[j].length];
                    for (int l=0; l<mass[j].length; l++){
                        massString[l] = String.valueOf(mass[j][l].getX())+String.valueOf(mass[j][l].getY());
                    }
                    if (Arrays.asList(massString).contains(String.valueOf(xs.get(i).getX())+String.valueOf(xs.get(i).getY()))) {
                        System.out.println("Got here!");
                        System.out.println("Putting X to mass["+j+"]");
                        System.out.println("mass["+j+"] is "+massString[0]+massString[1]+massString[2]);

                        int randomCell = r.nextInt(3);
                        System.out.println("Random cell is "+ randomCell);

                        int count = 0;
                        while (true){
                            if(field.getFigure(mass[j][randomCell]) == null) {
                                System.out.println("X goes to " + mass[j][randomCell].getX() + " " + mass[j][randomCell].getY());
                                return mass[j][randomCell];
                            }
                            else {
                                r = new Random();
                                randomCell = r.nextInt(3);
                                System.out.println("Random cell is "+ randomCell);
                            }
                            count ++;
                            if (count == 3){
                                break;
                            }
                        }
                    }
                }
            }
        }










        // END

        return randomPoint.getMoveCoordinate(field);
    }

    private ArrayList<Point> checkOs(Field field) {
        ArrayList<Point> res = new ArrayList<Point>();
        for (int i=0; i<field.getSize(); i++) {
            for (int j=0; j<field.getSize(); j++) {
                Point p = new Point(i, j);
                if (field.getFigure(p) == Figure.O) {
                    res.add(p);
                    System.out.println("O at "+ p.getX()+p.getY());
                }
            }
        }
        return res;
    }

    private ArrayList<Point> checkXs(Field field) {
        ArrayList<Point> res = new ArrayList<Point>();
        for (int i=0; i<field.getSize(); i++) {
            for (int j=0; j<field.getSize(); j++) {
                Point p = new Point(i, j);
                if (field.getFigure(p) == Figure.X) {
                    res.add(p);
                    System.out.println("X at "+ p.getX()+p.getY());
                }
            }
        }
        return res;
    }


    private void flagsChange(ArrayList<Point> os, Point[] item, int j) {
        for (int i=0; i<os.size(); i++) {
            //System.out.println("Checking O in mass row "+ j);
            //System.out.println(item[0].getX()+" "+item[0].getY());
            //System.out.println(os.get(i).getX()+" "+os.get(i).getY());
            String[] itemString = new String[item.length];
            for (int l=0; l<item.length; l++){
                 itemString[l] = String.valueOf(item[l].getX())+String.valueOf(item[l].getY());
            }

            if (Arrays.asList(itemString).contains(String.valueOf(os.get(i).getX())+String.valueOf(os.get(i).getY()))) {
                flags[j] = false;
                //System.out.println("Got O, go to false!");
            }
            else {
                flags[j] = true;
            }
        }
    }

}
