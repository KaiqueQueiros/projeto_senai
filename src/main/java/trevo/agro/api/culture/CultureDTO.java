package trevo.agro.api.culture;

import jakarta.validation.constraints.NotBlank;


public record CultureDTO(
        String name
) {
    public String getName() {
        return name;
    }

    public Object setName() {
        return name;
    }
}
