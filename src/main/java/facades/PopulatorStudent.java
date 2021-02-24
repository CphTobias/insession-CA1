package facades;
import dtos.StudentDTO;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author pra
 */
@Startup
public class PopulatorStudent {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        FacadeStudent fs = FacadeStudent.getFacadeStudent(emf);
        fs.create(new StudentDTO("Jens Gelbeck", 101, "Den store bagedyst"));
        fs.create(new StudentDTO("Rasmus Ditlev Hansen", 102, "Badehotellet"));
        fs.create(new StudentDTO("Tobias Zimmermann", 103, "Muppet Show"));
        fs.create(new StudentDTO("Peter Andersen", 104, "Simpsons"));        
    }
    
    public static void main(String[] args) {
        populate();
    }
}
