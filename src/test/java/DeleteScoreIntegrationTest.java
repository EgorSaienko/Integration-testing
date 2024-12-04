import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sumdu.edu.ua.ApplicationStarter;
import sumdu.edu.ua.repo.ContentRepo;
import sumdu.edu.ua.model.Content;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApplicationStarter.class)
@AutoConfigureMockMvc
public class DeleteScoreIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentRepo contentRepo;

    @Test
    public void testDeleteScore() throws Exception {
        Content content = new Content(null, 1, "Biology", "C", 70); // null дозволений
        contentRepo.save(content);

        Integer savedId = content.getId(); // Отримайте збережений ідентифікатор

        // Видалення даних
        mockMvc.perform(post("/deleteScore").param("id", String.valueOf(savedId)))
                .andExpect(status().is3xxRedirection());

        Optional<Content> deletedContent = contentRepo.findById(savedId);
        assertTrue(deletedContent.isEmpty());

    }
}
