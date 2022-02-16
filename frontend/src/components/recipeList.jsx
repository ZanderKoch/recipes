/**
 * @file an element that displays a list of all recepies
 * @author Zander Koch
 */

import RecipePreview from "./recipePreview";

function RecipeList(){
    //dummy data, two-recipe array
    let testRecipes = [
        {
            "id": 1,
            "author": "Svante",
            "title": "pannkakor",
            "ingredients": [
                "mjölk",
                "ägg",
                "mjöl"    
            ],
            "description": "de e platta",
            "instructions": [
                "vispa",
                "stek"
            ],
            "starCount": 7,
            "comments": [
                {
                    "id": 1,
                    "target": 1,
                    "username": "Erik",
                    "content": "🥘🍪👍👍👍👍👍"
                },
                {
                    "id": 2,
                    "target": 1,
                    "username": "Håkan",
                    "content": "instruktioner oklara, byggde av mistag en IKEA-hylla"
                },
            ],
            "imageUrl": "https://picsum.photos/200/300"
        },
        {
            "id": 2,
            "author": "Sture",
            "title": "ugnspanka med fläsk",
            "ingredients": [
                "mjölk",
                "ägg",
                "mjöl",
                "saltat fläsk"    
            ],
            "description": "bättre än pannkakor",
            "instructions": [
                "vispa",
                "stek"
            ],
            "starCount": 300,
            "comments": [
                {
                    "id": 3,
                    "target": 2,
                    "username": "Erik",
                    "content": "🥘🍪🥓👍👍👍👍👍"
                },
                {
                    "id": 4,
                    "target": 2,
                    "username": "Håkan",
                    "content": "instruktioner oklara, brände ner min lägenhet"
                },
            ],
            "imageUrl": "https://picsum.photos/200/300"
        }
    ];

    return(
        <section className="recipe-list">
            <header>
                <h3>
                    Recept:
                </h3>
            </header>
            {
                testRecipes.map(recipe=>
                {
                    return(
                        <RecipePreview key={recipe.id} {...recipe}/>
                    )
                })
            }
        </section>
    );
}

export default RecipeList;