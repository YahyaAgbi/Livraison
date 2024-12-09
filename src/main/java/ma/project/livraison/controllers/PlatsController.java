package ma.project.livraison.controllers;

import ma.project.livraison.entities.Plats;
import ma.project.livraison.services.PlatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plats")
public class PlatsController {

    @Autowired
    private PlatsService platsService;

    @GetMapping
    public List<Plats> getAllPlats() {
        return platsService.getAllPlats();
    }

    @GetMapping("/{id}")
    public Optional<Plats> getPlatsById(@PathVariable Long id) {
        return platsService.getPlatsById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Plats> getPlatsByCategory(@PathVariable Long categoryId) {
        return platsService.getPlatsByCategory(categoryId);
    }

    @GetMapping("/search")
    public List<Plats> getPlatsByName(@RequestParam String name) {
        return platsService.getPlatsByName(name);
    }

    @PostMapping
    public Plats savePlats(@RequestBody Plats plats) {
        return platsService.savePlats(plats);
    }

    @DeleteMapping("/{id}")
    public void deletePlats(@PathVariable Long id) {
        platsService.deletePlats(id);
    }
}