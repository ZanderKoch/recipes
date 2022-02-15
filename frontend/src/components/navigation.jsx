/**
 * @file the main navigation header for the site
 * @author Zander Koch
 */
import React from "react";
import {NavLink} from "react-router-dom";

function Navigation(){
    return (
        <header>
            <nav>
                <header>
                    <NavLink to="/">
                        <h1>
                            Receptsidan
                        </h1>
                    </NavLink>
                </header>
                <ul>
                    <li>
                        <NavLink to="/add">
                            Lägg till recept
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/login">
                            Logga in
                        </NavLink>
                    </li>
                </ul>
            </nav>
        </header>
    );
}

export default Navigation;