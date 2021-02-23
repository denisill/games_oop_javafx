package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell ps) {
        position = ps;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) {
        if (!isDiagonal(position, dest)) {
            throw new ImpossibleMoveException(
                    String.format("Could not move by diagonal from %s to %s", position, dest)
            );
        }
        int size = Math.abs(dest.getX() - position().getX());
        Cell[] steps = new Cell[size];
        int deltaX = (dest.getX() - position().getX() > 0) ? 1 : -1;
        int deltaY = (dest.getY() - position().getY() > 0) ? 1 : -1;
        int positionX = position().getX();
        int positionY = position().getY();
        for (int index = 0; index < size; index++) {
            int x = positionX + deltaX;
            int y = positionY + deltaY;
            steps[index] = Cell.findBy(x, y);
            positionX = x;
            positionY = y;
        }
        return steps;
    }

    public boolean isDiagonal(Cell position, Cell dest) {
        return Math.abs(dest.getX() - position.getX()) == Math.abs(dest.getY() - position.getY());
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
