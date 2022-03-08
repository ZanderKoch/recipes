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
        ArrayList<Recipe> recipes = new ArrayList();
        try(Connection connection = ConnectionFactory.getConnection()){
            
            
            String sql = "SELECT * FROM recipe";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            if(!result.first()){
                System.out.println("RecipeBean.getRecipes(): no recipes found");
                return new ReturnSprout("no recipes found"
                        ,Response.Status.NOT_FOUND);
            }
            
            recipes = getBasicRecipeList(result);
            System.out.println(recipes.size());
            
            addExternals(recipes); 
            System.out.println(recipes.size());
        }
        catch(Exception e){
            System.out.println("RecipeBean.getRecipes():" + e);
        }

        /*I should make a method that validates the recipes list and returns
        a sprout with an error message if it doesn't go through
        */
        return new ReturnSprout(recipes, Response.Status.OK);
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
            resultSet.beforeFirst();
            while(resultSet.next()){
                result.add(new Recipe(
                    resultSet.getInt("id")
                    ,resultSet.getString("user_username")
                    ,resultSet.getString("title")
                    ,resultSet.getString("description")
                ));
                System.out.println("Recipebean.getBasicRecipesList(): title:"
                        + resultSet.getString("title"));
            }
            
        }
        catch(SQLException e){
            System.out.println("Recipebean.getBasicRecipesList(): "
                    + "there was an error while getting basics from ResultSet: "
                    + e);
        }
        return result;
    }
    
    /**
     * returns provided list of recipes with
     * ingredients, instructions, starcount, and comments added 
     * @param recipes recipes to add externals to
     * @return same ArrayList as provided but with externals added
     * @TODO add methods for adding ingredients, instructions and comments
     */
    private static void addExternals(ArrayList<Recipe> recipes){
        
        for(Recipe recipe : recipes){
            addIngredients(recipe);
            //addInstructions(recipe);
            //addComments;
            addStarCount(recipe);
        }        
    }
    
    /**
     * updates provided recipe's ingredients using its <code>id</code> attribute
     * @param recipe recipe to update
     */
    private static void addIngredients(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT text FROM ingredient WHERE recipe_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipe.getId());
        }
        catch(Exception e){
            System.out.println("RecipeBean.AddIngredients():" + e);
        }
    }
    
    
    /**
     * updates provided recipe's starcount using its <code>id</code> attribute
     * @param recipe recipe to update
     */
    private static void addStarCount(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT COUNT(*) FROM star WHERE recipe_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipe.getId());
            ResultSet result = statement.executeQuery();
            
            //check if any stars were found
            if(result.first()){
                recipe.setStarCount(result.getInt(1));
            }
            else{
                recipe.setStarCount(0);
            }
            
            recipe.setStarCount(result.getInt(1));
        }
        catch(Exception e){
            System.out.println("RecipeBean.AddStarCount():" + e);
        }
    }
}
