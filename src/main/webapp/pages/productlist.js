import {ProductList} from '../components/ProductList.js';
import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Cookies from 'universal-cookie';
import Router from 'next/router'

const cookies = new Cookies();
const querystring = require('querystring');
const fetch = require("node-fetch");

class Page extends Component {
  render() {
    let cl = "pages " + this.props.active;
    return (
      <button className={cl} id={this.props.value} onClick={this.props.changeHandler}> {this.props.value} </button>
    );
  }
}


class Productlist extends Component {
  constructor(props){
    super(props);
    this.state = {
      loggedIn:false,
      total:null,
      start:0,
      count:5,
      data:[],
      pagesNo:0,
      activePage:1,
      query: {},
    }
    this.handlePageChange = this.handlePageChange.bind(this);
    this.handleFilterChange = this.handleFilterChange.bind(this);
  }

  componentWillMount() {
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }

  componentDidMount() {
    this.setState({query:Router.query})
    let myQuery = querystring.stringify(Router.query);
    myQuery = myQuery.replace(/%20/g, "&tags=");
    let data = fetch('https://localhost:8765/observatory/api/products?start='+this.state.start+"&count="+this.state.count+"&"+myQuery).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.products, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
  }

  handlePageChange(event){
    let st = (event.target.id -1) * (this.state.count+1) ;
    let myQuery = querystring.stringify(this.state.query);
    myQuery = myQuery.replace(/%20/g, "&tags=");
    let data = fetch('https://localhost:8765/observatory/api/products?start='+st+"&count="+this.state.count+"&"+myQuery).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.products, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
    this.setState({activePage: event.target.id,start:st});
  }

  handleFilterChange(newObject,category){
    let myQuery = querystring.stringify(newObject)
    myQuery = "category="+category+"&" + myQuery;
    let data = fetch('https://localhost:8765/observatory/api/products?start=0&count='+this.state.count+"&"+myQuery).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.products, start:0, activePage:1 , total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
  }


  render(){
    var pages=[];
    for (var i=1; i<=this.state.pagesNo; i++){
      i == this.state.activePage ? pages.push(<Page value={i} active="active" changeHandler={this.handlePageChange}/>) : pages.push(<Page value={i} active="" changeHandler={this.handlePageChange} />)
    }


    return(
      <div>
          <Head>
            <title> Λίστα προϊόντων | Stingy </title>
            <link rel="shortcut icon" href="../static/logo/logo.png"/>
            <link href='../static/ProductList.css' type='text/css' rel='stylesheet' />
          </Head>
          <div className='background'>
                  <NavBar loggedIn={this.state.loggedIn}/>
                  <div className="pagesBar" align="center"><label>Pages:</label> {pages} </div>
                  <ProductList products={this.state.data} query={this.state.query} filterChange={this.handleFilterChange.bind(this)} />
                  <Footer />
          </div>
      </div>
  );}

}
export default Productlist
