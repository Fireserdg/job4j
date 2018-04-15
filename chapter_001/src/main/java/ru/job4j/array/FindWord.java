package ru.job4j.array;
/**
 *Проверка, что одно слово находится в другом.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 15.04.2018
 */
public class FindWord {
    private char[] data;
    private char[] index;
    /**
     * Проверяет, что одно слово находится в другом.
     *
     * @param origin одно слово.
     * @param sub    второе слово.
     * @return true or false.
     */
    public boolean contains(String origin, String sub) {
        this.data = origin.toCharArray();
        this.index = sub.toCharArray();
        boolean result = false;
        int in = 0;
        for (int out = 0; out < data.length; out++) {
            if (data[out] == index[in]) {
                in++;
            } else {
                in = 0;
            }
            if (in == index.length) {
                result = true;
                break;
            }
        }
        return result;
    }
}

