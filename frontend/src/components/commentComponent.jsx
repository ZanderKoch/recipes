/**
 * @file a component that displays a single comment
 * @author Zander Koch
 */

function CommentComponent(props){
    return(
        <article>
            <header>
                <h6>{props.username} skriver:</h6> 
            </header>
            <p>
                {props.content}
            </p>
        </article>
    );
}

export default CommentComponent;