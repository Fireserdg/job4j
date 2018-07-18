package ru.job4j.chess;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.black.BishopBlack;
import ru.job4j.chess.figures.black.PawnBlack;


/**
 * Тестовый класс проверящий логику.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class LogicTest {


    @Test
    public void whenPawnBlackCanMove()  {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.F7));
        assertThat(logic.move(Cell.F7,Cell.F6), is(true));
    }

    @Test
    public void whenPawnBlackCanNotMove() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.F7));
        assertThat(logic.move(Cell.F7,Cell.G6), is(false));
    }

    @Test
    public void whenBishopBlackCanMove() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F2));
        assertThat(logic.move(Cell.F2,Cell.B6), is(true));
    }

    @Test
    public void whenBishopBlackCanNotMove() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F8));
        assertThat(logic.move(Cell.F8,Cell.B1), is(false));
    }

    @Test
    public void whenPawnBlackWantToMoveAndFigureNotOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.E6));
        assertThat(logic.move(Cell.D7,Cell.D6), is(true));
    }

    @Test
    public void whenPawnBlackWantToButFigureOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.D6));
        assertThat(logic.move(Cell.D7,Cell.D6), is(false));
    }
    @Test
    public void whenBishopBlackWantToMoveAndFigureNotOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.D7));
        logic.add(new BishopBlack(Cell.F8));
        assertThat(logic.move(Cell.F8,Cell.D6), is(true));
    }

    @Test
    public void whenBishopBlackWantToMoveButFigureOnWay() {
        Logic logic = new Logic();
        logic.add(new PawnBlack(Cell.E7));
        logic.add(new BishopBlack(Cell.F8));
        assertThat(logic.move(Cell.F8,Cell.D6), is(false));
    }
}