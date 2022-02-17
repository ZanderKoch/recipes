package resources;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * resource that handles calls to GET, POST, UPDATE, and DELETE recipes
 * @author Zander Koch
 */
@Path("recipe")
public class RecipeResource{
    @EJB
    RecipeBean recipeBean;
    
    @GET
    public Response getAllRecipes(){
        
    }
}
