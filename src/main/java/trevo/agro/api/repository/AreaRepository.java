package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.area.Area;
import java.util.List;
@Repository
public interface AreaRepository extends JpaRepository <Area,Long> {
    List<Area> findByIdIn (List<Long> areasIds);
}
