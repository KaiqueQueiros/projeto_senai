package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public record ProductDTO(
        @NotBlank(message = "Insira o nome do produto")
        String name,
        @JsonFormat(pattern = "YYYY/MM/DD")
        LocalDate date,
        @NotBlank(message = "Insira a descrição do produto")
        String description,
        @NotBlank(message = "É necessario informar neste campo o tamanho da area suportada pelo produto!")
        String area_size,
        @NotNull(message = "Insira uma ou mais imagens do produto")
        URL img,
        @JsonProperty("categories")
        List<Long> categoryIds,
        @JsonProperty("cultures")
        List<Long> cultureIds
) {
}


