package ru.job4j.bomberman;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenMakeTwoHeroThenStartGame() throws InterruptedException {
        Board board = new Board(3);
        Hero one = new Hero(board);
        Hero two = new Hero(board);
        Thread threadOne = new Thread(one);
        Thread threadTwo = new Thread(two);
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        assertThat(one.steps(), is(9));
        assertThat(two.steps(), is(9));
    }
}