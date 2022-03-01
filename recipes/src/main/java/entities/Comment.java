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
    private String Image; //base64 when being posted, url otherwise
}