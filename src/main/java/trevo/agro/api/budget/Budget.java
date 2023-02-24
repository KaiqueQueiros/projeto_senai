package trevo.agro.api.budget;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro.api.product.Product;

import java.time.LocalDate;
import java.util.List;

@Table(name = "tb_budget")
@Entity(name = "budget")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "country")
    private String country;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "company")
    private String company;
    @ManyToMany
    @JoinTable
            (
                    name = "TB_BUDGET_PRODUCT",
                    joinColumns = {@JoinColumn(name = "budget_id",referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "product_id",referencedColumnName = "id")}
            )
    private List<Product> products;

    public Budget(BudgetDTO dto,List<Product> products) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.country = dto.country();
        this.date = LocalDate.now();
        this.company = dto.company();
        this.products = products;
    }
    public void update(BudgetDTO dto,List<Product>products){
        if (dto.name() != null){
            this.name = dto.name();
        }
        if (dto.email() != null){
            this.email = dto.email();
        }
        if (dto.phone() != null){
            this.phone = dto.phone();
        }
        if (dto.country() != null){
            this.country = dto.country();
        }  if (dto.company() != null){
            this.company = dto.company();
        }
        if (products != null){
            this.products = products;
        }

    }


}



