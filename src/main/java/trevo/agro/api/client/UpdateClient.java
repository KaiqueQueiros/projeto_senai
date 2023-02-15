package trevo.agro.api.client;

import jakarta.validation.constraints.NotNull;

public record UpdateClient(
        @NotNull Long id,
        String name,
        String phone,
        String email,
        String country
) {

}
