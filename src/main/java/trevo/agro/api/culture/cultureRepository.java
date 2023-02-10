package trevo.agro.api.culture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import trevo.agro.api.culture.Culture;

public interface cultureRepository extends JpaRepository<Culture,Long> {

}
