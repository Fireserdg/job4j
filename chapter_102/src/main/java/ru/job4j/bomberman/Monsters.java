package ru.job4j.bomberman;

import java.util.Random;

/**
 * Hero.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 11.09.2018.
 */
public class Monsters implements Runnable {

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
     * @param board board.
     */
    public Monsters(Board board) {
        this.board = board;
        this.limit = board.size() / 2;
    }

    /**
     * Init Monster.
     *
     * @throws InterruptedException if thread is interrupted.
     */
    private void start() throws InterruptedException {
        boolean result;
        this.source = new Cell(limit + random.nextInt(3),
                limit + random.nextInt(3));
        do {
            result = this.board.startPosition(source);
        } while (!result);
    }

    /**
     * Check way for Monster.
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
     * Check move Monster for X.
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
     * Check move Monster for X.
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
                way();
                this.count++;
                if (count == (board.size() / 2)) {
                    Thread.currentThread().interrupt();
                    this.board.giveAwayLock(source);
                    System.out.println(String.format("Thread: %s is stop, count:%s",
                            Thread.currentThread().getName(), count));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}