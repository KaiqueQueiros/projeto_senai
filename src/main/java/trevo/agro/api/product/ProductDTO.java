package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public record ProductDTO(
        @NotBlank
        String name,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate date,
        @NotBlank String description,
        @NotBlank String area_size,
        @NotNull URL img,
        @NotNull
        @JsonProperty("categories")
        List<Long> categoryIds,
        @NotNull
        @JsonProperty("cultures")
        List<Long> cultureIds
) {
}


