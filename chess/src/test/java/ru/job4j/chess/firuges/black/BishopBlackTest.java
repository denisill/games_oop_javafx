package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.FigureNotFoundException;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.Logic;
import ru.job4j.chess.OccupiedCellException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {

    @Test
    public void position() {
        BishopBlack positionBishop = new BishopBlack(Cell.A8);
        Cell rsl = positionBishop.position();
        assertThat(rsl,
                is(Cell.A8));
    }

    @Test
    public void way() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell [] rsl = bishopBlack.way(Cell.G5);
        Cell [] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(rsl,
                is(expected));
    }

    @Test
    public void copy() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Figure newBishop = bishop.copy(Cell.A2);
        assertThat(newBishop.position(),
                is(Cell.A2));
    }

    @Test
    public void diagonal() {
        BishopBlack bishop = new BishopBlack(Cell.D7);
        assertThat(bishop.isDiagonal(Cell.D7, Cell.B5),
                is(true));
    }

    @Test(expected = OccupiedCellException.class)
    public void whenCellOccupied() throws FigureNotFoundException, OccupiedCellException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.D4);
        logic.add(bishopBlack);
        KingBlack kingBlack = new KingBlack(Cell.E3);
        logic.add(kingBlack);
        logic.move(Cell.D4, Cell.F2);
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenFigureWasNotFound() throws FigureNotFoundException, OccupiedCellException {
        Logic logic = new Logic();
        BishopBlack bishopBlack = new BishopBlack(Cell.B5);
        logic.add(bishopBlack);
        logic.move(Cell.C6, Cell.D7);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveImpossible() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A6);
        bishopBlack.way(Cell.G5);
    }
}