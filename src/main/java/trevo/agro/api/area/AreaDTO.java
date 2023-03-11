package trevo.agro.api.area;

import jakarta.validation.constraints.NotBlank;

public record AreaDTO(
        @NotBlank(message = "Tamanho de area obrigatoria")
        String size
) {
}
