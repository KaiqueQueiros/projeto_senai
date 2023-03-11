package trevo.agro.api.culture;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record CultureDTO(
        @NotBlank(message = "O campo nome da cultura é obrigatorio")
        @NotEmpty(message =" O campo nome da cultura é obrigatorio")
        String name
) {
}
