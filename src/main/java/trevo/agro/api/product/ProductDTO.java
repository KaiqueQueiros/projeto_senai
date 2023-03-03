package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ProductDTO(
        @NotEmpty(message = "Nome do produto é obrigatorio")
        @NotNull(message = "Nome obrigatorio")
        String name,
        @NotEmpty(message = "Descrição do produto é obrigatorio")
        @NotBlank(message = "Insira a descrição do produto")
        String description,
        @NotEmpty(message = "Tamanho de area é obrigatorio")
        @NotBlank(message = "É necessario informar neste campo o tamanho da area suportada pelo produto!")
        String area_size,
        String img,
        @JsonProperty("categories")
        List<Long> categoryIds,
        @JsonProperty("cultures")
        List<Long> cultureIds
) {
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public String getArea_size() {
        return area_size;
    }

    public String getImg() {
        return img;
    }
}

