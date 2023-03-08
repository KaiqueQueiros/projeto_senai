package trevo.agro.api.image;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

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
    @Column(name = "name",length = 30)
    private String name;
    @Column(name = "type",length = 30)
    private String type;
    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;


}
