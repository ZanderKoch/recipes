package beans;

import com.mysql.jdbc.Connection;
import entities.Recipe;
import entities.ReturnSprout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            recipes = getBasicRecipeList(result);
            
        }
        catch(Exception e){
            System.out.println("RecipeBean.getRecipes():" + e);
        }
    }
    
    /**
     * returns an arraylist of recepies with the basic attributes that can be
     * gotten from recipe table
     * @param resultSet ResultSet with columns:
     * id, author, title, and description
     * @return an arraylist of recipes with id, author, title, and description
     */
    private static ArrayList<Recipe> getBasicRecipeList(ResultSet resultSet){
        ArrayList<Recipe> result = new ArrayList();
        try{
            while(resultSet.next()){
                result.add(new Recipe(
                    resultSet.getInt("id")
                    ,resultSet.getString("author")
                    ,resultSet.getString("title")
                    ,resultSet.getString("description")
                ));
            }
        }
        catch(SQLException e){
            System.out.println("Recipebean.getBasicRecipesList(): "
                    + "there was an error while getting basics from ResultSet: "
                    + e);
        }
        finally{
            return result;
        }
    }
    
    /**
     * returns provided list of recipes with
     * ingredients, instructions, starcount, and comments added 
     * @param recipes recipes to add externals to
     * @return same ArrayList as provided but with externals added
     * @TODO add methods for adding ingredients, instructions and comments
     */
    private static ArrayList<Recipe> addExternals(ArrayList<Recipe> recipes){
        ArrayList<Recipe> result = new ArrayList();
        for(Recipe recipe : recipes){
            //addIngredients(recipe);
            //addInstructions(recipe);
            addStarCount(recipe);
        }
    }
    
    }
}
