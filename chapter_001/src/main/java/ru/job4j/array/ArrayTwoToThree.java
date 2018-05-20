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

        int [] three = new int[one.length + two.length];
        int i = 0, j = 0, index = 0;
        while (i < one.length && j < two.length) {
            three[index++] = one[i]<two[j] ? one[i++] : two[j++];
        }

        if (i < one.length) {
            System.arraycopy(one,i,three,index,one.length - i);
        }

        if (j<two.length) {
            System.arraycopy(two,j,three, index,two.length - j);
        }
       return three;
    }
}
