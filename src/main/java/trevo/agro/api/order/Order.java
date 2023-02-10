package trevo.agro.api.order;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;
@Table(name = "tb_order")
@Entity(name = "Order")
@NoArgsConstructor
public class Order{


    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    private LocalDateTime date;




}
