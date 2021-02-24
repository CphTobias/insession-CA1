package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.StudentDTO;
import utils.EMF_Creator;
import facades.FacadeStudent;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("student")
public class StudentResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeStudent FACADE =  FacadeStudent.getFacadeStudent(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Student\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getStudentCount() {
        long count = FACADE.getStudentCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<StudentDTO> studentDTOs = FACADE.getAll();
        return GSON.toJson(studentDTOs);
    }
        
    @Path("byid/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") long id) {
        StudentDTO student = FACADE.getById(id);        
        return GSON.toJson(student);
    }
    
   
}
