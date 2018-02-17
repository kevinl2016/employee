import React, { Component } from 'react';
import arrayToTree from 'array-to-tree';
import axios from 'axios';

import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.timeout = 30000;
    this.state = {};
  }

  componentDidMount(){
    const employees = axios.get('/employees');
    employees.then( response=>{
      this.setState({data:response.data});
    });
  }

  dataConvert(items) {
    if (items && items.length > 0){
      return items.map(item => {
        let data = {
          "id": item.id,
          "name": item.name,
          "toggled": true,
          "parent_id": undefined,
        };
        if(item.manager !== null) {
          data.parent_id = item.manager.id;
        }
        return data;
      })
    } else {
      return [];
    }
  }

  renderOl(nodes, level) {
      var component = this;
      var items = [];
      // For every node
      (nodes || []).forEach(function(node) {
          // Build an OL for all children of this node
          var children;
          if (node.children && node.children.length>0) {
              children = component.renderOl(node.children, level -1);
          }
          // Push an LI for this node.
          items.push(<li className='li-style' key={node.id}>
              <div className={`title-wrap-${level}`}>
                <span>
                  {node.name}
                </span>
              </div>
              {children}
          </li>);
      });
      return (<ol>{items}</ol>);
  }

  render() {
    const { data } = this.state;
    const nodes = arrayToTree(this.dataConvert(data));
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Employee List</h1>
        </header>
        <div className='list-div'>
          { nodes && nodes.length > 0 &&
            this.renderOl(nodes, 3)
          }
        </div>
      </div>
    );
  }
}

export default App;
