/**
 * @file site homepage, rendered when route is "/", and shows recipe list
 * @author Zander Koch
 */

import RecipeList from "./recipeList";

function Home(){  
    return(
        <main>
            <header>
                <h2>
                    Hem
                </h2>
            </header>
            <p>
                Välkommen till receptsidan! Nedan kan du se alla sidans recept
                och ovan kan du logga in / reistrera dig, eller som inloggad
                användare lägga till ett recept.
            </p>
            <RecipeList/>
        </main>
    );
}

export default Home;