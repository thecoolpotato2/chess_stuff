import React from 'react';
import { useState } from 'react';
import logo from './logo.svg';
import './App.css';
import Board from './Board';
import Bishop from './Bishop';

function App() {
  let [pieces, setPieces] = useState<Object>();

  function fetchChessData() {
    fetch('http://localhost:8080/chess/getBoard')
    .then((response) => {return response.json()})
    .then(json => {
      console.log('Received data:', json);
      assignPieces(json)})
  }

  function assignPieces(json: any) {
    let tempPieces = json
    setPieces(tempPieces)
  }
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <section>
          <button onClick={fetchChessData}>Get Chess Data</button>
        </section>
        <section>
          <Bishop />
          <Board />
        </section>
        <section>
        {pieces ? (
            Object.entries(pieces).map(([key, value]) => (
              <span key={key}>
                <p>{value.value}</p>
                <p>{value.color}</p>
                <p>{value.dead.toString()}</p>
              </span>
            ))
          ) : (<p>No Pieces to display</p>)}</section>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
