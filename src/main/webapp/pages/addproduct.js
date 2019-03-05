import React from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Head from 'next/head';

var defaultRender =["Λειτουργικό Σύστημα:", "Οθόνη:", "RAM:", "Χωρητικότητα:", "Πίσω κάμερα:", "Μπροστά κάμερα:", "Πυρήνες επεξεργαστή:"]
var tvRender = ["Ανάλυση Οθόνης:", "Smart TV:", "Μέγεθος Οθόνης"]
var laptopRender =["Επεξεργαστς","Πυρήνες επεξεργαστή:","RAM:", "Σκληρός Δίσκος:", "Λειτουργικό Σύστημα:", "Μέγεθος Οθόνης:", "Κάρτα γραφικών:" ]
var monitorRender = ["Οθόνη", "Ανάλυση"]
var tabletRender = ["Οθόνη", "RAM", "Λειτουργικό Σύστημα", "Σκληρός Δίσκος"]
var newRender = ["Όνομα νέας Κατηγορίας"]
// TODO: + 2 ΚΑΤΗΓΟΡΙΕΣ ΑΚΟΜΑ

class content extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            value1: 'smartphone',
            filevalue: '',
            product_render: defaultRender.map((extraData,i)=>(
            <tr>
                <td>
                    <label>{extraData}</label>
                </td>
                <td>
                    <input type='text' />
                </td>
            </tr>
          )),
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleFileSelect = this.handleFileSelect.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick(e) {
        this.refs.fileUploader.click();
    }

    handleFileSelect(e) {
        this.setState({filevalue: e.target.value});
    }

    handleChange(event) {
        this.setState({value1: event.target.value});
        switch (event.target.value){
            case 'smartphone':

                this.state.product_render = (

                            defaultRender.map((extraData,i)=>(
                            <tr>
                                <td>
                                    <label>{extraData}</label>
                                </td>
                                <td>
                                    <input type='text' />
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
                                    <label>{extraData}</label>
                                </td>
                                <td>
                                    <input type='text' />
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
                                <label>{extraData}</label>
                            </td>
                            <td>
                                <input type='text' />
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
                                <label>{extraData}</label>
                            </td>
                            <td>
                                <input type='text' />
                            </td>
                        </tr>
                    )
                )
            )
            break;
            case 'tablet':
                this.state.product_render = (            
                laptopRender.map((extraData,i)=>(
                        <tr>
                            <td>
                                <label>{extraData}</label>
                            </td>
                            <td>
                                <input type='text' />
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
                                <label>{extraData}</label>
                            </td>
                            <td>
                                <input type='text' />
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
                    <NavBar loggedIn={this.loggedIn}/>
                    <div>
                    <h2 class='aheader'>Προσθήκη Προϊόντος:</h2>
                    <div>
                    <form class='form1'>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Κατηγορία:</label>
                                    </td>
                                    <td class='category'>
                                        <select value={this.state.value1} onChange={this.handleChange}>
                                            <option value="smartphone">Smartphone</option>
                                            <option value="laptop">Laptop</option>
                                            <option value="monitor">Monitor</option>
                                            <option value="tablet">Tablet</option>
                                            <option value="tv">TV</option>
                                            <option value="new">Νέα Κατηγορία</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class='optionalcontent'>
                            <table class='tablecontent'>
                                <tbody>{this.state.product_render}</tbody>
                            </table>
                        </div>
                        <input type='button' class='button1' value='Submit' />
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

export default content;
