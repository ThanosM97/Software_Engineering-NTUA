import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';
import Cookies from 'universal-cookie';
const fetch = require("node-fetch");

const cookies = new Cookies();

class Page extends Component {
  render() {
    let cl = "pages " + this.props.active;
    return (
      <button className={cl} id={this.props.value} onClick={this.props.changeHandler}> {this.props.value} </button>
    );
  }
}

class Shops extends Component {
  constructor(){
    super();
    this.state = {
      total:null,
      start:0,
      count:8,
      data:[],
      pagesNo:0,
      activePage:1,
      loggedIn:false,
    }
    this.handleChange = this.handleChange.bind(this);
  }

  componentWillMount(){
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }

  componentDidMount(){
    let data = fetch('https://localhost:8765/observatory/api/shops?start='+this.state.start+"&count="+this.state.count).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.shops, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
  }


  handleChange(event){
    let st = (event.target.id -1) * (this.state.count +1);
    let data = fetch('https://localhost:8765/observatory/api/shops?start='+st+"&count="+this.state.count).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.shops, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
    this.setState({activePage: event.target.id,start:st});
  }


  render() {
    const shops = this.state.data.map((shop,i) =>
        <div key={i} className='shop'>
            <img src={shop.image} className='product-image' />
            <h2>{shop.name}</h2>
            <h4>{shop.address}</h4>
        </div>
        );

      var pages = [];
      for (var i = 1; i<=this.state.pagesNo; i++){
        i == this.state.activePage ? pages.push(<Page value={i} active="active" changeHandler={this.handleChange}/>) : pages.push(<Page value={i} active="" changeHandler={this.handleChange} />)
        }


    return (
      <div style={{backgroundColor:"#f1f1f1", position:"relative", width:"100%" , minHeight:"100vh", display:"block", overflow:"hidden"}} align="center">
        <Head>
          <title>Καταστήματα | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href="../static/shopsStyle.css" rel="stylesheet" />
        </Head>
        <NavBar loggedIn={this.state.loggedIn} />
        <div>
          <h1 style={{marginTop:"2%"}}> Καταστήματα </h1>
          <button className="filter"> Αλφαβητική σειρά </button>
        </div>
        <div  id="horizontalLine" align="center"></div>
        <div style={{width:"80%",marginTop:"2%", position:"relative", display:"block"}}>
          {shops} <br />
          <div className="pagesBar" align="center"> <label>Pages:</label>{pages} </div>
        </div>
        <Footer />
      </div>


    );
  }


}

export default Shops;
