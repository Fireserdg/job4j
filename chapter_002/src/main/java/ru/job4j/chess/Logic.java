package ru.job4j.chess;

import ru.job4j.chess.figures.*;

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
                checkOccepidedWay(steps, this.figures);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }
            } catch (ImpossibleMoveException ime) {
                System.out.println("Фигура так пойти не может");
            } catch (FigureNotFoundException fnf) {
                System.out.println("Фигура не найдена");
            } catch (OccupiedWayException owe) {
                System.out.println("Клетка по пути движения фигуры занята");
            }
        return rst;
    }

    /**
     * Очищение шахматной доски.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Поиск фигуры в заданной клетке.
     *
     * @param cell Клетка на шахматной доске.
     * @return Позиция фигуры в массиве.
     * @throws FigureNotFoundException Исключение если нет фигуры на заданной позиции.
     */
    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        if (rst == -1) {
            throw new FigureNotFoundException("Фигура на данной клетке не найдена.");
        }

        return rst;
    }

    /**
     * Метод для проверки занятых клеток по пути движения фигуры.
     *
     * @param steps Массив шагов.
     * @param figures Массив фигур.
     * @throws OccupiedWayException Если на пути движения есть фигура.
     */
    private void checkOccepidedWay(Cell[] steps, Figure[] figures) throws OccupiedWayException {
        boolean result = true;
        for (int i = 0; i < steps.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (figures[j] != null && steps[i].equals(figures[j].position)) {
                    result = false;
                    break;
                }
            }
        }
        if (!result) {
            throw new OccupiedWayException("Клетка занята");
        }
    }
}
