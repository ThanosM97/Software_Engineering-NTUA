import React, { Component } from 'react';
import HomeHeader from "../components/HomeHeader.js";
import HomeGrid from "../components/HomeGrid.js";
import Footer from "../components/Footer.js";
import Head from "next/head";
import Cookies from 'universal-cookie';

const cookies = new Cookies();

class Index extends Component {
  constructor(){
    super();
    this.state = {
      loggedIn:false,
    }
  }

  componentWillMount(){
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }

  render(){


    return (
      <div>
        <Head>
          <title> Stingy | Price comparison! </title>
          <link href="/static/indexStyle.css" rel="stylesheet" />
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
        </Head>
        <HomeHeader loggedIn={this.state.loggedIn}/>
        <HomeGrid />
        <Footer />
      </div>
    );
  }
  }
export default Index;
