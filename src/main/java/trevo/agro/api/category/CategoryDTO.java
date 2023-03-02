package trevo.agro.api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CategoryDTO(
        @NotBlank
        @NotEmpty(message = "O campo nome da categoria é obrigatorio")
        String name
) {

    public String getName() {
        return name;
    }

    public Object setName() {
        return name;
    }
}
