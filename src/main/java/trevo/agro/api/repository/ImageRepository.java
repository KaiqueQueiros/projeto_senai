package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.image.Image;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findByIdIn(List<Long> images);
    Boolean existsByName(String name);



}
