import React from 'react';
import List from "./components/List";
import dummyItems from "./items.json"

export default class App extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      favItems: [],
      showFavorite: false
    };
  }
  render() {
    const { favItems } = this.state;
    const ShowFavorite = () => (
      <div className="col-sm">
        <List
          title="My Favourite"
          items={favItems}
          onItemClick={this.handleItemClickRemove}
        />
      </div>
    )

    return (
      <div className="container-fluid">
        <h1 className="text-center">
          Welcome!
          <small>Class-based</small>
        </h1>
        <div className="container pt-3">
          <input type="checkbox" onClick={this.toggleHidden.bind(this)} /> Show Favorite
          <div className="row">
            <div className="col-sm">
              <List
                title="Our Menu"
                items={dummyItems}
                onItemClick={this.handleItemClickAdd}
              />
            </div>
            {this.state.showFavorite && <ShowFavorite/>}
          </div>
        </div>
      </div>
    )
  }

  handleItemClickAdd = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};
    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if(targetInd < 0) newItems.push(newItem);
    // else newItems.splice(targetInd, 1);
    this.setState({ favItems: newItems });
  };

  handleItemClickRemove = item => {
    const newItems = [...this.state.favItems];
    const newItem = {...item};
    const targetInd = newItems.findIndex(it => it.id === newItem.id);
    if(!(targetInd < 0)) newItems.splice(targetInd, 1);
    this.setState({ favItems: newItems });
  };

  toggleHidden () {
    if (this.state.showFavorite) {
      this.setState({
        showFavorite: false
      })
    }
    else {
      this.setState({
        showFavorite: true
      })
    } 
  }
}
