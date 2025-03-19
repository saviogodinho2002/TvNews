package godinho.savio.TvNews.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.Date;

public record NewsPaperRecordDto(@NotNull Date executionDate, @NotNull LocalTime executionTime, @NotNull String description) {

}
