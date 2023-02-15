package trevo.agro.api.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tb_client")
@Entity(name = "Client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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
        if (dados.name() != null) {
            this.name = dados.name();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.country() != null) {
            this.country = dados.country();
        }
        if (dados.phone() != null) {
            this.phone = dados.phone();
        }

    }
}


