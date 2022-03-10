/**
 * @file an component that displays a preview of provided recipe
 * @author Zander Koch
 */

import "./component styles/recipePreview.scss";

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
                    props.ingredients.map(ingredient =>{
                        return(
                            <li key={ingredient}>{ingredient}</li> 
                        )
                    })
                }
            </ul>
            <header>
                <h5>Beskrivning</h5>
            </header>
            <p>
                {props.description}
            </p>
        </article>
    );
}

export default RecipePreview;