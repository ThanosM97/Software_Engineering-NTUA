import React from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Head from 'next/head';
import Cookies from 'universal-cookie';
import Router from 'next/router'


const cookies = new Cookies();
const querystring = require('querystring');
const fetch = require("node-fetch");

class Content extends React.Component {
  constructor(){
    super()
    let curDate = new Date();
    curDate.hours = curDate.hours + 2;
    curDate = curDate.toISOString().split('T')[0]
    this.state = {
      loggedIn:false,
      shops:[],
      allShops:null,
      req:{
        shopId: null,
        dateFrom: curDate,
        dateTo: curDate,
        productId:null,
        price:null,

      },
      visibility:"hidden",
    }

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentWillMount() {
    if (cookies.get('auth'))  this.setState({loggedIn:true})

  }

  componentDidMount(){
    if (!cookies.get('auth')) Router.back()
    let id = Router.query.id;
    let object = this.state.req;
    object["productId"] = id;
    this.setState({req:object})
    let data = fetch('https://localhost:8765/observatory/api/products/'+id+"?shops=true").then((resp)=>{
      resp.json().then((res)=>{
        let sid = res[0].id;
        let sobj = this.state.req;
        sobj.shopId = sid;
        this.setState({shops: res, req:sobj})
      })
    })
    let data2 = fetch('https://localhost:8765/observatory/api/shops?start=0&count=8').then((resp)=>{
      resp.json().then((res)=>{
        this.setState({allShops:res.shops});
      })
    })
  }

  handleSubmit(e){
    e.preventDefault();
    let req = querystring.stringify(this.state.req);
    let cookie = cookies.get('auth')
    fetch("https://localhost:8765/observatory/api/prices",{
      method: "post",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "X-OBSERVATORY-AUTH": cookie,
      },
      body: req
    }).then(response => {
        if (!response.ok) { alert("Something wente wrong! Please try again."); throw response }
        else { alert("You have succesfully added the record"); Router.back()}
      })
  }

  handleChange(e){
    if (e.target.value=="all"){
      this.setState({visibility:"visible"})
    }
    else{
      var item = event.target.name;
      let object = this.state.req;
      object[item] = event.target.value;
      this.setState({req:object,visibility:"hidden"})
    }
  }

    render() {

        if (this.state.shops){
          let shopsList = this.state.shops.map(shop =>(<option value={shop.id}>{shop.name}</option>))
          shopsList.push(<option value="all">Όλα τα καταστήματα</option>);
          let curDate = new Date();
          curDate.hours = curDate.hours + 2;
          curDate = curDate.toISOString().split('T')[0]
          var allShopsList = null;
        if(this.state.allShops)  { allShopsList = this.state.allShops.map(aShop=>(<option value={aShop.id}>{aShop.name}</option>))}
        return (
            <div id="container">
                <Head>
                    <title> Προσθέστε τιμή | Stingy </title>
                    <link rel="shortcut icon" href="../static/logo/logo.png"/>
                    <link href='../static/addrecord.css' type='text/css' rel='stylesheet' />
                </Head>
                <div id='body'>
                    <NavBar loggedIn={this.state.loggedIn}/>
                    <div>
                    <h2 class='aheader'>Προσθήκη Τιμής:</h2>
                    <div>
                    <form class='form1' id="addrecord" onSubmit={this.handleSubmit}>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Κατάστημα:</label>
                                    </td>
                                    <td>
                                        <select name="shopId" form="addrecord" onChange={this.handleChange}>
                                          {shopsList}
                                        </select>
                                    </td>
                                </tr>
                                <tr style={{visibility:this.state.visibility}}>
                                    <td>
                                        <label>Όλα τα καταστήματα:</label>
                                    </td>
                                    <td>
                                        <select name="shopId" form="addrecord" onChange={this.handleChange}>
                                          {allShopsList}
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Hμερομηνία από:</label>
                                    </td>
                                    <td>
                                        <input type='date' name="dateFrom" defaultValue={curDate} min={curDate} onChange={this.handleChange} required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Ημερομηνία έως:</label>
                                    </td>
                                    <td>
                                        <input type='date' name="dateTo" defaultValue={curDate} min={curDate} onChange={this.handleChange}required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Τιμή:</label>
                                    </td>
                                    <td>
                                        <input type='text' name="price" onChange={this.handleChange} required />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <a href='/addshop'><button type="button" className='button1'> Προσθήκη καταστήματος</button></a>
                        <input type='submit' class='button1' />
                    </form>
                    </div>
                    <div class='footerdiv'>
                        <Footer />
                    </div>
                    </div>
                </div>
            </div>
        );} else { <div>Το προιον δε διατίθεται σε κάποιο κατάστημα αυτή τη στιγμή</div>}
    }
}

export default Content;
