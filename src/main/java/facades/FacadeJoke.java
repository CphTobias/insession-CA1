
package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jens
 */
public class FacadeJoke {
    private static FacadeJoke instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeJoke() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeJoke getFacadeJoke(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeJoke();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public JokeDTO create(JokeDTO jdto){
        Joke joke = new Joke(jdto.getId(),jdto.getTheJoke(),jdto.getReference(),jdto.getType(),jdto.getRating());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(joke);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new JokeDTO(joke);
    }
    
    public JokeDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        Joke j=em.find(Joke.class, id);
        //if not found return something , not null
        if(j==null){return new JokeDTO(null,null,null,null,0);} 
        return new JokeDTO(j);
    }
    
    public List<JokeDTO> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j", Joke.class);
        List<Joke> jokes = query.getResultList();
        return JokeDTO.getDtos(jokes);
    }

}
