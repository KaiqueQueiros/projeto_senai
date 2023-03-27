package trevo.agro.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record AreaDTO(
        @NotEmpty(message = "Tamanho de area obrigatoria")
        String size
) {
}
