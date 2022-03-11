/**
 * @file an component that displays a preview of provided recipe
 * @author Zander Koch
 */

import "./component styles/recipePreview.scss";
import CommentComponent from "./commentComponent";

function RecipePreview(props){
    return(
        <article className="recipe-preview">
            <header>
                <h4>{props.title}</h4>
            </header>
            <span>&#9733;{props.starCount}</span>
            <header>
                <h5>Ingredienser</h5>
            </header>
            <ul>
                {
                    props.ingredients.map(ingredient => {
                        return(
                            <li key={ingredient}>{ingredient}</li> 
                        );
                    })
                }
            </ul>
            <header>
                <h5>Beskrivning</h5>
            </header>
            <p>
                {props.description}
            </p>
            <header>
                <h5>Instruktioner</h5>
            </header>
            <ol>
                {
                    props.instructions.map(instruction => {
                        return(
                            <li key={instruction}>{instruction}</li>
                        );
                    })
                }
            </ol>
            <div>
                <header>
                    <h5>Kommentarer</h5>
                </header>
                {
                    props.comments.map(comment => {
                        return(
                            <CommentComponent key={comment.id} {...comment}/>
                        );
                    })
                }
                </div>
        </article>
    );
}

export default RecipePreview;