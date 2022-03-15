/**
 * @file a component for a recipe editor
 * @author Zander Koch
 */

 import React from "react";

function RecipeEditor(){
    function sendRecipe(e){
        e.preventDefault();
        console.log("send recipes");
        
        let recipe = {
            title: "",
            ingredients: [],
            description: "",
            instructions: []
        };

        let formElem = document.querySelector("form");
        console.log(formElem);

        recipe.title = formElem.elements.title.value;
        
        console.log(formElem.elements.ingredient);

        //hardcoded until a solution with loops is found
        recipe.ingredients.push((formElem.elements.ingredient1).value);
        recipe.ingredients.push((formElem.elements.ingredient2).value);
        recipe.ingredients.push((formElem.elements.ingredient3).value);
        recipe.ingredients.push((formElem.elements.ingredient4).value);
        recipe.ingredients.push((formElem.elements.ingredient5).value);
        recipe.ingredients.push((formElem.elements.ingredient6).value);
        recipe.ingredients.push((formElem.elements.ingredient7).value);
        recipe.ingredients.push((formElem.elements.ingredient8).value);
            
        
        console.log(recipe.ingredients);

        recipe.description = formElem.elements.description.value;

        //hardcoded until a solution with loops is found
        recipe.instructions.push((formElem.elements.instruction1).value);
        recipe.instructions.push((formElem.elements.instruction2).value);
        recipe.instructions.push((formElem.elements.instruction3).value);
        recipe.instructions.push((formElem.elements.instruction4).value);
        recipe.instructions.push((formElem.elements.instruction5).value);
        recipe.instructions.push((formElem.elements.instruction6).value);
        recipe.instructions.push((formElem.elements.instruction7).value);
        recipe.instructions.push((formElem.elements.instruction8).value);

        console.log(recipe.instructions);
    }
    
    return(
        <main className="recipe-editor">
            <header>
                <h2>Lägg till /redigera ett recept</h2>
            </header>
            <form action="POST" onSubmit={sendRecipe}>
                <label htmlFor="title">Titel: </label>
                <input type="text" id="title" name="title"/>
                <fieldset>
                    <legend>Ingredienser</legend>
                    <input type="text" id="ingredient1" name="ingredient1"/>
                    <input type="text" id="ingredient2" name="ingredient2"/>
                    <input type="text" id="ingredient3" name="ingredient3"/>
                    <input type="text" id="ingredient4" name="ingredient4"/>
                    <input type="text" id="ingredient5" name="ingredient5"/>
                    <input type="text" id="ingredient6" name="ingredient6"/>
                    <input type="text" id="ingredient7" name="ingredient7"/>
                    <input type="text" id="ingredient8" name="ingredient8"/>
                </fieldset>
                <label htmlFor="description">Beskrivning: </label>
                <textarea name="description" id="description" cols="35" rows="4"></textarea>
                <fieldset>
                    <legend>Instruktioner</legend>
                    <input type="text" id="instruction1" name="instruction1"/>
                    <input type="text" id="instruction2" name="instruction2"/>
                    <input type="text" id="instruction3" name="instruction3"/>
                    <input type="text" id="instruction4" name="instruction4"/>
                    <input type="text" id="instruction5" name="instruction5"/>
                    <input type="text" id="instruction6" name="instruction6"/>
                    <input type="text" id="instruction7" name="instruction7"/>
                    <input type="text" id="instruction8" name="instruction8"/>
                </fieldset>
                <button type="submit">Skicka recept</button>
            </form>
        </main>
    );
}

export default RecipeEditor;