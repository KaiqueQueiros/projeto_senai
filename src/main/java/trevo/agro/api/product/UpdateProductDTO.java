package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;

import java.net.URL;
import java.util.List;

public record UpdateProductDTO(
        @NotNull
        Long id,
        // @NotBlank(message = "É necessário informar o nome do produto")
        String name,
        //@NotBlank(message = "É necessário informar a descrição do produto")
        String description,
        // @NotBlank(message = "É necessário informar qual a area suportada do produto")
        String area_size,
        //  @NotNull(message = "É necessário introduzir ao menos uma imagem")
        URL img,
        @JsonProperty("categories")
        List<Category> categories,
        @JsonProperty("cultures")
        List<Culture> cultures
) {

}
