package sumdu.edu.ua.repo;


import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sumdu.edu.ua.model.Content;
import sumdu.edu.ua.model.Student;

@Repository
public interface ContentRepo extends JpaRepository<Content, Integer> {
    @Query("from Content where stud_id= :id")
    List<Content> getScoresByStId(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("update Content c set c.score_num = :scoreNum, c.score_l = :scoreLetter where c.id = :id")
    void updateScore(@Param("id") int id, @Param("scoreNum") int scoreNum, @Param("scoreLetter") String scoreLetter);

    @Transactional
    @Modifying
    @Query("delete from Content where id = :id")
    void deleteScore(@Param("id") int id);
}


