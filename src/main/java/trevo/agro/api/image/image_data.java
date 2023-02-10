package trevo.agro.api.image;

import jakarta.validation.constraints.NotBlank;

import java.net.URL;

public record image_data(Long id, @NotBlank String name, URL img) {
}
