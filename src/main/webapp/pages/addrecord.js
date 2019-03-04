import React from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Head from 'next/head';

class content extends React.Component {
    constructor(props) {
        super(props);       

        this.state = {
            value1: 'smartphone',
            filevalue: '',
            product_render: (
                <div>
                    <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Λειτουργικό Σύστημα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Οθόνη:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>RAM:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Χωρητικότητα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Πίσω κάμερα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Μπροστά κάμερα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Πυρήνες επεξεργαστή:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                </div>
            )
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
                    <div>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Οθόνη:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>RAM:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Χωρητικότητα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Πίσω κάμερα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Μπροστά κάμερα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Λειτουργικό Σύστημα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Πυρήνες επεξεργαστή:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                );
                break;
            case 'tv':
                this.state.product_render = (
                    <div>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Οθόνη:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Ανάλυση:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>SmartTV:</label>
                                    </td>
                                    <td>
                                        <input type='checkbox' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                );
                break;
            case 'laptop':
                this.state.product_render = (
                    <div>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>CPU:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Πυρήνες επεξεργαστή:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>RAM:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Σκληρός δίσκος:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Λειτουργικό Σύστημα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Οθόνη:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Κάρτα γραφικών:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                );
                break;
            case 'monitor':
                this.state.product_render = (
                    <div>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Οθόνη:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Ανάλυση:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                );
                break;
            case 'tablet':
                this.state.product_render = (
                    <div>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Οθόνη:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>RAM:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Λειτουργικό Σύστημα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Σκληρός δίσκος:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                );
                break;
        }
    }
    
    render() {
        return (
            <div id="container">               
                <Head>
                    <title> Προσθέστε προϊόν | Stingy </title>
                    <link rel="shortcut icon" href="../static/logo/logo.png"/>    
                    <link href='../static/addrecord.css' type='text/css' rel='stylesheet' />
                </Head>
                <div id='body'>
                    <NavBar />
                    <div>
                    <h2 class='aheader'>Προσθήκη Προϊόντος:</h2>
                    <div>
                    <form class='form1'>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Όνομα:</label>                       
                                    </td>
                                    <td>
                                        <input type='text' required /> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Τιμή:</label>
                                    </td>
                                    <td>
                                        <input type='text' required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Hμερομηνία παρατήρησης:</label>
                                    </td>
                                    <td>
                                        <input type='text' required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Όνομα καταστήματος:</label>
                                    </td>
                                    <td>
                                        <input type='text' required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Διεύθυνση καταστήματος:</label>
                                    </td>
                                    <td>
                                        <input type='text' required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label class='photo'>Φωτογραφία:</label>
                                    </td>
                                    <td>
                                        <input type="file" id="file" ref="fileUploader" onChange={this.handleFileSelect} style={{display: "none"}} />
                                        <input type='button' onClick={this.handleClick} class='button1' value='Browse...' /><br></br>
                                        <label>{this.state.filevalue}</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Κατηγορία:</label>
                                    </td>   
                               
                                    <td>
                                        <select value={this.state.value1} onChange={this.handleChange}>
                                            <option value="smartphone">Smartphone</option>
                                            <option value="laptop">Laptop</option>
                                            <option value="monitor">Monitor</option>
                                            <option value="tablet">Tablet</option>
                                            <option value="tv">TV</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div class='optionalcontent'>{this.state.product_render}</div>
                        <input type='button' class='button2' value='Submit' />
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