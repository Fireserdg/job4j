package ru.job4j.comparator;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * List Compare.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 31.07.2018.
 */
public class ListCompare implements Comparator<String> {

    /**
     * Compare 2 words.
     *
     * @param left one String.
     * @param right two String.
     * @return result compare.
     */
    @Override
    public int compare(String left, String right) {
        return IntStream.range(0, Math.min(left.length(), right.length()))
                .filter(index -> left.charAt(index) != right.charAt(index))
                .map(result -> left.charAt(result) - right.charAt(result)).findFirst()
                .orElse(left.length() - right.length());
    }
}
