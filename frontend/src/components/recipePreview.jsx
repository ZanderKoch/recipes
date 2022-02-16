/**
 * @file an element that displays a preview of provided recipe
 * @author Zander Koch
 * @todo implement
 */

import "./component styles/recipePreview.scss";

function RecipePreview(props){
    return(
        <article className="recipe-preview">
            <header>
                <h4>{props.title}</h4>
            </header>
            <span>&#9733;{props.starCount}</span>
            <p>
                {props.description}
            </p>
        </article>
    );
}

export default RecipePreview;