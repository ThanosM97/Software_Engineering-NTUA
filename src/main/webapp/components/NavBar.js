import React, { Component } from 'react';
import Head from 'next/head';
import Cookies from 'universal-cookie';
import Router from 'next/router'

var bar;
var dropdown;
const cookies = new Cookies();

class NavBar extends Component {

  constructor(props){
    super(props)

    bar = this.props.loggedIn ? (<div className="nav-right">
      <p><a href="" onClick={this.handleLogOut} className="right"> Αποσύνδεση </a>
        <a href="/my_profile" className="right"> Το προφίλ μου </a></p>
    </div>) : (<div className="nav-right">
      <a href="signup" className="right"> Εγγραφή </a>
      <a href="login" className= "right"> Σύνδεση </a>
    </div>)
  }

  handleLogOut() {
    cookies.remove('auth', {path: '/'})
    Rooter.back();
  }

  render(){
    return (
      <div>
        <Head>
          <link href="/static/NavBar.css" rel="stylesheet" />
        </Head>
        <nav className="navbar">
          <div className="nav-left">
            <a href="/" ><img href="/" src="../static/logo/LogoFinal-small.png" alt="" width="30%"/> </a>
            <form className="form" action="/productlist" >
              <input placeholder="Αναζητήστε κάποιο προϊόν..." name="tags"/>
              <input type="submit"/>
            </form>
          </div>
          {bar}
        </nav>
      </div>
    );
  }
}

export default NavBar;
