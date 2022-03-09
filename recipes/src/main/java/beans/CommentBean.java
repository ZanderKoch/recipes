package beans;

import com.mysql.jdbc.Connection;
import entities.Comment;
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
public class CommentBean{

    /**
     * gets all comments belonging to a recipe with the given id
     *
     * @param id id of the recipe
     * @return a returnsprout containing an arraylist of all the comments
     */
    public ReturnSprout getComments(int id){
        ArrayList<Comment> comments = new ArrayList();
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM comment WHERE recipe_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            
            if(!result.first()){
                System.out.println(
                        "CommentBean.getComments(): no comments found");
                return new ReturnSprout("no comments found"
                        ,Response.Status.NOT_FOUND);
            }
        }
        catch(Exception e){
            /*TODO: error handleing that returns a sprout with appropriate
            message and status code*/
        }
        return new ReturnSprout(comments, Response.Status.OK);
    }

}
