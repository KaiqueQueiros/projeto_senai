package trevo.agro.api.budget;

import trevo.agro.api.product.Product;

import java.time.LocalDate;
import java.util.List;

public record DetailsBudget(Long id, String name, String email, String phone, String country, LocalDate date, List<Product> products) {
    public DetailsBudget(Budget budget) {
        this((long) budget.getId(),
                    budget.getName(),
                    budget.getEmail(),
                    budget.getPhone(),
                    budget.getCountry(),
                    budget.getDate(),
                    budget.getProducts());
    }
}
