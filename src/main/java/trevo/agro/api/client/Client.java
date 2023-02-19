package trevo.agro.api.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "tb_client")
@Entity(name = "Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    private String email;
    private String country;
    private String phone;


    public Client(ClientDate dados) {
        this.name = dados.name();
        this.email = dados.email();
        this.country = dados.country();
        this.phone = dados.phone();

    }

    public void updateDate(UpdateClient dados) {
        if(dados.name() != null) {
            this.name = dados.name();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }

        if(dados.country() != null) {
            this.country = dados.country();
        }
        if(dados.phone() != null) {
            this.phone = dados.phone();
        }

    }
}


