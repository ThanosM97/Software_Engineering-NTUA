import React, { Component } from 'react';
import Head from 'next/head';

class Footer extends Component {
  render(){
    return (
      <div>
        <Head>
          <link href="../static/Footer.css" rel="stylesheet" />
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
          <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Cookie"/>
          <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        </Head>
        <div className="footer">
          <div className="categories-box">
            <h3 class="logo" style={{float:"left"}}><img src="../static/logo/logo.png" alt="Stingy" width= "8%" height= "8%" style={{marginBottom:"-5px"}}/>  Stingy </h3>
            <a href="/"><i className="fa fa-home" /> Home </a>
            <a href="/productlist?category=tv"><i className="fa fa-television"/> TV's</a>
            <a href="/productlist?category=smartphone"><i className="fa fa-mobile"/> Smartphones</a>
            <a href="/productlist?category=laptop"><i className="fa fa-laptop"/> Laptops</a>
            <a href="/productlist?category=tablet"><i className="fa fa-tablet"/> Tablets</a>
            <a href="/productlist?category=monitor"><i className="fa fa-television"/> Monitors</a>
            <p className="footer-company-name">Stingy &copy; 2019</p>
          </div>
          <div className="contact-box">
              <a className="links" href="/contact-us"><h3> <i className="fa fa-envelope circle " width="20px" height="13px"/>Επικοινωνήστε μαζί μας</h3></a>
              <p style={{color:"red"}}>(Χρειάζεσαι βοήθεια?)</p><br />
              <a className="links" href="shops">  <h3><i className="fa fa-building-o"></i> Συνεργαζόμενα Καταστήματα</h3></a>
          </div>
          <div className="volunteering-box">
            <h3 style={{textAlign:"center"}}>Εθελοντική δράση</h3>
            <p>Το Stingy δίνει τη δυνατότητα στους χρήστες να συνεισφέρουν και οι ίδιοι εθελοντικά είτε ενημερώνοντας τις τιμές των προϊόντων είτε προσθέτοντας νέα προϊόντα στην εφαρμογή μας. Με αυτό τον τρόπο συμβάλλουν ώστε το Stingy να προβάλλει στους χρήστες τις χαμηλότερες τιμές της αγοράς.</p>
          </div>
        </div>
      </div>
    );
  }
}

export default Footer;
