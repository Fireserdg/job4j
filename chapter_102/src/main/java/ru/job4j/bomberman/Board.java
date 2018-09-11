package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Board.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 05.09.2018.
 */
public class Board {

    /**
     * Contains board.
     *
     */
    private final ReentrantLock[][] board;

    /**
     * Constructor.
     *
     * @param size size board.
     */
    public Board(final int size, final int value) {
        this.board = new ReentrantLock[size][size];
        start();
        lockingCell(value, size);
    }

    /**
     * Fill board.
     *
     */
    private void start() {
        for (int index = 0; index < board.length; index++) {
            for (int row = 0; row < board[index].length; row++) {
                board[index][row] = new ReentrantLock();
            }
        }
    }
    /**
     * Move for board.
     *
     * @param source position character.
     * @param dest Position to which character wants to pass.
     * @return result true if successful or false.
     * @throws InterruptedException if thread is interrupted.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
        if (board[dest.getX()][dest.getY()].tryLock(5, TimeUnit.SECONDS)) {
            board[source.getX()][source.getY()].unlock();
            result = true;
        }
        if (!result) {
            System.out.println(Thread.currentThread().getName() + " is waiting");
        }
        return result;
    }

    /**
     * Start position for character.
     *
     * @param source source Cell.
     * @return result true if successful or false.
     * @throws InterruptedException if thread is interrupted.
     */
    public boolean startPosition(Cell source) throws InterruptedException {
        return board[source.getX()][source.getY()].
                tryLock(50, TimeUnit.MILLISECONDS);
    }

    /**
     * Give away lock Cell if character finished walking.
     *
     * @param source position character.
     */
    public void giveAwayLock(Cell source) {
        this.board[source.getX()][source.getY()].unlock();
    }

    /**
     * Get size board.
     *
     * @return board size.
     */
    public int size() {
        return this.board.length;
    }

    /**
     * Get locking Cell.
     *
     * @param value count of locking Cell.
     * @param size size of board.
     */
    private void lockingCell(int value, int size) {
        Random block = new Random();
        for (int i = 0; i < value; i++) {
            this.board[block.nextInt((size - 1) / 2)]
                    [block.nextInt((size - 1) / 2)].lock();
        }
    }
}