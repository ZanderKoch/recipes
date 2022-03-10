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

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getTarget(){
        return target;
    }

    public void setTarget(int target){
        this.target = target;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
    
    
}
