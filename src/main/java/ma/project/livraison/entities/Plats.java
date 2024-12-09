package ma.project.livraison.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor

@Entity
public class Plats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;
    private String image;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public Plats(Long id, String name, Double price, String description, String image, Double rating, Category category, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }
    // Getters and Setters

    public String getName() {
        return name;
    }
}