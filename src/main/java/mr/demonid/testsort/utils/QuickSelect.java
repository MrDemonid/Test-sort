package mr.demonid.testsort.utils;


import java.util.Arrays;

/**
 * Утилитарный класс нахождения минимума: Quickselect.
 * Основано на Quicksort, но рекурсивно сортирует только половину массива.
 * Средняя сложность: O(n)
 * Худшая: O(n^2)
 */
public class QuickSelect {

    public static int select(int[] array, int left, int right, int k) {
        if (left == right)
            return array[left];

        int pivotIndex = partition(array, left, right); // опорный элемент
        System.out.println("  -- " + Arrays.toString(array));
        if (k == pivotIndex)    // нашли искомый элемент
            return array[k];
        else if (k < pivotIndex)
            return select(array, left, pivotIndex - 1, k);
        else
            return select(array, pivotIndex + 1, right, k);
    }

    /*
     * Разделяем массив по опорному элементу. Слева будут элементы меньше pivot, справа - больше.
     * Но, элементы не сортируются!
     * @return индекс опорного элемента.
     */
    private static int partition(int[] array, int left, int right) {
        // выбираем опорник из трех значений
        int pivotIndex = medianOfThree(array, left, left + (right - left) / 2, right);
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);
        int i = left;
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, right);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /*
     * Выборка среднего значения.
     */
    private static int medianOfThree(int[] array, int a, int b, int c) {
        int x = array[a], y = array[b], z = array[c];
        if ((x - y) * (z - x) >= 0) return a; // x медиана
        else if ((y - x) * (z - y) >= 0) return b; // y медиана
        else return c; // z медиана
    }

}
