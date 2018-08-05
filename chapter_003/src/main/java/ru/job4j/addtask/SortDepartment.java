package ru.job4j.addtask;

import java.util.*;

/**
 * Sort Department.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 05.08.2018.
 */
public class SortDepartment {

    /**
     * Sort Departments by increase.
     *
     * @param list list position Departments.
     * @return sort list Departments by Decrease.
     */
    public String[] sortIncrease(String[] list) {
        List<String> listDept = addElement(new ArrayList<>(Arrays.asList(list)), list);
        Collections.sort(listDept);
        return listDept.toArray(new String[0]);
    }

    /**
     * Sort Departments by decrease.
     *
     * @param list list position Departments.
     * @return sort list Departments by Decrease.
     */
    public String[] sortDecrease(String[] list) {
        List<String> listDept = addElement(new ArrayList<>(Arrays.asList(list)), list);
        listDept.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = -1;
                int size = o1.length() >= o2.length() ? o2.length() : o1.length();
                for (int index = 0; index < size; index++) {
                    result = Character.compare(o1.charAt(index), o2.charAt(index));
                    if (result != 0) {
                        break;
                    }
                }
                return result == 0 ? o1.length() - o2.length() : result * (-1);
            }
        });
        return listDept.toArray(new String[0]);
    }

    /**
     * Add missing divisions.
     *
     * @param list List of departments as a collection of strings
     * @param array List of departments as an array of strings.
     * @return full List of departments
     */
    private List<String> addElement(List<String> list, String[] array) {
        for (int i = 0; i < array.length; i++) {
            String[] arrays = array[i].split("\\\\");
            for (int j = 0; j < arrays.length - 1; j++) {
                if (!(list.contains(arrays[j]))) {
                    list.add(arrays[j]);
                } else if (!(list.contains(arrays[j] + "\\" + arrays[j + 1]))) {
                    list.add(arrays[j] + "\\" + arrays[j + 1]);
                }
                arrays[j + 1] = String.format("%s\\%s", arrays[j], arrays[j + 1]);
            }
        }
        return list;
    }
}
