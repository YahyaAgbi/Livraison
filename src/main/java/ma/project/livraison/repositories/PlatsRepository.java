package ma.project.livraison.repositories;

import ma.project.livraison.entities.Plats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatsRepository extends JpaRepository<Plats, Long> {
    List<Plats> findByCategoryId(Long categoryId);
    List<Plats> findByNameContainingIgnoreCase(String name);
}