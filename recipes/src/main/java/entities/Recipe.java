
package entities;

import java.util.ArrayList;

/**
 * entity representig a whole recipe
 * @author Zander Koch
 */
public class Recipe{
    private int id;
    private String author;
    private String title;
    private ArrayList<String> ingredients = new ArrayList();
    private String description;
    private ArrayList<String> instructions = new ArrayList();
    private int starCount;
    private ArrayList<Comment> comments = new ArrayList();
    private ArrayList<String> categories = new ArrayList();
    private String Image; //base64 when being posted, url otherwise
    
    public Recipe(){
        
    }
    
    /**
     * constructor for creating recipe with all atributes found in recipe table
     */
    public Recipe(int id, String author, String title, String description){
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<String> instructions) {
        this.instructions = instructions;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<String> getCategories(){
        return categories;
    }

    public void setCategories(ArrayList<String> categories){
        this.categories = categories;
    }
}
