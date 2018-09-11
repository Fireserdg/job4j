package ru.job4j.bomberman;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Start Game.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 11.09.2018.
 */
public class Game implements Runnable {

    /**
     * Contains Hero.
     *
     */
    private final Hero hero;

    /**
     * Contains container for monsters.
     *
     */
    private final Monsters[] monsters;

    /**
     * Constructor.
     *
     * @param steps count of steps for Hero.
     * @param boardSize board size.
     * @param lockCell count of locking Cell.
     * @param monsters count of monsters.
     */
    public Game(final BlockingQueue<Cell> steps, final int boardSize,
                final int lockCell, final int monsters) {
        Board board = new Board(boardSize, lockCell);
        this.hero = new Hero(board, steps);
        this.monsters = new Monsters[monsters];
        for (int i = 0; i < this.monsters.length; i++) {
            this.monsters[i] = new Monsters(board);
        }
    }

    @Override
    public void run() {
        int size = Runtime.getRuntime().availableProcessors();
        ExecutorService startCharacter = Executors.newFixedThreadPool(size);
        for (Monsters monster : this.monsters) {
            startCharacter.submit(monster);
        }
        startCharacter.submit(this.hero);
        startCharacter.shutdown();
        while (!startCharacter.isTerminated()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Потоки закончили работу");
    }
}
