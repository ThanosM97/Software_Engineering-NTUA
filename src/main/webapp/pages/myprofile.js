import React from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';


const ExampleUser = {
    username: 'ompampisosougias', password: 'onikosopetaloudas', email: 'xaralamboslove@gmail.com', firstname: 'Χαράλαμπος', lastname:  'Σουγιάς', phone_number: '6942852132'         
};

class MyProfile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            passVisibility: 'password',
            buttonText: 'Show'
        }
        
    }

    showPassword() {
        this.state.passVisibility === 'password' ? this.setState({passVisibility:'text'}) : this.setState({passVisibility:'password'});
        this.state.buttonText === 'Show' ? this.setState({buttonText:'Hide'}) : this.setState({buttonText:'Show'});
      }

    render() {
        return (
        <div>
            <Head>
                <title> My profile | Stingy </title>
                <link rel="shortcut icon" href="../static/logo/logo.png"/>
                <meta charSet="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1" />
                {/*<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">*/}
                <link href="../static/myprofile.css" rel="stylesheet" />
            </Head>
            <div>
                <NavBar />
                <header id="header">
                    <h2>My profile</h2>
                </header>
                <br /><hr />
                <div id="contact">                    
                    <div className="container">
                        <h3 className="center">User Information</h3>
                        <p><img src="../static/profiles/profile2.png" className="avatar" /></p>                   
                        <hr />
                        <form>
                            <fieldset>
                                <h5>Username:</h5>
                                <span><h3>{ExampleUser.username}</h3></span>
                            </fieldset>
                            <fieldset>
                                <h5>First Name:</h5>
                                <button onclick="document.getElementById('firstname').value = ''">Change</button>
                                <input defaultValue={ExampleUser.firstname} id="firstname" type="firstname" tabIndex={3} />
                            </fieldset>
                            <fieldset>
                                <h5>Last Name:</h5>
                                <button onclick="document.getElementById('lastname').value = ''">Change</button>
                                <input defaultValue={ExampleUser.lastname} id="lastname" type="lastname" tabIndex={3} />
                            </fieldset>                       
                            <fieldset>
                                <h5>Your email:</h5>
                                <button onclick="document.getElementById('email').value = ''">Change</button>
                                <input defaultValue={ExampleUser.email} id="email" type="email" tabIndex={3} />
                            </fieldset>
                            <fieldset>
                                <h5>Your Phone number:</h5>
                                <button onclick="document.getElementById('telephone').value = ''">Change</button>
                                <input defaultValue={ExampleUser.phone_number} id="telephone" type="tel" tabIndex={4} readOnly />
                            </fieldset>
                                <h4 className="center">Change password</h4>
                                <hr />
                            <fieldset>
                                <h5>Current password:</h5>
                                <input type={this.state.passVisibility} defaultValue={ExampleUser.password} id="myInput" />
                                <button className="showPass" type="button" onClick={this.showPassword.bind(this)}> {this.state.buttonText} </button>
                            </fieldset>
                            <fieldset>
                                <h5>Type new password:</h5>
                                <input placeholder="New password" type="password" />
                                <h5>Re-Type new password:</h5>
                                <input placeholder="New password" type="password" />
                            </fieldset>
                            <fieldset>
                                <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Save Changes</button>
                            </fieldset>
                        </form>
                    </div>            
                </div>
                <Footer />
            </div>
        </div>
        );
    }
};

export default MyProfile