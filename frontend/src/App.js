import logo from './logo.svg';
import './App.css';
import Dashboard from './components/Dashboard';
import {useEffect, useState} from 'react';
import React from 'react';
import Deals from './components/Deals';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

// const retr = () => async() => {
//   const config = {
//     headers:{
//         'Content-type':'application/json'
//     }
//   }

//   //Handling the post action and getting user data
//   const {data} = await axios.get(
//       'http://localhost:8080/travel/deals',
//       config
//   )
//   console.log(data);
// }

function App() {
  //const [data, setData] = useState([]);
  
  

  return (
    <div className="App">
      
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Dashboard/>} />
          <Route path="/deals" element={<Deals />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;