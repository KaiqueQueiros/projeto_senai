package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public record ProductDTO(
        @NotBlank String name,
        LocalDateTime date,
        @NotBlank String culture,
        @NotBlank String description,
        @NotBlank String area_size,
        @NotNull URL img,
        @JsonProperty("categories")
        List<Long> categoryIds
) {
}

