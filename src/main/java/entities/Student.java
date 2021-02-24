package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Student.deleteAllRows", query = "DELETE from Student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int studentId;
    private String fTvShow;
    
    
    public Student() {}  

    public Student(String name, int studentId, String fTvShow) {
        this.name = name;
        this.studentId = studentId;
        this.fTvShow = fTvShow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getfTvShow() {
        return fTvShow;
    }

    public void setfTvShow(String fTvShow) {
        this.fTvShow = fTvShow;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", studentId=" + studentId + ", fTvShow=" + fTvShow + '}';
    }
    
    
    

   
}
