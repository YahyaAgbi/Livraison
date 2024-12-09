package ma.project.livraison.services;

import ma.project.livraison.entities.Plats;
import ma.project.livraison.repositories.PlatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatsServiceImpl implements PlatsService {

    @Autowired
    private PlatsRepository platsRepository;

    @Override
    public List<Plats> getAllPlats() {
        return platsRepository.findAll();
    }

    @Override
    public Optional<Plats> getPlatsById(Long id) {
        return platsRepository.findById(id);
    }

    @Override
    public List<Plats> getPlatsByCategory(Long categoryId) {
        return platsRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Plats> getPlatsByName(String name) {
        return platsRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Plats savePlats(Plats plats) {
        return platsRepository.save(plats);
    }

    @Override
    public void deletePlats(Long id) {
        platsRepository.deleteById(id);
    }
}