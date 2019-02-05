package ru.job4j.filters;

/**
 * Student
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class Students {

    /**
     * Student score.
     */
    private int score;

    /**
     * Constructor student.
     *
     * @param score score
     */
    public Students(int score) {
        this.score = score;
    }

    /**
     * Get score student.
     *
     * @return score
     */
    public int getScore() {
        return score;
    }
}