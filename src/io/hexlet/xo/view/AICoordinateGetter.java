package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Point;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class AICoordinateGetter implements ICoordinateGetter {

    private RandomCoordinateGetter randomPoint = new RandomCoordinateGetter();
    // Формируем двумерный массив из координат линий поля. 8 строчек соответствуют по порядку:
    // 2 диагонали, 3 строчки и 3 столбца
    private Point[] diag1 = {   new Point(0,0),
                                new Point(1,1),
                                new Point(2,2)};


    private Point[] diag2 = {   new Point(1,1),
                                new Point(2,0),
                                new Point(0,2)};

    private Point[][] mass = {  diag1,diag2,
                                formLine(0),formLine(1),formLine(2),
                                formRow(0),formRow(1),formRow(2)};

    // массив флагов, соответствующих по индексу линиям поля
    private Figure[] flags = new Figure[8];

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

        // Первый ход в центр
        // Выигрывает и так и так
        /*
        if (field.isEmpty()) {
            return new Point(1, 1);
        }
         */

        // Проверка ситуации, когда до выигрыша 1 ход
        for (int i = 0; i<8; i++){
            if (checkWin(mass[i], field, Figure.X) != null && field.getFigure(checkWin(mass[i], field, Figure.X)) == null){
                return checkWin(mass[i], field, Figure.X);
            }
        }

        //Проверка ситуации, когда до проигрыша 1 ход.
        // В таком случае экстренно мешаем выиграть противнику
        for (int i = 0; i<8; i++){
            if (checkWin(mass[i], field, Figure.O) != null && field.getFigure(checkWin(mass[i], field, Figure.O)) == null){
                return checkWin(mass[i], field, Figure.O);
            }
        }

        //Списки координат крестиков и ноликов
        ArrayList<Point> os = checkOs(field);
        ArrayList<Point> xs = checkXs(field);

        //Перебор линий поля. Если в линии есть нолик, её флаг - Figure.O,
        // Если в линии есть Х, её флаг - Figure.O,
        // Если линия пустая, её флаг - null
        for (int k=0; k<8; k++) {
            flagsChange(os, xs, mass[k], k);
        }


        // Основная логика решения о выставлении крестика
        //Если в какой-то из линий есть крестик, то ставим в неё. В приоритете диагонали.
        // В пределах линии приоритет отдается клеткам, которые пересекаются с линией, в которой есть нолик.
        for (Point i : xs){
            for (int j=0; j<8; j++){
                if (flags[j] == Figure.X){
                    for (int k=0; k<8; k++){
                        if (flags[k] == Figure.O){
                            if (intersection(mass[j], mass[k]) != null && field.getFigure(intersection(mass[j], mass[k])) == null) {
                                return intersection(mass[j], mass[k]);
                            }
                            else return randomInLine(mass[j], field);
                        }
                    }
                }
            }
        }

        return randomPoint.getMoveCoordinate(field);
        // END
    }

    private ArrayList<Point> checkOs(Field field) {
        ArrayList<Point> res = new ArrayList<Point>();
        for (int i=0; i<field.getSize(); i++) {
            for (int j=0; j<field.getSize(); j++) {
                Point p = new Point(i, j);
                if (field.getFigure(p) == Figure.O) {
                    res.add(p);
                    //System.out.println("O at "+ p.getX()+p.getY());
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
                    //System.out.println("X at "+ p.getX()+p.getY());
                }
            }
        }
        return res;
    }


    private void flagsChange(ArrayList<Point> os, ArrayList<Point> xs, Point[] item, int j) {
        for (Point i : os) {
            if (Arrays.asList(item).contains(i)) {
                flags[j] = Figure.O;
            }
        }

        for (Point i : xs) {
            if (Arrays.asList(item).contains(i) && flags[j] != Figure.O) {
                flags[j] = Figure.X;
            }
        }


    }

    // Метод, находящий координаты ячейки, в которой пересекаются 2 линии
    private Point intersection(Point[] lineA, Point[] lineB){
        for (Point i : lineA){
            for (Point j : lineB){
                if (j.equals(i)){
                    return j;
                }
            }
        }
        return null;
    }

    // Метод выставления крестика в одну из свободных клеток в линии. Выбирается случайно.
    //Если выбрать не удалось, ставит на любую свободную клетку на поле.
    private Point randomInLine(Point[] item, Field field){
        Random r = new Random();
        int randomCell = r.nextInt(3);
        //System.out.println("Random cell is "+ randomCell);

        int count = 0;
        while (true) {
            if (field.getFigure(item[randomCell]) == null) {
                //System.out.println("X goes to " + mass[j][randomCell].getX() + " " + mass[j][randomCell].getY());
                return item[randomCell];
            } else {
                r = new Random();
                randomCell = r.nextInt(3);
                //System.out.println("Random cell is "+ randomCell);
            }
            count++;
            if (count == 3) {
                break;
            }
        }
        return randomPoint.getMoveCoordinate(field);

    }

    // Метод, проверяющий наличие 2 одинаковых фигур в линии и возвращающий координаты свободной ячейки
    private Point checkWin(Point[] item, Field field, Figure f){
        int count = 0;
        for (Point i: item){
            if (field.getFigure(i) == f){
                count ++;
            }
        }
        if (count == 2){
            for (Point i: item){
                if (field.getFigure(i) == null){
                    return i;
                }
            }
        }
        return null;
    }


}
