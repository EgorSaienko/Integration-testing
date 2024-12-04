package sumdu.edu.ua.controller;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import sumdu.edu.ua.repo.StudentRepo;
import sumdu.edu.ua.model.Student;


@RestController
public class StudentController {
    List<Student> students;
    ApplicationContext factory;
    
    @Autowired
    StudentRepo srepo;
    
   
        @ModelAttribute
        public void modelData(Model m){
            if(students==null){ students = new LinkedList<Student>();}
            factory = new ClassPathXmlApplicationContext("/spring.xml");
        }
        
        @GetMapping("/")
	public ModelAndView home(Model m) {
            ModelAndView mav = new ModelAndView("student");
            m.addAttribute("students", srepo.findAll());
                return mav;
	}
        
        @RequestMapping(value = "/StudentAdd")
        public ModelAndView addStudent(HttpServletRequest request,Model m){
            
            System.out.println("accesed by "+ request.getParameter("name")+" token:"+request.getParameter("_csrf"));
        if (request.getParameter("name") != "" && request.getParameter("surname") != "") {
        Student student = (Student)factory.getBean("Student");
        
            student.setName(request.getParameter("name"));
            student.setSurname(request.getParameter("surname"));
            student.setAge(Integer.parseInt(request.getParameter("age")));
            student.setEmail(request.getParameter("email"));
            student.setGroup(request.getParameter("group"));
            student.setFaculty(request.getParameter("faculty"));
        
        srepo.save(student);
        }
        students=srepo.findAll();
    
        m.addAttribute("students", students);
        return new ModelAndView("student");
    }
        
}
    

