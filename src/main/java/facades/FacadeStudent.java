package facades;
import dtos.StudentDTO;
import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class FacadeStudent {

    private static FacadeStudent instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeStudent() {}
    
  
    public static FacadeStudent getFacadeStudent(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeStudent();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public StudentDTO create(StudentDTO st){
        Student sts = new Student(st.getName(), st.getStudentId(), st.getfTvShow());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sts);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new StudentDTO(sts);
    }
    
    
    public StudentDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new StudentDTO(em.find(Student.class, id));
    }
    
    public long getStudentCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long studentCount = (long)em.createQuery("SELECT COUNT(r) FROM Student r").getSingleResult();
            return studentCount;
        }finally{  
            em.close();
        }
    }
   
    public List<StudentDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT r FROM Student r", Student.class);
        List<Student> sts = query.getResultList();
        return StudentDTO.getDtos(sts);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeStudent fs = getFacadeStudent(emf);
        fs.getAll().forEach(dto->System.out.println(dto));
    }

}
