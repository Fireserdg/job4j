package ru.job4j.model;

import java.util.Objects;

/**
 * Hall
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 29.01.19
 */
public class Hall {

    /**
     * Hall id.
     */
    private final int id;

    /**
     * Row number.
     */
    private final int row;

    /**
     * Seat number.
     */
    private final int seat;

    /**
     * Price for hall.
     */
    private final int price;

    /**
     * Booked hall.
     */
    private boolean booked;

    /**
     * Constructor hall.
     * @param id halls id.
     * @param row row number.
     * @param seat seat number.
     * @param price price for halls.
     * @param booked booked hall.
     */
    public Hall(int id, int row, int seat, int price, boolean booked) {
        this.id = id;
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.booked = booked;
    }

    /**
     * Hall id.
     * @return hall id.
     */
    public int getId() {
        return id;
    }

    /**
     * Get row number.
     * @return row number.
     */
    public int getRow() {
        return row;
    }

    /**
     * Get seat.
     * @return seat number.
     */
    public int getSeat() {
        return seat;
    }

    /**
     * Booked hall.
     * @return true if booked.
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Get price.
     * @return price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set booked.
     * @param is parameter true or false.
     */
    public void setBooked(boolean is) {
        this.booked = is;
    }

    /**
     * Equals between objects.
     * @param o other object.
     * @return true if the same object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return id == hall.id && row == hall.row
                && seat == hall.seat && price == hall.price
                && booked == hall.booked;
    }

    /**
     * Hall hashcode.
     * @return hashcode account.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, row, seat, price, booked);
    }

    /**
     * String representation of an object.
     * @return representation of an object.
     */
    @Override
    public String toString() {
        return  String.format("Hall{id=%s, row=%s, seat=%s, price=%s, booked=%s}",
                this.id, this.row, this.seat, this.price, this.booked);
    }
}
