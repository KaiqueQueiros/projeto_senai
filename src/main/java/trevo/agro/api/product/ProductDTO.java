package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public record ProductDTO(
        @NotBlank
        String name,
        @JsonFormat(pattern = "YYYY/MM/DD")
        LocalDate date,
        @NotBlank
        String description,
        @NotBlank(message = "É necessario informar neste campo o tamanho da area suportada pelo produto!")
        String area_size,
        @NotNull
        URL img,
        @NotNull
        @JsonProperty("categories")
        List<Long> categoryIds,
        @NotNull
        @JsonProperty("cultures")
        List<Long> cultureIds
) {
}


