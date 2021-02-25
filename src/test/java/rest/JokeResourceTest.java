package rest;

import dtos.JokeDTO;
import entities.Joke;
import entities.RenameMe;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class JokeResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    
    private Joke j1;
    private Joke j2;
    private Joke j3;
    
    private JokeDTO j1d;
    private JokeDTO j2d;
    private JokeDTO j3d;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        j1 = new Joke("First Joke", "from the net", "riddle", 2);
        j2 = new Joke("Second Joke", "from the net", "riddle", 8);
        j3 = new Joke("Third Joke", "selfmade", "dirty", 6);
       
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(j1);
            em.persist(j2);
            em.persist(j3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        j1d=new JokeDTO(j1);
        j2d=new JokeDTO(j2);
        j3d=new JokeDTO(j3);
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/xxx").then().statusCode(200);
    }

   
    @Test
    public void testgetById() throws Exception {
        given()
                .contentType("application/json")
                .get("/joke/"+j2.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("theJoke", equalTo("Second Joke"))
                .body("reference", equalTo("from the net"))
                .body("type", equalTo("riddle"))
                .body("rating", equalTo(8));
    }
 @Test
    public void testgetall() throws Exception {
        given()
                .contentType("application/json")
                .get("/joke/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("", hasSize(3));
    }

    @Test
    public void testgetRandom() throws Exception {
        given()
                .contentType("application/json")
                .get("/joke/random").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
                
    }
    
}
