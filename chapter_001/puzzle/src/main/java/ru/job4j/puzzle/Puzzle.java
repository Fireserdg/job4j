package ru.job4j.puzzle;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.job4j.puzzle.figures.Block;
import ru.job4j.puzzle.figures.Cell;
import ru.job4j.puzzle.figures.Checker;
import ru.job4j.puzzle.figures.Figure;

import java.util.Objects;
import java.util.Random;

/**
 * Puzzle.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 30.10.2018.
 */
public class Puzzle extends Application {

    /**
     * Contains title
     *
     */
    private static final String JOB4J = "Пазлы на www.job4j.ru";

    /**
     * Contains size
     *
     */
    private final int size = 5;

    /**
     * Contains class Logic
     *
     */
    private final Logic logic = new Logic(size);

    /**
     * Get rectangle
     *
     * @param x coordinate x
     * @param y coordinate y
     * @return rectangle
     */
    private Rectangle buildRectangle(int x, int y) {
        Rectangle rect = new Rectangle();
        rect.setX(x * 40);
        rect.setY(y * 40);
        rect.setHeight(40);
        rect.setWidth(40);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    /**
     * Get rectangle
     *
     * @param x coordinate x
     * @param y coordinate y
     * @param image image
     * @return rectangle
     */
    private Rectangle buildFigure(int x, int y, String image) {
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setHeight(30);
        rect.setWidth(30);
        Image img = new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResource(image)).toString());
        rect.setFill(new ImagePattern(img));
        final Rectangle momento = new Rectangle(x, y);
        rect.setOnDragDetected(
                event -> {
                    momento.setX(event.getX());
                    momento.setY(event.getY());
                }
        );
        rect.setOnMouseDragged(
                event -> {
                    rect.setX(event.getX() - 30 / 2);
                    rect.setY(event.getY() - 30 / 2);
                }
        );
        rect.setOnMouseReleased(
                event -> {
                    if (logic.move(this.extract(momento.getX(), momento.getY()), this.extract(event.getX(), event.getY()))) {
                        rect.setX(((int) event.getX() / 40) * 40 + 5);
                        rect.setY(((int) event.getY() / 40) * 40 + 5);
                        checkWinner();
                    } else {
                        rect.setX(((int) momento.getX() / 40) * 40 + 5);
                        rect.setY(((int) momento.getY() / 40) * 40 + 5);
                    }
                }
        );
        return rect;
    }

    /**
     * Check winner
     *
     */
    private void checkWinner() {
        if (this.logic.isWin()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(JOB4J);
            alert.setHeaderText(null);
            alert.setContentText("Пазл решен! Начните новую Игру!");
            alert.showAndWait();
        }
    }

    /**
     * Build grid
     *
     * @return group
     */
    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                panel.getChildren().add(
                        this.buildRectangle(x, y)
                );
            }
        }
        return panel;
    }

    /**
     * Start
     *
     * @param stage stage
     */
    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox cont = new HBox();
        cont.setPrefHeight(40);
        cont.setSpacing(10.0);
        cont.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Start");
        start.setOnMouseClicked(
                event -> this.refresh(border)
        );
        cont.getChildren().addAll(start);
        border.setBottom(cont);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 400, 400));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        this.refresh(border);
    }

    /**
     * Refresh
     *
     * @param border BorderPane
     */
    private void refresh(final BorderPane border) {
        Group grid = this.buildGrid();
        this.logic.clean();
        border.setCenter(grid);
        this.generate(true, 6, grid);
        this.generate(false, 5, grid);
    }

    /**
     * Generate
     *
     * @param block block
     * @param total total
     * @param grid grid
     */
    public void generate(boolean block, int total,  Group grid) {
        int count = total;
        final Random random = new Random();
        while (count > 0) {
            Cell position = new Cell(random.nextInt(size), random.nextInt(size));
            if (this.logic.isFree(position)) {
                if (block) {
                    this.add(new Block(position), grid);
                } else {
                    this.add(new Checker(position), grid);
                }
                count--;
            }
        }
    }

    /**
     * Add figure
     *
     * @param figure figure
     * @param grid grid
     */
    public void add(Figure figure, Group grid) {
        this.logic.add(figure);
        Cell position = figure.position();
        grid.getChildren().add(
                this.buildFigure(
                        position.x * 40 + 5,
                        position.y * 40 + 5,
                        figure.icon()
                )
        );
    }

    /**
     * Extract
     *
     * @param graphX graphX
     * @param graphY graphY
     * @return Cell
     */
    private Cell extract(double graphX, double graphY) {
        return new Cell((int) graphX / 40, (int) graphY / 40);
    }
}