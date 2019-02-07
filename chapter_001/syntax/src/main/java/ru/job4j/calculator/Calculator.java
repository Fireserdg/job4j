package ru.job4j.calculator;
/**
 *Calculator
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class Calculator {
    double result;
    /**
     *Add.
     *@param first and second.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }
    /**
     *Subtract.
     *@param first and second.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }
    /**
     *Div.
     *@param first and second.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }
    /**
     *Multiple.
     *@param first and second.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
    /**
     *getResult.
     */
    public double getResult() {
        return this.result;
    }
}
