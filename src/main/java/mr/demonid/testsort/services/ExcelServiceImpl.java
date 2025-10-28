package mr.demonid.testsort.services;

import mr.demonid.testsort.utils.QuickSelect;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ExcelServiceImpl implements ExcelService {


    public Integer findNthMin(String filePath, int n) throws Exception {
        List<Integer> numbers = readNumbers(filePath);

        if (numbers.isEmpty())
            throw new IllegalArgumentException("Файл пуст или не содержит чисел.");

        if (n < 1 || n > numbers.size())
            throw new IllegalArgumentException("Некорректное значение N.");

        int[] array = numbers.stream().mapToInt(Integer::intValue).toArray();

        return QuickSelect.select(array, 0, array.length - 1, n - 1);
    }

    /*
     * Читает значения мз файла в список.
     * Для исключения дубликатов читаем во множество, а затем просто преобразуем в список.
     */
    private List<Integer> readNumbers(String path) throws Exception {
        Set<Integer> numbers = new HashSet<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case NUMERIC -> numbers.add((int) cell.getNumericCellValue());
                        case STRING -> {
                            try {
                                numbers.add(Integer.parseInt(cell.getStringCellValue().trim()));
                            } catch (NumberFormatException ignored) {}
                        }
                    }
                }
            }
        }
        return new ArrayList<>(numbers);
    }

}
