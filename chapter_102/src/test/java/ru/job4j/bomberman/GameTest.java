package ru.job4j.bomberman;

import org.junit.Test;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Game.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 11.09.2018.
 */
public class GameTest {

    @Test
    public void whenMake12MonstersThenStartGame() throws InterruptedException {
        BlockingQueue<Cell> steps = new LinkedBlockingDeque<>();
        steps.add(new Cell(0, 1));
        steps.add(new Cell(0, 1));
        steps.add(new Cell(1, 1));
        steps.add(new Cell(1, 0));
        steps.add(new Cell(-1, 1));
        Game game = new Game(steps, 16, 5, 12);
        Thread startGame = new Thread(game);
        startGame.start();
        startGame.join();
        assertThat(steps.isEmpty(), is(true));
    }
}