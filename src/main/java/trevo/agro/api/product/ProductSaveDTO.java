package trevo.agro.api.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProductSaveDTO(

        String name,
        String description,
        String areaSize,
        @JsonProperty("categories")
        List<Long> categoryIds,
        @JsonProperty("cultures")
        List<Long> cultureIds,
        @JsonProperty("images")
        List<Long> imageIds
) {
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAreaSize() {
        return areaSize;
    }


}

