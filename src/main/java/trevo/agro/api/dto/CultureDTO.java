package trevo.agro.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record CultureDTO(
        @NotEmpty(message = "O campo nome da cultura é obrigatorio")
        String name
) {
}
