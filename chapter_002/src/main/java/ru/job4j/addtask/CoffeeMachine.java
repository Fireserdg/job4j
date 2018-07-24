package ru.job4j.addtask;

/**
 * Сoffee machine.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 24.07.2018.
 */

public class CoffeeMachine {

    /**
     * Метод, реализующий выдачу сдачи из автомата.
     *
     * @param value купюра.
     * @param price цена.
     * @return сдача.
     */
    public int[] changes(int value, int price) {
        int[] coins = new int[] {1, 2, 5, 10};
        int balance = value - price;
        String change = "";

        for (int i = coins.length - 1; i >= 0; i--) {
            int count = balance / coins[i];
            while (count >= 1) {
                    balance = balance - coins[i];
                    change = change + String.format("%s ", coins[i]);
                count--;
            }
        }

        int[] coinsTotal = new int[change.split(" ").length];
        for (int i = 0; i < coinsTotal.length; i++) {
            coinsTotal[i] = Integer.parseInt(change.split(" ")[i]);
        }
        return coinsTotal;
    }
}
