import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';
const fetch = require("node-fetch");


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
    }
    this.handleChange = this.handleChange.bind(this);
  }



  handleChange(event){
    let st = (event.target.id -1) * this.state.count +1;
    this.setState({activePage: event.target.id,start:st});
  }


  render() {
    let data = fetch('http://localhost:8765/app/observatory/api/shops?start='+this.state.start+"&count="+this.state.count).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.shops, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })

    const shops = this.state.data.map(shop =>
        <div className='shop'>
            <img src={shop.image} class='product-image' />
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
          <link href="../static/shopsStyle.css" rel="stylesheet" />
        </Head>
        <NavBar />
        <div>
          <h1 style={{marginTop:"2%"}}> Καταστήματα </h1>
          <button className="filter"> Αλφαβητική σειρά </button>
        </div>
        <div  id="horizontalLine" align="center"></div>
        <div style={{width:"80%",marginTop:"2%", position:"relative", display:"block"}}>
          {shops} <br />
          <div className="pagesBar"> {pages} </div>
        </div>
        <Footer />
      </div>


    );
  }


}

export default Shops;
