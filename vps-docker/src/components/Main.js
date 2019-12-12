import React, { Component } from 'react';
import './main.css';

import TableVps from './TableVps.js'

class Main extends Component{
    
    
    render() {
        return (
          <div >
            <TableVps history={this.props.history}/>
          </div>
        );
      }
}

export default Main