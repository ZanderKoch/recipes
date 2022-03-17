package resources;

import beans.RecipeBean;
import entities.Recipe;
import entities.ReturnSprout;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
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
        ReturnSprout sprout = recipeBean.getRecipes();
        return Response.status(sprout.getStatus())
                .entity(sprout.getEntity())
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRecipe(Recipe recipe){
        ReturnSprout sprout = recipeBean.addRecipe(recipe);
        return Response.status(sprout.getStatus())
                .entity(sprout.getEntity())
                .build();
    }
}
