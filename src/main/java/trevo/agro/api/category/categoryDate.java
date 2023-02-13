package trevo.agro.api.category;

import jakarta.validation.constraints.NotBlank;

public record categoryDate(Long id, @NotBlank String name) {
}
