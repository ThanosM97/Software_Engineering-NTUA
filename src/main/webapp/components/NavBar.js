import React, { Component } from 'react';
import Head from 'next/head';

class NavBar extends Component {
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
              <input placeholder="Αναζητήστε κάποιο προϊόν..." name="search"/>
              <input type="submit"/>
            </form>
          </div>
          <div className="nav-right">
            <a href="signup" className="right"> Εγγραφή </a>
            <a href="login" className= "right"> Σύνδεση </a>
          </div>
        </nav>
      </div>
    );
  }
}

export default NavBar;
