package ru.job4j.array;
/**
 *Слияние двух отсортированных массивов в третий.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 06.05.2018
 */
public class ArrayTwoToThree {
    /**
     *
     * @param one принимаем первый отсортированный массив.
     * @param two принимаем второй отсортированный массив.
     * @return возвращаем третий отсортированный массив.
     */
    public int[] arraysThree(int[] one, int[] two) {
        int[] threeArray = new int[one.length + two.length];
        for (int indexNew = 0, array = 0; indexNew < threeArray.length; indexNew++, array++) {
            if (indexNew == one.length) {
                array = 0;
            }
            if (indexNew < one.length) {
                threeArray [indexNew] = one[array];
            } else {
                threeArray[indexNew] = two[array];
            }
        }
        for (int index = 1; index < threeArray.length; index++) {
            int input = threeArray [index];
            for (int out = index - 1; out >= 0; out--) {
                int data = threeArray [out];
                if (input < data) {
                    threeArray[out + 1] = data;
                    threeArray[out] = input;
                } else {
                    break;
                }
            }
        }
        return threeArray;
    }
}
