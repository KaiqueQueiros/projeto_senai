package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.models.Culture;

import java.util.List;

@Repository
public interface  CultureRepository extends JpaRepository<Culture, Long> {
    List<Culture> findByIdIn(List<Long> cultures);

    Boolean existsByName(String name);

}
