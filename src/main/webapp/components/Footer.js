import React, { Component } from 'react';
import Head from 'next/head';

class Footer extends Component {
  render(){
    return (
      <div>
        <Head>
          <link href="../static/Footer.css" rel="stylesheet" />
        </Head>
        <div className="footer">
          <div className="categories-box">
            <h3>Κατηγορίες</h3>
            <a href="#">TV's</a>
            <a href="#">Monitors</a>
            <a href="#">Laptops</a>
            <a href="#">Tablets</a>
            <a href="#">Smartphones</a>
          </div>
          <div className="contact-box">
              <img src="/static/images/black-envelope.jpg" alt="contact us" width="20px" height="13px" />
              <h3>Επικοινωνήστε μαζί μας</h3>
              <p style={{color:"red"}}>(Χρειάζεσαι βοήθεια?)</p>
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
