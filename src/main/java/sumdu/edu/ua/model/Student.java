package sumdu.edu.ua.model;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "age")
    private int age;
    @Column(name = "email")
    private String email;
    @Column(name = "group_")
    private String group;
    @Column(name = "faculty")
    private String faculty;
    public void setId(int id) {
        this.id = id;
    }
    
    public Student(){}
    
    public Student(int id, String name, String surname,int age, String email, String group, String faculty) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.age=age;
        this.email = email;
        this.group = group;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
   
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGroup() {
        return group;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    
    public String getFaculty() {
        return faculty;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
}