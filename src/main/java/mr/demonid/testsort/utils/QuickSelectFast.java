package mr.demonid.testsort.utils;


public class QuickSelectFast {


    public static int select(int[] array, int k) {
        int left = 0;
        int right = array.length - 1;

        // цикл пока не "схлопнутся" границы массива
        while (left < right) {
            int pivot = array[k];   // можно заменить на более оптимальное значение
            int i = left;
            int j = right;

            // проходимся по всему диапазону (left..right),
            // размещая меньшие числа слева от pivot, а большие - справа
            do {
                // ищем первые больший (слева) и меньший (справа) элементы
                while (array[i] < pivot)
                    i++;
                while (pivot < array[j])
                    j--;

                // и менем эти элементы местами, если они в диапазоне
                if (i <= j) {
                    swap(array, i, j);
                    i++;            // продолжаем сканирование со следующих элементов
                    j--;
                }
            } while (i <= j);

            // корректировка границ поиска (выбираем какую часть массива будем дальше "сортировать")
            if (j < k)
                left = i;       // нужный элемент справа
            if (k < i)
                right = j;      // нужный элемент слева
        }

        return array[k];
    }


    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}