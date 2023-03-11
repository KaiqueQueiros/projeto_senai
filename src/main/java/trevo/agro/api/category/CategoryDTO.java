package trevo.agro.api.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "O campo nome da categoria é obrigatorio")
        String name
) {
}
