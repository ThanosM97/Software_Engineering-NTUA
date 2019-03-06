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
      button:null,
      button2:null,
      id:null,
      visibility:"hidden",
      toDelete:null,
    }
    this.handleDelete = this.handleDelete.bind(this);
    this.deleteProduct = this.deleteProduct.bind(this);
  }

  componentWillMount(){
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }

  componentDidMount(){
    let profId = Router.query.id;
    this.setState({id:profId});
    if (this.state.loggedIn){this.setState({
      button:(<div id={profId} style={{width:"100%", backgroundColor:"#f1f1f1"}}><button className="deleteButton" id={profId} onClick={this.handleDelete} style={{width:"26%", padding:"10px 0"}}>Delete product</button></div>),
      button2:(<div id={profId} style={{width:"100%", backgroundColor:"#f1f1f1"}}><a href={"/addrecord?id="+profId}><button className="createButton" id={profId} onClick={this.handleCreate} style={{width:"26%", padding:"10px 0"}}>Create record</button></a></div>)
    })}
    this.setState({query:Router.query});
    let myQuery = querystring.stringify(Router.query);
    myQuery = myQuery.replace("id=","")
    let data = fetch('https://localhost:8765/observatory/api/products/'+myQuery).then((resp)=>{
      resp.json().then((res)=>{
        this.setState({data:res});
      })
    })
  }

  handleDelete(event){
    this.setState({visibility:"visible", toDelete:event.target.id});
  }

  deleteProduct(e){
    if (e.target.value=="yes"){
      let cookie = cookies.get('auth');
      fetch("https://localhost:8765/observatory/api/products/"+this.state.toDelete,{
        method: "delete",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          "X-OBSERVATORY-AUTH": cookie
        },
      }).then(response => {
          if (!response.ok) { alert("Something went wrong!"); throw response }
          else {alert("Product has been deleted"); Router.back()}

        })
      } else { Router.back()}
    }



  render(){
    let product = this.state.data;
    const confirm = (
      <div align="center" style={{visibility:this.state.visibility, position:"fixed", top:"50%", left:"35%",zIndex:10,backgroundColor:"#f2f2f2", border:"2px solid red",padding:"2%"}}>
        <h2> Are you sure that you want to delete this product? </h2>
        <button  className="buttonConf red" onClick={this.deleteProduct} value="yes"> Yes</button>
        <button className="buttonConf blue"  onClick={this.deleteProduct} value="no"> No</button>
      </div>
    )
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
                {this.state.button}
                {this.state.button2}
                {confirm}
                <ProductView product={this.state.data} loggedIn={this.state.loggedIn} />
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
