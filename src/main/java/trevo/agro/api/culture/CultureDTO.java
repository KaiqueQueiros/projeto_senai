package trevo.agro.api.culture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CultureDTO(
        @NotEmpty(message = "O campo nome da cultura é obrigatorio")
        @NotBlank
        String name
) {
    public String getName() {
        return name;
    }

    public Object setName() {
        return name;
    }
}
