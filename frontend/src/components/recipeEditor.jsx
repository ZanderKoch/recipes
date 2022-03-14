/**
 * @file a component for a recipe editor
 * @author Zander Koch
 */

 import React from "react";

function RecipeEditor(){
    function sendRecipe(){
        console.log("send recipes")
        let recipe = {
            title: "",
            ingredients: [],
            description: "",
            instructions: []
        };

        let formElem = document.querySelector("form");
        console.log(formElem);

        recipe.title = formElem.elements.title.value;

        console.log(recipe.title);
    }
    
    return(
        <main className="recipe-editor">
            <header>
                <h2>Lägg till /redigera ett recept</h2>
            </header>
            <form action="" onSubmit={sendRecipe}>
                <label htmlFor="title">Titel: </label>
                <input type="text" id="title" name="title"/>
                <fieldset>
                    <legend>Ingredienser</legend>
                    <input type="text" id="ingredient1" name="ingredient"/>
                    <input type="text" id="ingredient2" name="ingredient"/>
                    <input type="text" id="ingredient3" name="ingredient"/>
                    <input type="text" id="ingredient4" name="ingredient"/>
                    <input type="text" id="ingredient5" name="ingredient"/>
                    <input type="text" id="ingredient6" name="ingredient"/>
                    <input type="text" id="ingredient7" name="ingredient"/>
                    <input type="text" id="ingredient8" name="ingredient"/>
                </fieldset>
                <label htmlFor="description">Beskrivning: </label>
                <textarea name="description" id="description" cols="35" rows="4"></textarea>
                <fieldset>
                    <legend>Instruktioner</legend>
                    <input type="text" id="instruction1" name="instruction"/>
                    <input type="text" id="instruction2" name="instruction"/>
                    <input type="text" id="instruction3" name="instruction"/>
                    <input type="text" id="instruction4" name="instruction"/>
                    <input type="text" id="instruction5" name="instruction"/>
                    <input type="text" id="instruction6" name="instruction"/>
                    <input type="text" id="instruction7" name="instruction"/>
                    <input type="text" id="instruction8" name="instruction"/>
                </fieldset>
                <button type="submit">Skicka recept</button>
            </form>
        </main>
    );
}

export default RecipeEditor;