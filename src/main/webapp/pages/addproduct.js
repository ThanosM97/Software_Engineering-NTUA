import React from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Head from 'next/head';
import Cookies from 'universal-cookie';
import Router from 'next/router'


const cookies = new Cookies();
const querystring = require('querystring');
const fetch = require("node-fetch");

var defaultRender =[["Λειτουργικό Σύστημα:","OS"], ["Οθόνη:","ScreenSize"], ["RAM:","RAM"], ["Χωρητικότητα:","Capacity"], ["Πίσω κάμερα:","SelfieCamera"], ["Μπροστά κάμερα:","FrontCamera"], ["Πυρήνες επεξεργαστή:","CPUcores"]]
var tvRender = [["Ανάλυση Οθόνης:","Resolution"], ["Smart TV:","Smart"], ["Μέγεθος Οθόνης","ScreenSize"]]
var laptopRender =[["Επεξεργαστης:","CPU"],["Πυρήνες επεξεργαστή:","CPUcores"],["RAM:","RAM"], ["Σκληρός Δίσκος:","HardDrive"], ["Λειτουργικό Σύστημα:","OS"], ["Μέγεθος Οθόνης:","ScreenSize"], ["Κάρτα γραφικών:","GraphicsCard"] ]
var monitorRender = [["Οθόνη:","ScreenSize"], ["Ανάλυση:","Resolution"]]
var tabletRender = [["Οθόνη:","ScreenSize"], ["RAM:","RAM"], ["Λειτουργικό Σύστημα:","OS"], ["Σκληρός Δίσκος:","HardDrive"]]
var newRender = [["Όνομα νέας Κατηγορίας:",""]]

class content extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            loggedIn:false,
            value1: 'smartphone',
            filevalue: '',
            tagNo:1,
            product_render: defaultRender.map((extraData,i)=>(
            <tr>
                <td>
                    <label>{extraData[0]}</label>
                </td>
                <td>
                    <input type='text' name={extraData[1]} />
                </td>
            </tr>
          )),
          tagFields: [<tr><td>
              <label>Tag 1:</label>
          </td>
          <td>
              <input type='text' name="tags" required/>
          </td> </tr>]
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleFileSelect = this.handleFileSelect.bind(this);

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
        query = querystring.stringify(query);
        fetch("https://localhost:8765/observatory/api/products",{
          method: "post",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "X-OBSERVATORY-AUTH": cookie,
          },
          body: query
        }).then(response => {
            if (!response.ok) { alert("Something wente wrong! Please try again."); throw response }
            alert("Your product has been added to our database. Thank you for your help!")
            Router.push('/addproduct');
          })
      }

    }



    handleFileSelect(e) {
        this.setState({filevalue: e.target.value});
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


    handleChange(event) {
        this.setState({value1: event.target.value});
        switch (event.target.value){
            case 'smartphone':

                this.state.product_render = (

                            defaultRender.map((extraData,i)=>(
                            <tr>
                                <td>
                                    <label>{extraData[0]}</label>
                                </td>
                                <td>
                                    <input type='text' name={extraData[1]} />
                                </td>
                            </tr>
                          ))

                );
                break;
            case 'tv':
                this.state.product_render = (
                    tvRender.map((extraData,i)=>(
                            <tr>
                                <td>
                                    <label>{extraData[0]}</label>
                                </td>
                                <td>
                                    <input type='text' name={extraData[1]} />
                                </td>
                            </tr>
                        )
                    )
                )
                break;
            case 'laptop':
            this.state.product_render = (
                laptopRender.map((extraData,i)=>(
                        <tr>
                            <td>
                                <label>{extraData[0]}</label>
                            </td>
                            <td>
                                <input type='text' name={extraData[1]} />
                            </td>
                        </tr>
                    )
                )
            )
            break;
            case 'monitor':
            this.state.product_render = (
                monitorRender.map((extraData,i)=>(
                        <tr>
                            <td>
                                <label>{extraData[0]}</label>
                            </td>
                            <td>
                                <input type='text' name={extraData[1]} />
                            </td>
                        </tr>
                    )
                )
            )
            break;
            case 'tablet':
                this.state.product_render = (
                tabletRender.map((extraData,i)=>(
                        <tr>
                            <td>
                                <label>{extraData[0]}</label>
                            </td>
                            <td>
                                <input type='text' name={extraData[1]} />
                            </td>
                        </tr>
                    )
                )
            )
            break;
            case 'new':
            this.state.product_render = (
                newRender.map((extraData,i)=>(
                        <tr>
                            <td>
                                <label>{extraData[0]}</label>
                            </td>
                            <td>
                                <input type='text' name={extraData[1]} required />
                            </td>
                        </tr>
                    )
                )
            )
            break;
        }
    }

    render() {
        return (
            <div id="container">
                <Head>
                    <title> Προσθέστε προϊόν | Stingy </title>
                    <link rel="shortcut icon" href="../static/logo/logo.png"/>
                    <link href='../static/addproduct.css' type='text/css' rel='stylesheet' />
                </Head>
                <div id='body'>
                    <NavBar loggedIn={this.state.loggedIn}/>
                    <div>
                    <h2 className='aheader'>Προσθήκη Προϊόντος:</h2>
                    <div>
                    <form className='form1' onSubmit={this.handleSubmit} id="addproduct">
                        <table className='tablecontent'>
                            <tbody>

                            </tbody>
                        </table>
                        <div className='optionalcontent'>
                            <table className='tablecontent'>
                                <tbody>
                                <tr>
                                    <td>
                                        <label>Κατηγορία:</label>
                                    </td>
                                    <td className='category'>
                                        <select value={this.state.value1} onChange={this.handleChange} name="category" form="addproduct" required>
                                            <option value="smartphone">Smartphone</option>
                                            <option value="laptop">Laptop</option>
                                            <option value="monitor">Monitor</option>
                                            <option value="tablet">Tablet</option>
                                            <option value="tv">TV</option>
                                            <option value="new">Νέα Κατηγορία</option>
                                        </select>
                                    </td>
                                </tr>
                                  <tr>
                                      <td>
                                          <label>Όνομα προιόντος:</label>
                                      </td>
                                      <td>
                                          <input type='text' name="name" required/>
                                      </td>
                                      <td>
                                          <label>Περιγραφή προιόντος:</label>
                                      </td>
                                      <td>
                                          <input type='text' name="description" required/>
                                      </td>
                                  </tr>
                                    {this.state.tagFields}
                                  {this.state.product_render}
                                </tbody>
                            </table>
                        </div>
                        <button type="button" className="button1 tags" onClick={this.addTag}>Add more tags</button>
                        <input type='submit' className='button1' value='Submit' />
                    </form>

                    </div>
                    <div className='footerdiv'>
                        <Footer />
                    </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default content;
