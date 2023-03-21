package trevo.agro.api.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 30,unique = true)
    private String name;
    @Column(name = "type", length = 30)
    private String type;
    @Lob
    @Column(name = "imagedata", length = 1000)
    @JsonIgnore
    private byte[] imageData;


}
