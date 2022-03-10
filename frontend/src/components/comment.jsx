/**
 * @file a component that displays a single comment
 * @author Zander Koch
 */

function Comment(props){
    return(
        <article>
            <header>
                <h6>{props.username} skriver:</h6> 
            </header>
            
        </article>
    );
}