import {ProductView} from '../components/ProductView.js';
import React, { Component } from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Router from 'next/router';
import Head from 'next/head';
import Cookies from 'universal-cookie';

const cookies = new Cookies();
const querystring = require('querystring');
const fetch = require("node-fetch");


class Productview extends Component {
  constructor(){
    super()
    this.state={
      loggedIn:false,
      query:"",
      data:null,
    }
  }

  componentWillMount(){
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }

  componentDidMount(){
    this.setState({query:Router.query});
    let myQuery = querystring.stringify(Router.query);
    myQuery = myQuery.replace("id=","")
    let data = fetch('https://localhost:8765/observatory/api/products/'+myQuery).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res});
      })
    })
  }

  render(){
    let product = this.state.data;
    if (product){
      return(
        <div>
          <Head>
            <title> Stingy | {product.name}</title>
            <link rel="shortcut icon" href="../static/logo/logo.png"/>
            <link href='../static/ProductView.css' type='text/css' rel='stylesheet' />
          </Head>
            <div>
                <NavBar loggedIn={this.state.loggedIn}/>
                <ProductView product={this.state.data}  />
                <Footer />
            </div>
        </div>
      );
    }
    return(
      <div> Waiting for data...</div>
    );
  }
}
export default Productview
