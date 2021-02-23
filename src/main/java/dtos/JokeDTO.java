
package dtos;

import entities.Joke;
import entities.RenameMe;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jens
 */
public class JokeDTO {

    private Long id;
    private String theJoke;
    private String reference;
    private String type;
    private int rating;

    public JokeDTO(Long id, String theJoke, String reference, String type, int rating) {
        this.id = id;
        this.theJoke = theJoke;
        this.reference = reference;
        this.type = type;
        this.rating = rating;
    }

    public JokeDTO(String theJoke, String reference, String type, int rating) {
        this.theJoke = theJoke;
        this.reference = reference;
        this.type = type;
        this.rating = rating;
    }

    public JokeDTO(Joke j) {
        id = j.getId();
        theJoke = j.getTheJoke();
        reference = j.getReference();
        type = j.getType();
        rating = j.getRating();
    }
    
    public static List<JokeDTO> getDtos(List<Joke> jokes){
        List<JokeDTO> jokedtos = new ArrayList();
        jokes.forEach(joke->jokedtos.add(new JokeDTO(joke)));
        return jokedtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheJoke() {
        return theJoke;
    }

    public void setTheJoke(String theJoke) {
        this.theJoke = theJoke;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "JokeDTO{" + "id=" + id + ", theJoke=" + theJoke + ", reference=" + reference + ", type=" + type + ", rating=" + rating + '}';
    }
    
}
