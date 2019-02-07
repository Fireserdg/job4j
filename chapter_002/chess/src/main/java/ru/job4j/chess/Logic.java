package ru.job4j.chess;

import ru.job4j.chess.figures.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Класс отвечающий за логику игры.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 17.07.2018.
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * Метод для добавлени фигуры.
     *
     * @param figure Шахматная фигура.
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     *
     * @param source Начальная клетка.
     * @param dest Конечная клетка.
     * @return возможно ли движение фигуры.
     */
    public boolean move(Cell source, Cell dest)  {
        boolean rst = false;
            try {
                int index = this.findBy(source);
                Cell[] steps = this.figures[index].way(source, dest);
                checkOccupiedWay(steps);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }
            } catch (ImpossibleMoveException | FigureNotFoundException
                    | OccupiedWayException ex) {
                System.out.println(ex.getMessage());
            }
        return rst;
    }

    /**
     * Очищение шахматной доски.
     */
    public void clean() {
        IntStream.range(0, this.figures.length)
                .forEachOrdered(index -> this.figures[index] = null);
        this.index = 0;
    }

    /**
     * Поиск фигуры в заданной клетке.
     *
     * @param cell Клетка на шахматной доске.
     * @return Позиция фигуры в массиве.
     * @throws FigureNotFoundException Исключение если нет фигуры на заданной позиции.
     */
    private int findBy(Cell cell)  {
        return IntStream.range(0, this.figures.length).
                filter(index -> this.figures[index] != null
                        && this.figures[index].position().equals(cell)).findFirst()
                .orElseThrow(() -> new FigureNotFoundException("Фигура не найдена"));
    }

    /**
     * Метод для проверки занятых клеток по пути движения фигуры.
     *
     * @param steps Массив шагов.
     * @throws OccupiedWayException Если на пути движения есть фигура.
     */
    private void checkOccupiedWay(Cell[] steps) {
        boolean result = Arrays.stream(steps).allMatch(step -> Arrays.stream(figures).
                filter(Objects::nonNull).noneMatch((figure -> figure.position.equals(step))));
        if (!result) {
            throw new OccupiedWayException("Клетка по пути движения фигуры занята");
        }
    }
}