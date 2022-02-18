
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
    private ArrayList<String> ingredients;
    private String description;
    private ArrayList<String> instructions;
    private int starCount;
    private ArrayList<Comment> comments;
}
