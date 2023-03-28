package trevo.agro.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.dto.BudgetDTO;
import trevo.agro.api.exceptions.models.BadRequestException;
<<<<<<< HEAD:src/main/java/trevo/agro/api/models/Budget.java

=======
import trevo.agro.api.product.Product;
>>>>>>> master:src/main/java/trevo/agro/api/budget/Budget.java
import java.time.LocalDate;
import java.util.List;

@Table(name = "tb_budget")
@Entity(name = "Budget")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",nullable = false)
    @Length(max = 50)
    private String name;
    @Column(name = "email",nullable = false)
    @Length(max = 50)
    private String email;
    @Column(name = "phone",nullable = false)
    @Length(max = 50)
    private String phone;
    @Column(name = "country",nullable = false)
    @Length(max = 50)
    private String country;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "company",nullable = false)
    @Length(max = 100)
    private String company;
    @ManyToMany
    @JoinTable
            (
            name = "TB_BUDGET_PRODUCT",
            joinColumns = {@JoinColumn(name = "budget_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
            )
    private List<Product> products;

    public Budget(BudgetDTO dto, List<Product> products) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.country = dto.country();
        this.date = LocalDate.now();
        this.company = dto.company();
        if (products.isEmpty()){
            throw new BadRequestException("É necessário informar pelo menos um produto de interesse");
        }
        this.products = products;
    }

    public Budget(BudgetDTO budgetDTO) {
        this.name = budgetDTO.name();
        this.email = budgetDTO.email();
        this.phone = budgetDTO.phone();
        this.country = budgetDTO.country();
        this.company = budgetDTO.company();
    }

    public void update(BudgetDTO dto, List<Product> products) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();
        }
        if (dto.country() != null) {
            this.country = dto.country();
        }
        if (dto.company() != null) {
            this.company = dto.company();
        }
        if (products != null) {
            this.products = products;
        }
    }

}



