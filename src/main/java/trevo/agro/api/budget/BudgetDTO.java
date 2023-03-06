package trevo.agro.api.budget;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

import java.util.List;

public record BudgetDTO(

        String name,

        @Email
        String email,

        String phone,

        String country,

        String company,
        @JsonProperty("products")
        List<Long> productIds
) {
        public String getName() {
                return name;
        }
        public String getEmail() {
                return email;
        }

        public String getPhone() {
                return phone;
        }

}
