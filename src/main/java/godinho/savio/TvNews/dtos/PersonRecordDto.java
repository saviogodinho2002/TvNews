package godinho.savio.TvNews.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record PersonRecordDto(@NotNull String name, String phone, @NotNull String email, @NotNull Set<String> roles) {
}
