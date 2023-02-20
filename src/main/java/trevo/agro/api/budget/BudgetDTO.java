package trevo.agro.api.budget;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BudgetDTO(
                        Long id,
                        @NotBlank(message = "Por favor informe seu nome!")
                        String name,
                        @NotNull(message = "Informe seu email para contato!")
                        @Email
                        String email,
                        @NotBlank(message = "Informe seu telefone para contato!")
                        String phone,
                        @NotNull(message = "Informe seu pais")
                        String country,
                        @NotNull(message = "Informe o nome da sua empresa")
                        String company,
                        @NotNull
                        @JsonProperty("products")
                        List<Long> productIds
)


{

}
