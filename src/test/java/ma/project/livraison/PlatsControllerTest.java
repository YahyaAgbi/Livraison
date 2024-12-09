package ma.project.livraison;

import ma.project.livraison.entities.Category;
import ma.project.livraison.entities.Plats;
import ma.project.livraison.repositories.CategoryRepository;
import ma.project.livraison.services.PlatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = LivraisonApplication.class)
@AutoConfigureMockMvc
public class PlatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlatsService platsService;

    @Autowired
    private CategoryRepository categoryRepository;

    private Plats plats;
    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category(1L, "Entrées", "Description", new Date(), new Date(), new ArrayList<>());
        // Save the category entity to the database
        categoryRepository.save(category);

        plats = new Plats(1L, "Salad", 10.0, "Fresh salad", "image.jpg", 4.5, category, new Date(), new Date());
    }

    @Test
    public void testGetAllPlats() throws Exception {
        Mockito.when(platsService.getAllPlats()).thenReturn(Arrays.asList(plats));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(plats.getName())));
    }

    @Test
    public void testGetPlatsById() throws Exception {
        Mockito.when(platsService.getPlatsById(1L)).thenReturn(Optional.of(plats));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plats/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(plats.getName())));
    }

    @Test
    public void testGetPlatsByCategory() throws Exception {
        Mockito.when(platsService.getPlatsByCategory(1L)).thenReturn(Arrays.asList(plats));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plats/category/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(plats.getName())));
    }

    @Test
    public void testGetPlatsByName() throws Exception {
        Mockito.when(platsService.getPlatsByName("Salad")).thenReturn(Arrays.asList(plats));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/plats/search")
                .param("name", "Salad")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(plats.getName())));
    }

    @Test
    public void testSavePlats() throws Exception {
        Mockito.when(platsService.savePlats(Mockito.any(Plats.class))).thenReturn(plats);

        String platsJson = "{\"name\":\"Salad\",\"price\":10.0,\"description\":\"Fresh salad\",\"image\":\"image.jpg\",\"rating\":4.5,\"category\":{\"id\":1}}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/plats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(platsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(plats.getName())));
    }

    @Test
    public void testDeletePlats() throws Exception {
        Mockito.doNothing().when(platsService).deletePlats(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/plats/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}