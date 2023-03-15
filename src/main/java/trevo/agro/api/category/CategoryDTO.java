package trevo.agro.api.category;

import jakarta.validation.constraints.NotEmpty;

public record CategoryDTO(
        @NotEmpty(message = "O campo nome da categoria é obrigatorio")
        String name
) {
}
