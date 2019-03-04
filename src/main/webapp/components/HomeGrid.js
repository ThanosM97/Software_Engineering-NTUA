import React, { Component } from 'react';
const logo = "../static/logo/LogoFinal.png";
const tv = "../static/images/tv250x250.png";
const smartphone = "../static/images/smartphone250x250.png";
const monitor = "../static/images/monitor250x250.png";
const tablet = "../static/images/tablet250x250.png";
const laptop = "../static/images/laptop250x250.png";

class HomeGrid extends Component {
  render () {
    return (
      <div className="categories-container">
        <a href="/productlist?category=tv" style={{textDecoration:"none"}} ><div className="box purple">
          <img className="image" src={tv} width="40%" />
          <h1>TVs</h1>
        </div></a>
        <a href="/productlist?category=smartphone" style={{textDecoration:"none"}} ><div className="box blue">
            <img className="image" src={smartphone} width="40%" />
          <h1>Smartphones</h1>
        </div> </a>
        <a href="/productlist?category=monitor" style={{textDecoration:"none"}} ><div className="box yellow">
          <img className="image" src={monitor} width="40%" />
          <h1>Monitors</h1>
        </div></a>
        <div className="box stingy two-columns">
          <img className="stingy-image" src={logo} width="70%" />
          <p> Το Stingy είναι μια μηχανή σύγκρισης προϊόντων και τιμών που αναπτύχθηκε με στόχο να βοηθήσει τους καταναλωτές στις online αγορές τους. Δεσμευόμαστε να προσφέρουμε στους χρήστες τις χαμηλότερες τιμές της αγοράς. Εγγραφείτε στην ιστοσελίδα μας για την έγκαιρη ενημέρωσή σας καθώς και για να κερδίσετε εκπτώσεις στις μελλοντικές αγορές σας.</p>
        </div>
        <a href="/productlist?category=tablet" style={{textDecoration:"none"}} ><div className="box red">
          <img className="image" src={tablet} width="40%" />
          <h1>Tablets</h1>
        </div></a>
        <a href="/productlist?category=laptop" style={{textDecoration:"none"}} ><div className="box green">
          <img className="image" src={laptop} width="40%" />
          <h1>Laptops</h1>
        </div></a>
      </div>
    );
  }
}
export default HomeGrid;
