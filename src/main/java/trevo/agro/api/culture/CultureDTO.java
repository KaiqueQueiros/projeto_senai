package trevo.agro.api.culture;

import jakarta.validation.constraints.NotEmpty;

public record CultureDTO(
        @NotEmpty(message = "O campo nome da cultura é obrigatorio")
        String name
) {
}
