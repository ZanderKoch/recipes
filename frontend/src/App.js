import logo from './logo.svg';
import "./styles/colors.scss";

import Navigation from './components/navigation';
import Home from './components/home';

import {BrowserRouter as Router, Route, Routes } from 'react-router-dom';




function App() {
  return (
    <>
        <Router>
            <Navigation/>
            <Routes>
                <Route path="/" element={<Home/>}/>
            </Routes>
        </Router>
    </>
  );
}

export default App;
