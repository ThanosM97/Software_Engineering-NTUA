import React, { Component } from 'react';
import Head from 'next/head';
import Cookies from 'universal-cookie'
import Router from 'next/router'

const cookies = new Cookies();

class Login extends Component {
  constructor(){
    super();
    this.state = {
      loggedIn:false,
      token:null,
      username:"",
      password:"",
    }
    this.login = this.login.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  login(event){
    fetch("https://localhost:8765/observatory/api/login",{
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: "username="+this.state.username+"&password="+this.state.password
    }).then(response => {
        if (!response.ok) { alert("Wrong credentials!"); throw response }
        return response.json()
      })
    .then(data => {cookies.set('auth', data.token, {path: '/'}); Router.back()});
    event.preventDefault();
  }


  handleChange(event) {
   this.setState({
     [event.target.name]: event.target.value
   });
  }


  render() {
    return (
      <div style={{backgroundColor:"#f1f1f1", position:"absolute",height:"100%", width:"100%", margin:"-10px"}}>
        <Head>
          <title>Σύνδεση χρήστη | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href="/static/loginStyle.css" rel="stylesheet" />
        </Head>
        <div className="modal">
            <form action="" onSubmit={this.login} >
              <div className="imgcontainer" align="center">
                <img src="../static/images/avatar.png" width="40%" alt="Avatar" />
              </div>

              <div className="container">
                <input className="inputField" type="text" placeholder="Όνομα χρήστη" name="username" value={this.state.username}  onChange={this.handleChange} /><br />
                <input className="inputField" type="password" placeholder="Συνθηματικό" name="password" value={this.state.password}  onChange={this.handleChange} /><br />
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
