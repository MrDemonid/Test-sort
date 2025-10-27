package mr.demonid.testsort.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mr.demonid.testsort.dto.FindMinRequest;
import mr.demonid.testsort.services.ExcelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/api")
@Tag(name = "Min finder API", description = "REST API для тестового задания по поиску в XLSX файле.")
public class ApiController {

    private final ExcelService excelService;


    public ApiController(ExcelService excelService) {
        this.excelService = excelService;
    }


    @Operation(summary = "Поиск N-го минимума.", description = "Найти N-ое минимальное число из XLSX файла")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный ответ"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping("/min-number")
    public Integer findNthMin(@Valid @RequestBody FindMinRequest request) throws Exception {
        return excelService.findNthMin(request.getFilePath(), request.getN());
    }


}
