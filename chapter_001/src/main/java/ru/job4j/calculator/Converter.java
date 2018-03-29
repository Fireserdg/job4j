package ru.job4j.calculator;
/**
 *Конвертер валюты
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class Converter {
    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public double rubleToEuro(double value) {
        double euro = value / 70;
        return euro;
    }
    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public double rubleToDollar(double value) {
        double dollar = value / 60;
        return dollar;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value Доллары.
     * @return Рубли
     */
    public double dollarToRuble(double value) {
        double rublle = value / 60;
        return rublle;
    }
    /**
     * Конвертируем доллары в рубли.
     * @param value Евро.
     * @return Рубли
     */
    public double euroToRuble(double value) {
        double rublle = value / 70;
        return rublle;
    }
}
