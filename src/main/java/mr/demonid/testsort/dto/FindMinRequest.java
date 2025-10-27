package mr.demonid.testsort.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Schema(
        title = "FindMinRequest",
        description = "Запрос на поиск N-го минимального числа в XLSX-файле. " +
                "Передаётся путь к файлу и порядковый номер минимального значения."
)
public class FindMinRequest {

    @Schema(description = "Путь к XLSX файлу", example = "C:\\data\\numbers.xlsx")
    @NotBlank(message = "Путь к файлу не может быть пустым")
    private String filePath;

    @Schema(description = "Порядковый номер минимального числа", example = "3")
    @Min(value = 1, message = "N должно быть больше или равно 1")
    private Integer n;


    public FindMinRequest(String filePath, Integer n) {
        this.filePath = filePath;
        this.n = n;
    }

    public FindMinRequest() {
        this(null, 0);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }


    @Override
    public String toString() {
        return "FindMinRequest{" +
                "filePath='" + filePath + '\'' +
                ", n=" + n +
                '}';
    }
}
