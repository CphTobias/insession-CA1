package facades;

import dtos.CarDTO;
import entities.Car;
import utils.EMF_Creator;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarFacadeTest {

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    public CarFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(new Car("Tesla", 2020, "Model Roadster", 1250000, 15));
            em.persist(new Car("Ford", 2015, "Model Mondeo", 1250000, 19));

            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getCarCount(), "Expects two rows in the database");
    }
    
    @Test
    public void Create() {
        
        facade.create(new CarDTO(new Car("Sedan", 2012, "Camry", 50000, 4)));
        int exp = 3;
        int result = facade.getAll().size();
        
        assertEquals(exp, result);
    }
    
    @Test
    public void getCarsByManufacturer() {
        
        String input = "Tesla";
        String exp = "Tesla";
        List<CarDTO> resultList = facade.getCarsByManufacturer(input);
        String result = resultList.get(0).getManufacturer();
        assertEquals(exp, result);
    }

}
