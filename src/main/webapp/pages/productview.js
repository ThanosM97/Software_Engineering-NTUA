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

const myExampleProduct = {
    device: 'smartphone', name: 'Testphone 5inch 4GB RAM Black', size: '6"', ram: '4GB', capacity: '32GB', backCamera: '20Mp',
    frontCamera: '4Mp', cpuCores: '4', cpuFrequency: '1.8 GHz', OS: 'Android'
};

const myExampleShops = [
    {name: 'Λαική αγορά Αιγαλέου', price: '5$', otherData: ''},
    {name: 'Public Συντάγματος', price: '1000$', otherData: ''},
    {name: 'Πλαίσιο Πειραιά', price: '1000$', otherData: ''}
];

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
            <head>
                <link href='../static/ProductView.css' type='text/css' rel='stylesheet' />
            </head>
            <div>
                <NavBar />
                <ProductView product={this.state.data} shops={myExampleShops} />
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
