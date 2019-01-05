import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';

class SignUp extends Component {

  render() {
    return (
      <div style={{backgroundColor:"#f1f1f1", position:"absolute",height:"100%", width:"100%"}}>
        <Head>
          <title>Εγγραφή χρήστη | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href="../static/signupStyle.css" rel="stylesheet" />
        </Head>
        <NavBar />
        <div className="modal">
          <h1>Εγγραφή νέου χρήστη</h1>

        </div>
        <Footer />
      </div>
    );
  }
}

export default SignUp
