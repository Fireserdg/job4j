package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.*;
import ru.job4j.chess.figures.white.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Тестовый класс проверящий логику.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class LogicTest {

    /**
     * Fields object Logic for test method move figure.
     */
    private Logic logic = new Logic();

    @Test
    public void whenPawnBlackCanMove()  {
        logic.add(new PawnBlack(Cell.F7));
        assertThat(logic.move(Cell.F7, Cell.F6), is(true));
    }

    @Test
    public void whenPawnBlackCanNotMove() {
        logic.add(new PawnBlack(Cell.F7));
        assertThat(logic.move(Cell.F7, Cell.G6), is(false));
    }

    @Test
    public void whenPawnBlackWantToMoveAndFigureNotOnWay() {
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.E6));
        assertThat(logic.move(Cell.D7, Cell.D6), is(true));
    }

    @Test
    public void whenPawnBlackWantToButFigureOnWay() {
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.D6));
        assertThat(logic.move(Cell.D7, Cell.D6), is(false));
    }

    @Test
    public void whenKnightBlackCanMove() {
        logic.add(new KnightBlack(Cell.C6));
        assertThat(logic.move(Cell.C6, Cell.B8), is(true));
    }

    @Test
    public void whenKnightBlackCanNotMove() {
        logic.add(new KnightBlack(Cell.C6));
        assertThat(logic.move(Cell.C6, Cell.C3), is(false));
    }

    @Test
    public void whenKnightBlackWantToMoveAndOccupiedCell() {
        logic.add(new KnightBlack(Cell.B1));
        logic.add(new PawnBlack(Cell.C3));
        assertThat(logic.move(Cell.B1, Cell.C3), is(false));
    }

    @Test
    public void whenBishopBlackCanMove() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F2));
        assertThat(logic.move(Cell.F2, Cell.B6), is(true));
    }

    @Test
    public void whenBishopBlackCanNotMove() {
        logic.add(new BishopBlack(Cell.F8));
        assertThat(logic.move(Cell.F8, Cell.B1), is(false));
    }

    @Test
    public void whenBishopBlackWantToMoveAndFigureNotOnWay() {
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.F8));
        assertThat(logic.move(Cell.F8, Cell.D6), is(true));
    }

    @Test
    public void whenBishopBlackWantToMoveButFigureOnWay() {
        logic.add(new PawnBlack(Cell.E7));
        logic.add(new BishopBlack(Cell.F8));
        assertThat(logic.move(Cell.F8, Cell.D6), is(false));
    }

    @Test
    public void whenRookBlackCanMoveHorizontal() {
        logic.add(new RookBlack(Cell.E5));
        assertThat(logic.move(Cell.E5, Cell.H5), is(true));
    }

    @Test
    public void whenRookBlackCanMoveVertical() {
        logic.add(new RookBlack(Cell.F6));
        assertThat(logic.move(Cell.F6, Cell.F1), is(true));
    }

    @Test
    public void whenRookBlackCanNotMove() {
        logic.add(new RookBlack(Cell.H8));
        assertThat(logic.move(Cell.H8, Cell.F6), is(false));
    }

    @Test
    public void whenRookBlackCanNotMoveFigureOnWay() {
        logic.add(new RookBlack(Cell.H8));
        logic.add(new PawnBlack(Cell.H7));
        assertThat(logic.move(Cell.H8, Cell.H5), is(false));
    }

    @Test
    public void whenQeenBlackCanMoveVertical() {
        logic.add(new QeenBlack(Cell.D1));
        assertThat(logic.move(Cell.D1, Cell.D4), is(true));
    }

    @Test
    public void whenQeenBlackCanMoveDiagonal() {
        logic.add(new QeenBlack(Cell.D1));
        assertThat(logic.move(Cell.D1, Cell.H5), is(true));
    }

    @Test
    public void whenQeenBlackCanNotMove() {
        logic.add(new QeenBlack(Cell.D1));
        assertThat(logic.move(Cell.D1, Cell.F4), is(false));
    }

    @Test
    public void whenQeenBlackCanNotMoveFigureOnWay() {
        logic.add(new QeenBlack(Cell.D1));
        logic.add(new PawnBlack(Cell.E2));
        assertThat(logic.move(Cell.D1, Cell.F3), is(false));
    }

    @Test
    public void whenKingBlackCanMoveVertical() {
        logic.add(new KingBlack(Cell.D8));
        assertThat(logic.move(Cell.D8, Cell.D7), is(true));
    }

    @Test
    public void whenKingBlackCanMoveHorizontal() {
        logic.add(new KingBlack(Cell.D6));
        assertThat(logic.move(Cell.D6, Cell.E6), is(true));
    }

    @Test
    public void whenKingBlackCanMoveDiagonal() {
        logic.add(new KingBlack(Cell.E5));
        assertThat(logic.move(Cell.E5, Cell.D4), is(true));
    }

    @Test
    public void whenKingBlackCanNotMove() {
        logic.add(new KingBlack(Cell.E5));
        assertThat(logic.move(Cell.E5, Cell.G3), is(false));
    }

    @Test
    public void whenKingBlackCanNotMoveButFigureOnWay() {
        logic.add(new KingBlack(Cell.E1));
        logic.add(new PawnBlack(Cell.E2));
        assertThat(logic.move(Cell.E1, Cell.E2), is(false));
    }

    @Test
    public void whenPawnWhiteCanMove() {
        logic.add(new PawnWhite(Cell.G2));
        assertThat(logic.move(Cell.G2, Cell.G3), is(true));
    }

    @Test
    public void whenPawnWhiteCanNotMove() {
        logic.add(new PawnWhite(Cell.G2));
        assertThat(logic.move(Cell.G2, Cell.F3), is(false));
    }

    @Test
    public void whenKnightWhiteCanMove() {
        logic.add(new KnightWhite(Cell.C6));
        assertThat(logic.move(Cell.C6, Cell.B8), is(true));
    }

    @Test
    public void whenKnightWhiteCanNotMove() {
        logic.add(new KnightWhite(Cell.C6));
        assertThat(logic.move(Cell.C6, Cell.C3), is(false));
    }

    @Test
    public void whenKnightWhiteWantToMoveAndFigureOnWay() {
        logic.add(new KnightWhite(Cell.B1));
        logic.add(new PawnBlack(Cell.B2));
        assertThat(logic.move(Cell.B1, Cell.C3), is(true));
    }

    @Test
    public void whenBishopWhiteCanMove() {
        Logic logic = new Logic();
        logic.add(new BishopWhite(Cell.F2));
        assertThat(logic.move(Cell.F2, Cell.B6), is(true));
    }

    @Test
    public void whenBishopWhiteCanNotMove() {
        logic.add(new BishopWhite(Cell.F8));
        assertThat(logic.move(Cell.F8, Cell.B1), is(false));
    }

    @Test
    public void whenBishopWhiteWantToMoveAndFigureNotOnWay() {
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopWhite(Cell.F8));
        assertThat(logic.move(Cell.F8, Cell.D6), is(true));
    }

    @Test
    public void whenRookWhiteCanMove() {
        logic.add(new RookWhite(Cell.G8));
        assertThat(logic.move(Cell.G8, Cell.G1), is(true));
    }

    @Test
    public void whenRookWhiteCanNotMove() {
        logic.add(new RookWhite(Cell.G8));
        assertThat(logic.move(Cell.G8, Cell.H1), is(false));
    }

    @Test
    public void whenRookWhiteCanNotMoveFigureOnWay() {
        logic.add(new RookWhite(Cell.H8));
        logic.add(new PawnBlack(Cell.H7));
        assertThat(logic.move(Cell.H8, Cell.H5), is(false));
    }

    @Test
    public void whenQeenWhiteCanMove() {
        logic.add(new QeenWhite(Cell.D1));
        assertThat(logic.move(Cell.D1, Cell.D4), is(true));
    }

    @Test
    public void whenQeenWhiteCanNotMove() {
        logic.add(new QeenWhite(Cell.D1));
        assertThat(logic.move(Cell.D1, Cell.F4), is(false));
    }

    @Test
    public void whenQeenWhiteCanNotMoveFigureOnWay() {
        logic.add(new QeenWhite(Cell.D1));
        logic.add(new PawnBlack(Cell.E2));
        assertThat(logic.move(Cell.D1, Cell.F3), is(false));
    }
    @Test
    public void whenKingWhiteCanMove() {
        logic.add(new KingWhite(Cell.D8));
        assertThat(logic.move(Cell.D8, Cell.D7), is(true));
    }

    @Test
    public void whenKingWhiteCanNotMove() {
        logic.add(new KingWhite(Cell.E5));
        assertThat(logic.move(Cell.E5, Cell.G3), is(false));
    }

    @Test
    public void whenKingWhiteCanNotMoveButFigureOnWay() {
        logic.add(new KingWhite(Cell.E1));
        logic.add(new PawnBlack(Cell.E2));
        assertThat(logic.move(Cell.E1, Cell.E2), is(false));
    }
}