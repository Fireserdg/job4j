package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

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
     * Contains destination Cell.
     *
     */
    private Cell dest;

    /**
     * Contains steps for BomberMan.
     *
     */
    private final BlockingQueue<Cell> steps;

    /**
     * Constructor.
     *
     * @param board new board.
     */
    public Hero(final Board board, final BlockingQueue<Cell> steps) {
        this.board = board;
        this.limit = board.size() / 2;
        this.steps = steps;
    }

    /**
     * Init Hero.
     *
     * @throws InterruptedException if thread is interrupted.
     */
    private void start() throws InterruptedException {
        boolean result;
        do {
            this.source = new Cell(limit + random.nextInt(1),
                    limit + random.nextInt((1)));
            result = this.board.startPosition(this.source);
        } while (!result);
        Cell first = steps.take();
        this.dest = new Cell(source.getX() + first.getX(),
                source.getY() + first.getY());
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
                if (board.move(source, dest)) {
                    Cell insert = this.steps.take();
                    this.source = this.dest;
                    this.dest = new Cell(source.getX() + insert.getX(),
                            source.getY() + insert.getY());
                    Thread.sleep(1000);
                }
                if (this.steps.isEmpty()) {
                    Thread.currentThread().interrupt();
                    System.out.println(String.format("Hero Thread: %s is stop",
                            Thread.currentThread().getName()));
                    this.board.giveAwayLock(source);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}