package trevo.agro.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ProductSaveDTO(

        @NotEmpty(message = "Nome de produto obrigatorio")
        String name,
        @NotEmpty(message = "Descrição de produto obrigatoria")
        String description,
        @JsonProperty("areas")
        @NotEmpty(message = "Selecione a area")
        List<Long> areasIds,
        @JsonProperty("categories")
        @NotEmpty(message = "Informe a categoria do produto")
        List<Long> categoryIds,
        @JsonProperty("cultures")
        @NotEmpty(message = "Informe a cultura do produto")
        List<Long> cultureIds,
        @JsonProperty("images")
        @NotEmpty(message = "Informe a imagem do produto")
        List<Long> imageIds
) {

}

