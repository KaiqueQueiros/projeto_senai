package trevo.agro.api.image;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

import java.net.URL;


@Table(name = "tb_image")
@Entity(name = "Image")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private URL img ;

    public Image(image_data dados) {
        this.name = dados.name();
        this.img = dados.img();

    }
}