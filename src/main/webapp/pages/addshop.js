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
    this.state = {
      loggedIn:false,
      tagNo:1,
      tagFields: [<tr><td>
          <label>Tag 1:</label>
      </td>
      <td>
          <input type='text' name="tags" required/>
      </td> </tr>],
    }

      this.addTag = this.addTag.bind(this);
  }

  componentWillMount() {
    if (cookies.get('auth'))  this.setState({loggedIn:true})
  }
  componentDidMount() {
    if (!cookies.get('auth')) Router.back()
    let query = Router.query
    let cookie = cookies.get('auth')
    if (query.name){
      let shopName=query.name
      query = querystring.stringify(query);
      fetch("https://localhost:8765/observatory/api/shops",{
        method: "post",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
          "X-OBSERVATORY-AUTH": cookie,
        },
        body: query
      }).then(response => {
          if (!response.ok) { alert("Something wente wrong! Please try again."); throw response }
          alert("Shop:"+shopName+" has been added to our database. Thank you for your help!")
          Router.push('/addshop');
        })
    }
  }
  addTag(e){

    let obj = this.state.tagFields;
    let no = this.state.tagNo +1
    obj = obj.concat(<tr key={this.state.tagNo}><td>
        <label>Tag {no}:</label>
    </td>
    <td>
        <input type='text' name="tags" required/>
    </td></tr>);

    this.setState({tagFields:obj,tagNo:no});
  }


    render() {
        return (
            <div id="container">
                <Head>
                    <title> Προσθέστε κατάστημα | Stingy </title>
                    <link rel="shortcut icon" href="../static/logo/logo.png"/>
                    <link href='../static/addrecord.css' type='text/css' rel='stylesheet' />
                </Head>
                <div id='body'>
                    <NavBar loggedIn={this.state.loggedIn}/>
                    <div>
                    <h2 class='aheader'>Προσθήκη Καταστήματος:</h2>
                    <div>
                    <form class='form1' onSubmit={this.handleSubmit} id="addshop">
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Όνομα καταστήματος:</label>
                                    </td>
                                    <td>
                                        <input type='text' name="name" required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Διεύθυνση καταστήματος:</label>
                                    </td>
                                    <td>
                                        <input type='text' name="address" required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Γεωγραφικό μήκος:</label>
                                    </td>
                                    <td>
                                        <input type='text' name="lng" required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Γεωγραφικό πλάτος:</label>
                                    </td>
                                    <td>
                                        <input type='text' name="lat" required />
                                    </td>
                                </tr>
                                {this.state.tagFields}
                            </tbody>
                        </table>
                        <button type="button" className="button1 tags" onClick={this.addTag}>Add more tags</button>
                        <input type='submit' class='button1' value='Submit' />
                    </form>
                    </div>
                    <div class='footerdiv'>
                        <Footer />
                    </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Content;
