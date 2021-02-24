/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tha
 */
public class StudentDTO {
    private long id;
    private String name;
    private int studentId;
    private String fTvShow;

    public StudentDTO(String name, int studentId, String fTvShow) {
        this.name = name;
        this.studentId = studentId;
        this.fTvShow = fTvShow;
    }
    
    public static List<StudentDTO> getDtos(List<Student> sts){
        List<StudentDTO> stsdtos = new ArrayList();
        sts.forEach(st->stsdtos.add(new StudentDTO(st)));
        return stsdtos;
    }
   
    public StudentDTO(Student st) {
        this.id = st.getId();
        this.name = st.getName();
        this.studentId = st.getStudentId();
        this.fTvShow = st.getfTvShow();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "StudentDTO{" + "id=" + id + ", name=" + name + ", studentId=" + studentId + ", fTvShow=" + fTvShow + '}';
    }

    
    
    
}
