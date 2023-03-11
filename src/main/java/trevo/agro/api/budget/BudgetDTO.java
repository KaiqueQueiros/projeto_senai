package trevo.agro.api.budget;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record BudgetDTO(
        @NotBlank(message = "Campo nome é obrigatorio")
        String name,
        @Email(message = "Informe um email valido")
        @NotBlank(message = "Informe o email")
        String email,
        @NotBlank(message = "Informe seu telefone")
        String phone,
        @NotBlank(message = "Informe qual seu país")
        String country,
        @NotBlank(message = "Informe o nome de sua empresa")
        String company,
        @JsonProperty("products")
        @NotEmpty(message = "É necessário informar pelo menos um produto de interesse")
        List<Long> productIds
) {
}
