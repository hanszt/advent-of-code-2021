package utils;

import java.util.ArrayList;
import java.util.List;

public final class JavaUtils {

    private JavaUtils() {
    }

    public static List<Vector> diagonal(Vector begin, Vector end) {
        List<Vector> diagonals = new ArrayList<>();
        diagonalLeftToRight(begin, end, diagonals);
        diagonalRightToLeft(begin, end, diagonals);
        return diagonals;
    }

    private static void diagonalLeftToRight(Vector begin, Vector end, List<Vector> diagonals) {
        if (begin.getX() < end.getX() && begin.getY() < end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x <= end.getX() && y <= end.getY(); x++, y++) {
                diagonals.add(new Vector(x, y));
            }
        }
        if (begin.getX() > end.getX() && begin.getY() > end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x >= end.getX() && y >= end.getY(); x--, y--) {
                diagonals.add(new Vector(x, y));
            }
        }
    }

    private static void diagonalRightToLeft(Vector begin, Vector end, List<Vector> diagonals) {
        if (begin.getX() < end.getX() && begin.getY() > end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x <= end.getX() && y >= end.getY(); x++, y--) {
                diagonals.add(new Vector(x, y));
            }
        }
        if (begin.getX() > end.getX() && begin.getY() < end.getY()) {
            for (int x = begin.getX(), y = begin.getY(); x >= end.getX() && y <= end.getY(); x--, y++) {
                diagonals.add(new Vector(x, y));
            }
        }
    }
}
