package ma.project.livraison.services;

import ma.project.livraison.entities.Plats;

import java.util.List;
import java.util.Optional;

public interface PlatsService {
    List<Plats> getAllPlats();
    Optional<Plats> getPlatsById(Long id);
    List<Plats> getPlatsByCategory(Long categoryId);
    List<Plats> getPlatsByName(String name);
    Plats savePlats(Plats plats);
    void deletePlats(Long id);
}