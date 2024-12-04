import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sumdu.edu.ua.ApplicationStarter;
import sumdu.edu.ua.model.Student;
import sumdu.edu.ua.repo.StudentRepo;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationStarter.class)
@AutoConfigureMockMvc
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepo studentRepo;

    @Test
    public void testAddStudent() throws Exception {
        mockMvc.perform(post("/StudentAdd")
                        .param("name", "Jane")
                        .param("surname", "Smith")
                        .param("age", "22")
                        .param("email", "jane.smith@gmail.com")
                        .param("group", "CS-101")
                        .param("faculty", "Computer Science"))
                .andExpect(status().isOk());

        List<Student> students = studentRepo.findAll();
        assertThat(students, hasItem(
                hasProperty("name", is("Jane"))
        ));
    }
}
