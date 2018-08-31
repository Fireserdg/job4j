package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Rectangle Move.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 21.08.2018.
 */
public class RectangleMove implements Runnable {

    /**
     * Contains rectangle.
     *
     */
    private final Rectangle rect;

    /**
     * Constructor Rectangle Move.
     *
     * @param rect new Rectangle.
     */
    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        int delta = 1;
        while (!Thread.interrupted()) {
            double getX = this.rect.getX();
            delta = getX == 0 ? 1 : getX == 290 ? -1 : delta;
            this.rect.setX(getX + delta);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}