import React, { Component } from 'react';
const square = 'static/images/Square_200x200.png';
import Cookies from 'universal-cookie';


var topBar;
const cookies = new Cookies();

class HomeHeader extends Component {

  constructor(props){
    super(props)
    this.state={
      trendsVis: 'hidden',
      offersVis: 'hidden',
    }
    topBar = this.props.loggedIn ? (<nav><a href="/" onClick={this.handleLogOut}> Αποσύνδεση </a>
          <a href="/my_profile">Το προφίλ μου</a></nav>) : (<nav><p>
      <a href="signup">Εγγραφή</a>
      <a href="login">Σύνδεση</a></p>
    </nav>);

  }

  handleLogOut(){
    let token = cookies.get('auth');
    fetch("https://localhost:8765/observatory/api/logout",{
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "X-OBSERVATORY-AUTH": token,
      },
      body: ""
    }).then(response => console.log(response.json()))
    cookies.remove('auth', {path: '/'})
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
        {topBar}
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
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <button className="header-buttons" onClick={this.toggleTrends.bind(this)}>Top trends</button>
        </div>
        <div className="searchcontainer" style={{ position: 'relative'}}>
          <div className="searchspace">
            <form action="/productlist">
              <input placeholder="Αναζητήστε κάποιο προϊόν..." name="tags" />
              <input type="submit" value="Αναζήτηση" />
            </form>
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
            <img src={square} />
            <h1>Item</h1>
          </div>
          <div className="items-container">
            <img src={square} />
            <h1>Item</h1>
          </div>
          <button className="header-buttons" onClick={this.toggleOffers.bind(this)}>Hot offers</button>
        </div>
      </div>
    );
  }
}

export default HomeHeader;
