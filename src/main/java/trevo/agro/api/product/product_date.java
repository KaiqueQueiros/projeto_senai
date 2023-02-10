package trevo.agro.api.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDateTime;
public record product_date(
        @NotBlank String name,
        @NotBlank String area_size,
        @NotBlank String description,
        //Campo date esta configurado para ser o atual da criação.
        LocalDateTime date,
        @NotBlank String culture,
        @NotNull URL img
)
{

}
