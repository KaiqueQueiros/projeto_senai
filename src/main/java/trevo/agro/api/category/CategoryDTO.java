package trevo.agro.api.category;

import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;

public record CategoryDTO(
        @NotEmpty(message = "O campo nome da categoria é obrigatorio")
        String name
) {
}
