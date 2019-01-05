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
            <a href="/"><img src="../static/logo/LogoFinal-small.png" alt="" width="30%" /></a>
            <input href="#" placeholder="Αναζητήστε κάποιο προϊόν..." />
            <input type="submit" value="Αναζήτηση" />
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
