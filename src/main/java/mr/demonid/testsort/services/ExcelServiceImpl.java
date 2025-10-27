package mr.demonid.testsort.services;

import mr.demonid.testsort.utils.QuickSelect;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


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
     */
    private List<Integer> readNumbers(String path) throws Exception {
        List<Integer> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    list.add((int) cell.getNumericCellValue());
                }
            }
        }
        return list;
    }

}
