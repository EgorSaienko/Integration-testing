package sumdu.edu.ua.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import sumdu.edu.ua.ApplicationStarter;
import sumdu.edu.ua.repo.ContentRepo;
import sumdu.edu.ua.model.Content;
import org.springframework.security.core.userdetails.InMemoryUserDetailsManager;  // Доданий імпорт

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ApplicationStarter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test") // Використовувати окремий профіль для тестів
public class UpdateScoreIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentRepo contentRepo;

    @Test
    public void testUpdateScore() throws Exception {
        // Підготовка даних
        Content content = new Content(null, 1, "History", "B", 80);
        contentRepo.save(content);

        Integer savedId = content.getId(); // Отримання збереженого ID

        // Оновлення даних
        mockMvc.perform(post("/updateScore")
                        .param("id", String.valueOf(savedId))
                        .param("scoreNum", "95")
                        .param("scoreLetter", "A")
                        .header("Authorization", "Basic " + getBasicAuthHeader("admin", "1234"))) // Логін і пароль, який існує в базі
                .andExpect(status().is3xxRedirection());

        // Перевірка, що зміни були застосовані
        Content updatedContent = contentRepo.findById(savedId).orElse(null);
        assertNotNull(updatedContent);
        assertEquals(95, updatedContent.getScore_num());
        assertEquals("A", updatedContent.getScore_l());
    }

    private String getBasicAuthHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        return "Basic " + new String(encodedAuth);
    }
}
