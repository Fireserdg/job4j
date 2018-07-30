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
        int[] coinsTotal = new int[]{0};
        int balance;
        String change = "";
        try {
            balance = checkBalance(value, price);
            for (int i = coins.length - 1; i >= 0; i--) {
                int count = balance / coins[i];
                while (count >= 1) {
                    balance = balance - coins[i];
                    change = change + String.format("%s ", coins[i]);
                    count--;
                }
            }
            coinsTotal = new int[change.split(" ").length];
            for (int i = 0; i < coinsTotal.length; i++) {
                coinsTotal[i] = Integer.parseInt(change.split(" ")[i]);
            }
        } catch (NotEnoughMoneyException e) {
            System.out.println("Недостаточно средств");
        }
        return coinsTotal;
    }

    /**
     * Метод для проверки баланса.
     *
     * @param value купюра.
     * @param price цена.
     * @return сумма сдачи.
     * @throws NotEnoughMoneyException Если средств недостаточно.
     */
    public int checkBalance(int value, int price) throws NotEnoughMoneyException {
        int check = value - price;
        if (!(check < 0)) {
            return check;
        } else {
            throw new NotEnoughMoneyException();
        }
    }
}
