import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';
import Router from 'next/router'
import Cookies from 'universal-cookie';

const cookies = new Cookies();
const querystring = require('querystring');

class SignUp extends Component {
  constructor(){
    super()
    this.state={
      passVisibility: 'password',
      buttonText: 'Show',
      username: null,
      firstName:null,
      lastName:null,
      email:null,
      password:null,
      phoneNumber:null,
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentWillMount(){
    if (cookies.get('auth')) Router.push("/")
  }

  handleSubmit(event){
    event.preventDefault();
    let query={
      username:this.state.username,
      firstName: this.state.firstName,
      lastName:this.state.lastName,
      email:this.state.email,
      passowrd:this.state.password,
      phoneNumber:this.state.phoneNumber
    }
    query = querystring.stringify(query);
    fetch("https://localhost:8765/observatory/api/register",{
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
      },
      body: query
    }).then(response => {
        if (!response.ok) { alert("Something went wrong!"); throw response }
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

  showPassword(){
    this.state.passVisibility === 'password' ? this.setState({passVisibility:'text'}) : this.setState({passVisibility:'password'});
    this.state.buttonText === 'Show' ? this.setState({buttonText:'Hide'}) : this.setState({buttonText:'Show'});
  }

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
          <div  id="horizontalLine"></div>
          <form onSubmit={this.handleSubmit}>
          <div>
              <div className="col1">
                <label>*Ψευδώνυμο λογαριασμού:</label>
                <input name="username"  onChange={this.handleChange} className="inputField req" type="text" placeholder="Type your username..." name="username"  required/>
                <label>*Ηλεκτρονικό ταχυδρομείο:</label>
                <input name="email" onChange={this.handleChange}  className="inputField req" type="email" placeholder="Type your email..." name="email" required/>
                <label>*Κωδικός πρόσβασης:</label>
                <input name="password"  onChange={this.handleChange} className="inputField req" type={ this.state.passVisibility } placeholder="Type your password..." name="password1"  required />
                <button className="showPass" type="button"onClick={this.showPassword.bind(this)}> {this.state.buttonText} </button>
              </div>
              <div className="col2">
                <label>Όνομα:</label>
                <input name="firstName"  onChange={this.handleChange} className="inputField" type="text" placeholder="Type your first name..." name="firstName" />
                <label>Επώνυμο:</label>
                <input name="lastName"  onChange={this.handleChange} className="inputField" type="text" placeholder="Type your last name..." name="lastName" />
                <label>Αριθμός τηλεφώνου:</label>
                <input name="phoneNumber"  onChange={this.handleChange} className="inputField" type="text" placeholder="Type your phone number..." name="tel" />
              </div>

              <div className="submitCol" align="center">
                <p style={{"fontSize":"1vw", "color":"blue", "display":"inline"}}>*</p><p style={{"fontSize":"1vw", "color":"black","display":"inline"}}> Τα μπλε πεδία είναι υποχρεωτικά. Τα υπόλοιπα μπορούν να συμπληρθούν και αργότερα από το προφίλ σας. </p><br />
                <button className="inputButton" type="submit"> Εγγραφή </button>
              </div>
            </div>
          </form>
        </div>
        <Footer />
      </div>
    );
  }
}

export default SignUp
