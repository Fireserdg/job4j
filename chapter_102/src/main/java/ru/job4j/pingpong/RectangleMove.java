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
     * Contains marker for tasks switching.
     *
     */
    private boolean markX;

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
        while (true) {
            if(this.rect.getX() == 0) {
                this.markX = false;
            } else if(this.rect.getX() == 290) {
                this.markX = true;
            }
            if(!markX) {
                this.rect.setX(this.rect.getX() + 1);
            } else {
                this.rect.setX(this.rect.getX() - 1);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}