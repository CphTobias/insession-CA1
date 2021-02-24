/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.JokeDTO;
import entities.Joke;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;

/**
 *
 * @author PC
 */
public class FacadeJokeTest {
    
    private static EntityManagerFactory emf;
    private static FacadeJoke facade;

    private Joke j1;
    private Joke j2;
    private Joke j3;
    public FacadeJokeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = FacadeJoke.getFacadeJoke(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            
            
            j1=new Joke("First Joke","from the net","riddle",2);em.persist(j1);
            j2=new Joke("Second Joke","from the net","riddle",8);em.persist(j2);
            j3=new Joke("Third Joke","selfmade","dirty",6);em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        JokeDTO excepted = facade.create(new JokeDTO("create", "somewhere", "bad", 9));
        JokeDTO actual = facade.getById(excepted.getId());
        assertEquals(excepted, actual);
    }

    @Test
    public void testGetById() {
        Long id = j2.getId();
        JokeDTO actual = facade.getById(id);
        JokeDTO excepted = new JokeDTO(j2);
        assertEquals(excepted, actual);
           }
    
    @Test
    public void testGetAll(){
        List<JokeDTO> jokes=facade.getAllJokes();
        assertEquals(3, jokes.size());
        assertTrue(jokes.contains(new JokeDTO(j1)));
        assertTrue(jokes.contains(new JokeDTO(j2)));
        assertTrue(jokes.contains(new JokeDTO(j3)));
    }
    
    
}
