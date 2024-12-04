package sumdu.edu.ua.model;
import jakarta.persistence.*;


@Entity
@Table(name="scores")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Змінено на IDENTITY
    @Column(name="id")
    private Integer id;
    @Column(name="stud_id")
    private int stud_id;
    @Column(name="Title")
    private String title;
    @Column(name="mark_let")
    private String score_l;
    @Column(name="mark_num")
    private int score_num;

    public Content(){}

    public Content(Integer id, int stud_id, String title, String score_l, int score_num) {
        this.id = id;
        this.stud_id = stud_id;
        this.title = title;
        this.score_l = score_l;
        this.score_num = score_num;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getScore_l() {
        return score_l;
    }

    public int getScore_num() {
        return score_num;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScore_l(String score_l) {
        this.score_l = score_l;
    }

    public void setScore_num(int score_num) {
        this.score_num = score_num;
    }

    public int getStud_id() {
        return stud_id;
    }
}
