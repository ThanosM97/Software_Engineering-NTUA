import React from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';


class content extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value1: 'smartphone',
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
                                <tr>
                                    <td>
                                        <label>Ταχύτητα επεξεργαστή:</label>
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
                                <tr>
                                    <td>
                                        <label>Ταχύτητα επεξεργαστή:</label>
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
                                        <label>Συχνότητα:</label>
                                    </td>
                                    <td>
                                        <input type='text' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>4K:</label>
                                    </td>
                                    <td>
                                        <input type='checkbox' />
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
        }
    }
    
    render() {
        return (
            <div id="container">               
                <head>
                    <title> Προσθέστε προϊόν | Stingy </title>
                    <link rel="shortcut icon" href="../static/logo/logo.png"/>    
                    <link href='../static/addrecord.css' type='text/css' rel='stylesheet' />
                </head>
                <div id='body'>
                    <NavBar />
                    <div>
                    <h2 class='aheader'>Προσθήκη Προϊόντος</h2>
                    <form class='form'>
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
                                        <label>Φωτογραφία:</label>
                                    </td>
                                    <td>
                                        <input type='button' class='button' value='Browse...' />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Κατηγορία:</label>
                                    </td>   
                                </tr>
                                <tr>
                                    <td>
                                        <select value={this.state.value1} onChange={this.handleChange}>
                                            <option value="smartphone">Smartphone</option>
                                            <option value="tv">TV</option>
                                            <option value="laptop">Laptop</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <div>{this.state.product_render}</div>
                    </form>
                    
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