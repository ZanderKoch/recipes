package beans;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entities.Comment;
import entities.Recipe;
import entities.ReturnSprout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
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
            /*TODO: error handleing that returns a sprout with appropriate
            message and status code*/
            
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
            addInstructions(recipe);
            addComments(recipe);
            addStarCount(recipe);
        }        
    }
    
    /**
     * updates provided recipe's ingredients using its id attribute
     * @param recipe recipe to update
     */
    private static void addIngredients(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT text FROM ingredient WHERE recipe_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipe.getId());
            ResultSet result = statement.executeQuery();
            
            //check if any ingredients were found
            if(result.first()){
                result.beforeFirst();
                while(result.next()){
                    recipe.getIngredients().add(result.getString("text"));
                }
            }
            else{
                recipe.getIngredients().add("no ingredients found");
                System.out.println("RecipeBean.AddIngredients(): no ingredients"
                        + "found");
            }
        }
        catch(Exception e){
            System.out.println("RecipeBean.AddIngredients(): " + e);
        }
    }
    
    /**
     * updates provided recipe's instructions using its id attribute
     * @param recipe recipe to update
     */
    public static void addInstructions(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT text FROM instruction WHERE recipe_id = ? "
                    + "ORDER BY step asc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipe.getId());
            ResultSet result = statement.executeQuery();
            
            //check if any ingredients were found
            if(result.first()){
                result.beforeFirst();
                while(result.next()){
                    recipe.getInstructions().add(result.getString("text"));
                }
            }
            else{
                recipe.getInstructions().add("no instructions found");
                System.out.println("RecipeBean.AddInstructions(): no "
                        + "instructions found");
            }
        }
        catch(Exception e){
            System.out.println("RecipeBean.AddInstructions(): " + e);
        }
    }
    
    /**
     * updates provided recipe's comments using its id attribute
     * @param recipe recipe to update
     */
    private static void addComments(Recipe recipe){
        
        CommentBean commentBean = new CommentBean();
        ReturnSprout sprout = commentBean.getComments(recipe.getId());
        
        if(sprout.getStatus() == Response.Status.OK){
            recipe.setComments((ArrayList<Comment>) sprout.getEntity());
        }   
    }
    
    
    /**
     * updates provided recipe's starcount using its id attribute
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
    
    /**
     * attempt to add a recipe to database
     * @param recipe recipe to be added
     * @return returnsprout containing an appropriate status code and message
     */
    public ReturnSprout addRecipe(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO recipe (user_username, description, title)"
                    + " VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql
                    ,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, "test");
            statement.setString(2, recipe.getDescription());
            statement.setString(3, recipe.getTitle());
            statement.execute();
            ResultSet result = statement.getGeneratedKeys();
            
            if(!result.first()){
                System.out.println("RecipeBean.addRecipe: could not add recipe");
                return new ReturnSprout("Recipe could not be added to database"
                   ,Response.Status.INTERNAL_SERVER_ERROR);
            }
            recipe.setId(result.getInt(1));
            
            ReturnSprout ingredientSprout = addIgredientsToDB(recipe);
            if(ingredientSprout.getStatus() != Response.Status.OK){
                return ingredientSprout;
            }
            
            //add instructions to database
            
            return new ReturnSprout("", Response.Status.CREATED);
        }
        catch(SQLException e){
            System.out.println("RecipeBean.addRecipe: " + e);
            return new ReturnSprout("there was an error while connecting to "
                    + "the database", Response.Status.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            System.out.println("RecipeBean.addRecipe: " + e);
            return new ReturnSprout("there was an error while adding recipe"
                    ,Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * attempts to add the ingredients of provided recipe to the database and 
     * returns a ReturnSprout with information about how it went
     * @param recipe
     * @return a ReturnSprout with info on how the process went, status will be
     * OK if successful
     */
    private ReturnSprout addIgredientsToDB(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "INSERT INTO ingredients(recipe_id, text) "
                    + "VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            for(String ingredient : recipe.getIngredients()){
                statement.setInt(1, recipe.getId());
                statement.setString(2, ingredient);
                
                int result = statement.executeUpdate();
                
                if(result != 1){
                    System.out.println("recipeBean.addIngredientsToDB: "
                            + "there was a problem while adding ingredients to "
                            + "the database");
                    return new ReturnSprout("there was a problem while adding "
                            + "ingredients to the database"
                            ,Response.Status.INTERNAL_SERVER_ERROR);
                }
                
            }
            return new ReturnSprout("recipes successfully added"
                    ,Response.Status.OK);
            
        }
        catch(SQLException e){
            System.out.println("RecipeBean.addIgredientsToDB: " + e);
            return new ReturnSprout("there was an error while connecting to "
                    + "the database", Response.Status.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            System.out.println("RecipeBean.addIgredientsToDB: " + e);
            return new ReturnSprout("there was an error while adding recipe"
                    ,Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * attempts to add the ingredients of provided recipe to the database and 
     * returns a ReturnSprout with information about how it went
     * @param recipe
     * @return a ReturnSprout with info on how the process went, status will be
     * OK if successful
     */
    /*private ReturnSprout addInstructionsToDB(Recipe recipe){
        try(Connection connection = ConnectionFactory.getConnection()){
            
        }
        catch(SQLException e){
            System.out.println("RecipeBean.addIgredientsToDB: " + e);
            return new ReturnSprout("there was an error while connecting to "
                    + "the database", Response.Status.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            System.out.println("RecipeBean.addIgredientsToDB: " + e);
            return new ReturnSprout("there was an error while adding recipe"
                    ,Response.Status.INTERNAL_SERVER_ERROR);
        }
    }*/
}
