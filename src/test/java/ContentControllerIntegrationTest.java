import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sumdu.edu.ua.ApplicationStarter;
import sumdu.edu.ua.model.Content;
import sumdu.edu.ua.model.Student;
import sumdu.edu.ua.repo.ContentRepo;
import sumdu.edu.ua.repo.StudentRepo;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationStarter.class)
@AutoConfigureMockMvc
public class ContentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentRepo contentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void testGetScoresByStudentId() throws Exception {
        // Попередня підготовка даних
        Student student = new Student(1, "John", "Doe", 20, "john.doe@gmail.com", "CS-101", "Computer Science");
        studentRepo.save(student);

        Content content = new Content(1, student.getId(), "Math", "A", 90);
        contentRepo.save(content);

        // Виконання запиту
        mockMvc.perform(get("/UserContent").param("id2", String.valueOf(student.getId())))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("scores"))
                .andExpect(model().attribute("scores", hasSize(1)))
                .andExpect(model().attribute("scores", hasItem(
                        hasProperty("title", is("Math"))
                )));
    }
}
