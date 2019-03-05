import React from 'react';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import Head from 'next/head';

class content extends React.Component {
    
    render() {
        return (
            <div id="container">               
                <Head>
                    <title> Προσθέστε τιμή | Stingy </title>
                    <link rel="shortcut icon" href="../static/logo/logo.png"/>    
                    <link href='../static/addrecord.css' type='text/css' rel='stylesheet' />
                </Head>
                <div id='body'>
                    <NavBar />
                    <div>
                    <h2 class='aheader'>Προσθήκη Τιμής:</h2>
                    <div>
                    <form class='form1'>
                        <table class='tablecontent'>
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Id προϊόντος:</label>                       
                                    </td>
                                    <td>
                                        <input type='text' required /> 
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Id καταστήματος:</label>
                                    </td>
                                    <td>
                                        <input type='text' required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Hμερομηνία από:</label>
                                    </td>
                                    <td>
                                        <input type='text' placeholder='ΕΕΕΕ-ΜΜ-ΗΗ' required />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Ημερομηνία έως:</label>
                                    </td>
                                    <td>
                                        <input type='text' placeholder='ΕΕΕΕ-ΜΜ-ΗΗ' required />
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
                            </tbody>
                        </table>
                        <input type='button' class='button1' value='Submit...' />
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
