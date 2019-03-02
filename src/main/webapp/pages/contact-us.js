import React, { Component } from 'react';
import Head from 'next/head';
import NavBar from '../components/NavBar.js';
import Footer from '../components/Footer.js';

class Contact extends Component {

  render() {
    return (
      <div className="bodyArea">
        <Head>
          <title> Επικοινωνήστε μαζί μας | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href="../static/contact_form.css" rel="stylesheet" />
        </Head>
        <NavBar />
        <header id="header" class="center animate-top">
            <h2>Get in touch with Stingy</h2>
            <p class="center animate-top">Μπορείτε να έρθετε με εύκολια σε επικοινωνία μαζί μας συμπληρώνοντας την παρακατω φόρμα για
                οποιοδήποτε θέμα σχετικά με τις υπηρεσίες μας </p>
        </header>
        <div class="container">
            <form id="contact" action="" method="post">
                <h3>Contact Form</h3>
                <fieldset>
                    <input placeholder="Your name" type="text" tabindex="1" required autofocus />
                </fieldset>
                <fieldset>
                    <input placeholder="Your Email Address" type="email" tabindex="2" required />
                </fieldset>
                <fieldset>
                    <textarea placeholder="Type your message here...." tabindex="5" required></textarea>
                </fieldset>
                <fieldset>
                    <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
                </fieldset>
            </form>
        </div>
        <Footer />
      </div>
    );
  }
}

export default Contact
