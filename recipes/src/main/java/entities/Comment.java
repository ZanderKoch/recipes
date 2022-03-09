package entities;

/**
 * entity representing a comment
 * @author Zander Koch
 */
public class Comment{
    private int id;
    private int target;
    private String username;
    private String content;

    public Comment(){
    
    }

    public Comment(int id, int target, String username, String content){
        this.id = id;
        this.target = target;
        this.username = username;
        this.content = content;
    }
    
    
}
