package trevo.agro.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record CategoryDTO(
        @NotEmpty(message = "O campo nome da categoria é obrigatorio")
        String name
) {
}
