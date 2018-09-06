package ru.job4j.bomberman;

import java.util.Random;

/**
 * Hero.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 05.09.2018.
 */
public class Hero implements Runnable {

    /**
     * Contains board.
     *
     */
    private final Board board;

    /**
     * Contains random for get random value.
     *
     */
    private final Random random = new Random();

    /**
     * Contains limit board for steps Hero.
     *
     */
    private final int limit;

    /**
     * Contains source.
     *
     */
    private Cell source;

    /**
     * Contains count.
     *
     */
    private int count = 0;

    /**
     * Constructor.
     *
     * @param board new board.
     */
    public Hero(Board board) {
        this.board = board;
        this.limit = board.size() - 1;
    }

    /**
     * Init Hero.
     *
     * @throws InterruptedException if thread is interrupted.
     */
    private void start() throws InterruptedException {
        boolean result;
        this.source = new Cell(random.nextInt(limit),
                random.nextInt(limit));
        do {
            result = this.board.startPosition(source);
        } while (!result);
    }

    /**
     * Check way for Hero.
     *
     * @throws InterruptedException if thread is interrupted.
     */
    private void way() throws InterruptedException {
        Cell dest;
        do {
            int deltaX = random.nextInt(2) == 0 ? -1 : 1;
            int deltaY = random.nextInt(2) == 0 ? 1 : -1;
            dest = new Cell(moveX(deltaX), moveY(deltaY));
        } while (!board.move(source, dest));
        this.source = dest;
    }

    /**
     * Check move Hero for X.
     *
     * @param deltaX deltaX.
     * @return value.
     */
    private int moveX(int deltaX) {
        deltaX = source.getX() == 0 ? 1 : deltaX;
        deltaX = source.getX() == limit ? -1 : deltaX;
        return source.getX() + deltaX;
    }

    /**
     * Check move Hero for X.
     *
     * @param deltaY deltaY.
     * @return value.
     */
    private int moveY(int deltaY) {
        deltaY = source.getY() == 0 ? -1 : deltaY;
        deltaY = source.getY() == limit ? 1 : deltaY;
        return source.getY() - deltaY;
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!Thread.interrupted()) {
            try {
                System.out.println(String.format("Source:%s - Thread:%s ", source,
                        Thread.currentThread().getName()));
                way();
                System.out.println(String.format("Dest:%s - Thread:%s ", source,
                        Thread.currentThread().getName()));
                this.count++;
                if (count == (board.size() * board.size())) {
                    Thread.currentThread().interrupt();
                    this.board.giveAwayLock(source);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(String.format("Thread: %s is stop, count:%s",
                        Thread.currentThread().getName(), count));
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Steps hero.
     *
     * @return steps.
     */
    public int steps() {
        return this.count;
    }
}