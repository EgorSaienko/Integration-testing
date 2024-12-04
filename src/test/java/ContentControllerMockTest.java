import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sumdu.edu.ua.ApplicationStarter;
import sumdu.edu.ua.model.Content;
import sumdu.edu.ua.repo.ContentRepo;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationStarter.class)
public class ContentControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentRepo contentRepo; // Mock-об'єкт для репозиторію

    @Test
    public void testGetScoresWithMockRepo() throws Exception {
        // Імітація повернення даних з бази
        Content mockContent = new Content(1, 1, "Math", "A", 95);
        when(contentRepo.findAllByStudentId(1)).thenReturn(Collections.singletonList(mockContent));

        // Виконання запиту до контролера
        mockMvc.perform(get("/UserContent").param("id2", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("scores"))
                .andExpect(model().attribute("scores", hasSize(1)))
                .andExpect(model().attribute("scores", hasItem(
                        hasProperty("title", is("Math"))
                )));

        // Перевірка, що метод репозиторію був викликаний один раз
        verify(contentRepo, times(1)).findAllByStudentId(1);
    }
}
