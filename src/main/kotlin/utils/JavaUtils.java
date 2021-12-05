package utils;

import java.util.ArrayList;
import java.util.List;

public class JavaUtils {

    public static List<Vector> diagonals(Vector begin, Vector end) {
        List<Vector> diagonals = new ArrayList<>();
        if (begin.getX() < end.getX() && begin.getY() < end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x <= end.getX() && y <= end.getY(); x++, y++) {
                diagonals.add(new Vector(x, y));
            }
        }
        if (begin.getX() > end.getX() && begin.getY() < end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x >= end.getX() && y <= end.getY(); x--, y++) {
                diagonals.add(new Vector(x, y));
            }
        }
        if (begin.getX() < end.getX() && begin.getY() > end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x <= end.getX() && y >= end.getY(); x++, y--) {
                diagonals.add(new Vector(x, y));
            }
        }
        if (begin.getX() > end.getX() && begin.getY() > end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x >= end.getX() && y >= end.getY(); x--, y--) {
                diagonals.add(new Vector(x, y));
            }
        }
        return diagonals;
    }
}
