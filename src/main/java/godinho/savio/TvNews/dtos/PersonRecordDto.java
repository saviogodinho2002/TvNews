package godinho.savio.TvNews.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PersonRecordDto(@NotNull String name, String phone, @NotNull String email, @NotNull List<String> roles) {
}
