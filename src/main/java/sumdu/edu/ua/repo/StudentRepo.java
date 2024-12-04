package sumdu.edu.ua.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sumdu.edu.ua.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
    
}
