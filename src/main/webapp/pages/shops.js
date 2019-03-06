import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';
import Cookies from 'universal-cookie';
import Router from 'next/router'

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
      toDelete:null,
      button:null,
      visibility:"hidden",
      buttonVisibility:"hidden",
      sort:false,

    }
    this.handleChange = this.handleChange.bind(this);
    this.deleteShop = this.deleteShop.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  componentWillMount(){
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }

  componentDidMount(){
    if (cookies.get('auth')) this.setState({buttonVisibility:"visible"})
    let data = fetch('https://localhost:8765/observatory/api/shops?start='+this.state.start+"&count="+this.state.count).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.shops, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
  }


  handleChange(event){
    let st = (event.target.id -1) * (this.state.count +1);
    var sort="";
    if(event.target.name == "sort" || this.state.sort){st=0 ; sort="&sort=name|ASC"; this.setState({sort:true})}
    let data = fetch('https://localhost:8765/observatory/api/shops?start='+st+"&count="+this.state.count+sort).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res.shops, total:res.total, pagesNo:Math.ceil(res.total/this.state.count)});
      })
    })
    this.setState({activePage: event.target.id,start:st});
  }

  handleClick(event){
    this.setState({visibility:"visible", toDelete: event.target.id});
  }

  dontDelete(){
    Router.back()
  }

  deleteShop(e){
    if (e.target.value="yes"){
      let cookie = cookies.get('auth');
      fetch("https://localhost:8765/observatory/api/shops/"+this.state.toDelete,{
        method: "delete",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          "X-OBSERVATORY-AUTH": cookie
        },
      }).then(response => {
          if (!response.ok) { alert("Something went wrong!"); throw response }
          else {alert("Shop has been deleted"); Router.push('/shops')}

        })
      } else { Router.push('/shops')}
  }


  render() {
    const shops = this.state.data.map((shop,i) =>
        <div key={i} className='shop'>
            <img src={shop.image} className='product-image' />
            <h2>{shop.name}</h2>
            <h4>{shop.address}</h4>
            <button id={shop.id} style={{visibility:this.state.buttonVisibility}}className="deleteButton" onClick={this.handleClick}> Delete shop?</button>
        </div>
        );

      var pages = [];
      for (var i = 1; i<=this.state.pagesNo; i++){
        i == this.state.activePage ? pages.push(<Page value={i} active="active" changeHandler={this.handleChange}/>) : pages.push(<Page value={i} active="" changeHandler={this.handleChange} />)
        }

        const confirm = (
          <div style={{visibility:this.state.visibility, position:"fixed", top:"50%", left:"35%",zIndex:10,backgroundColor:"#f2f2f2", border:"2px solid red",padding:"2%"}}>
            <h2> Are you sure that you want to delete this shop? </h2>
            <button  className="buttonConf red" onClick={this.deleteShop} value="yes"> Yes</button>
            <button className="buttonConf blue"  onClick={this.dontDelete} value="no"> No</button>
          </div>
        )

    return (
      <div style={{backgroundColor:"#f1f1f1", position:"relative", width:"100%" , minHeight:"100vh", display:"block", overflow:"hidden"}} align="center">
        <Head>
          <title>Καταστήματα | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href="../static/shopsStyle.css" rel="stylesheet" />
        </Head>
        <NavBar loggedIn={this.state.loggedIn} />
        {confirm}
        <div>
          <h1 style={{marginTop:"2%"}}> Καταστήματα </h1>
          <button className="filter" name="sort" onClick={this.handleChange}> Αλφαβητική σειρά </button>
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
