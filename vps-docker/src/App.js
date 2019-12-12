import React, { Component } from 'react';
import './App.css'
import {BrowserRouter as Router, Route, Switch } from "react-router-dom"

import Main from './components/Main.js'
import Create from './components/Create.js'

class App extends Component {
  
  render(){
    return(
      <div className="App">
        <div className="App-header">            
          <h2>VPS DOCKERS</h2>
        </div>
        <Router>
          <Switch>
            <Route path="/" exact component={Main} />
            <Route path="/create" exact component={Create} /> 
          </Switch>
        </Router>
      </div>
    )

  }
}

export default App;
