import React, { Component } from 'react';
import Head from 'next/head';

class Login extends Component {

  render() {
    return (
      <div style={{backgroundColor:"#f1f1f1", position:"absolute",height:"100%", width:"100%", margin:"-10px"}}>
        <Head>
          <title>Σύνδεση χρήστη | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href="/static/loginStyle.css" rel="stylesheet" />
        </Head>
        <div className="modal">
            <form action="/">
              <div className="imgcontainer" align="center">
                <img src="../static/images/avatar.png" width="40%" alt="Avatar" />
              </div>

              <div className="container">
                <input className="inputField" type="text" placeholder="Όνομα χρήστη" name="uname" /><br />
                <input className="inputField" type="password" placeholder="Συνθηματικό" name="psw" /><br />
                <input className="regular-checkbox" type="checkbox" /> Μείνε συνδεδεμένος <br />
                <button className= "inputField inputButton" type="submit">Σύνδεση</button><br />
                <div align="center">
                  <a className="forgot" href="#" >Ξέχασα το συνθηματικό μου</a><br />
                  <div style={{marginTop:"5px"}}>Νέος χρήστης? <a className="forgot" href="signup" >Γίνε μέλος</a></div>
                  <div style={{marginTop:"5%"}}><a className="forgot" href="/" >Επιστροφή στην αρχική</a></div>
                </div>
              </div>

            </form>
        </div>
      </div>
    );
  }
}

export default Login;
