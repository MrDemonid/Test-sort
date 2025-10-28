package mr.demonid.testsort.utils;


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
        int pivot = medianOfThree(array[left], array[left + (right - left) / 2], array[right]);
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
    private static int medianOfThree(int a, int b, int c) {
        if ((b >= a && b <= c) || (b >= c && b <= a))
            return b;
        else if ((a >= b && a <= c) || (a >= c && a <= b))
            return a;
        else return c;
    }

}
