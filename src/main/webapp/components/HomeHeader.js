import React, { Component } from 'react';
const square = 'static/images/Square_200x200.png';

class HomeHeader extends Component {

  constructor(){
    super()
    this.state={
      trendsVis: 'hidden',
      offersVis: 'hidden'
    }
  }

  toggleTrends(){
    this.state.trendsVis === "hidden" ? this.setState({trendsVis:"visible"}) : this.setState({trendsVis:"hidden"});
  }
  toggleOffers(){
    this.state.offersVis === "hidden" ? this.setState({offersVis:"visible"}) : this.setState({offersVis:"hidden"});
  }

  render() {

    return (
      <div className="header">
        <nav>
          <a href="signup">Εγγραφή</a>
          <a href="login">Σύνδεση</a>
        </nav>
        <div className="toptrends" style={{visibility: this.state.trendsVis}}>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <h1><a href="#">See more...</a></h1>
          </div>
          <button className="header-buttons" onClick={this.toggleTrends.bind(this)}>Top trends</button>
        </div>
        <div className="searchcontainer" style={{ position: 'relative'}}>
          <div className="searchspace">
            <input href="#" placeholder="Αναζητήστε κάποιο προϊόν..." />
            <input type="submit" value="Αναζήτηση" />
          </div>
        </div>
        <div className="hotoffers" style={{visibility: this.state.offersVis}}>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <h1><a href="#">See more...</a></h1>
          </div>
          <button className="header-buttons" onClick={this.toggleOffers.bind(this)}>Hot offers</button>
        </div>
      </div>
    );
  }
}

export default HomeHeader;
