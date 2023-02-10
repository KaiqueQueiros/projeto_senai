package trevo.agro.api.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record client_date(Long id, @NotBlank String name, @NotBlank @Email String email, @NotBlank String country, @NotBlank String phone) {

}
