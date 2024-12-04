package sumdu.edu.ua.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sumdu.edu.ua.repo.ContentRepo;
import sumdu.edu.ua.repo.StudentRepo;

import sumdu.edu.ua.model.Content;
import sumdu.edu.ua.model.Student;


@Controller
public class ContentController {

    List<Content> scores;
    ApplicationContext factory;

    @Autowired
    public StudentRepo srepo;

    @Autowired
    public ContentRepo scorepo;

    @RequestMapping(value = "UserContent", method = RequestMethod.GET)
    public ModelAndView formContent(
            @RequestParam("id2") String id2,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, SQLException {

        ModelAndView modelNview = new ModelAndView();
        modelNview.setViewName("score");
        ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
        Student st = srepo.getOne(Integer.parseInt(id2));
        modelNview.addObject("user", st);
        scores = scorepo.getScoresByStId(st.getId());
        modelNview.addObject("scores", scores);
        return modelNview;
    }

    // Обробка оновлення оцінки
    @RequestMapping(value = "updateScore", method = RequestMethod.POST)
    public String updateScore(
            @RequestParam("id") int id,
            @RequestParam("scoreNum") int scoreNum,  // Змінено тип на int
            @RequestParam("scoreLetter") String scoreLetter) {

        try {
            scorepo.updateScore(id, scoreNum, scoreLetter); // Виклик оновленого методу репозиторію
            return "redirect:/UserContent?id2=" + id; // Повернення до списку оцінок
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Перенаправлення на сторінку помилки
        }
    }


    // Обробка видалення оцінки
    @RequestMapping(value = "deleteScore", method = RequestMethod.POST)
    public String deleteScore(@RequestParam("id") int id) {
        try {
            scorepo.deleteScore(id);
            return "redirect:/UserContent?id2=" + id; // Повернення до списку оцінок
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Перенаправлення на сторінку помилки
        }
    }
}

