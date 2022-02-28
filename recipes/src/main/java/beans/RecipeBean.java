package beans;

import com.mysql.jdbc.Connection;
import entities.Recipe;
import entities.ReturnSprout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import recipes.ConnectionFactory;

/**
 *
 * @author Zander Koch
 */
@Stateless
public class RecipeBean{
    
    /**
     * gets all recipes
     * @return a sprout containing the recipes resulting from the database query
     * and a status code
     */
    public ReturnSprout getRecipes(){
        try(Connection connection = ConnectionFactory.getConnection()){
            ArrayList<Recipe> recipes = new ArrayList();
            
            String sql = "SELECT * FROM recipe";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            if(!result.first()){
                return new ReturnSprout("no recipes found"
                        ,Response.Status.NOT_FOUND);
            }
            
        }
        catch(Exception e){
            System.out.println("RecipeBean.getRecipes():" + e);
        }
    }
    
    /**
     * returns an arraylist of recepies with the basic attributes that can be
     * gotten from 
     * @param resultSet
     * @return an arraylist of recipes with id, author, title, and description
     */
    private static ArrayList<Recipe> getBasicRecipeList(ResultSet resultSet){
        
    }
}
