package facades;

import dtos.CarDTO;
import entities.Car;
import dtos.RenameMeDTO;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CarDTO create(CarDTO car){
        Car newCar = new Car(car.getManufacturer(), car.getPrice(), car.getModel(), car.getYear(), car.getQuantity());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newCar);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new CarDTO(newCar);
    }
    public RenameMeDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new RenameMeDTO(em.find(RenameMe.class, id));
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    
    
    public List<RenameMeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<RenameMe> query = em.createQuery("SELECT r FROM RenameMe r", RenameMe.class);
        List<RenameMe> rms = query.getResultList();
        return RenameMeDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        CarFacade fe = getCarFacade(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
