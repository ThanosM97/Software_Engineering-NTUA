import React from 'react';
import ReactDOM from 'react-dom';

const smartphone = "../static/images/smartphone250x250.png";

export class ProductList extends React.Component{
    render(){
        const product_list = this.props.products.map(product => 
            <div class='product-entry'>
                <img src={smartphone} class='product-image' />
                <a class='product-name'>Nokia L330 under triangle radar</a><br></br>
                <a class='product-price'>Χαμηλότερη καταγεγραμμένη τιμή: {product.price}</a>
                <a class='product-specs'>Χαρακτηριστικά: Οθόνη:{product.screen} RAM:{product.ram} ROM:{product.rom}</a>
            </div>
            );
        // Maybe use the same Component for displaying specs here? We'll see
        return(
            <div >
                  <div class='productbox'>{product_list}</div>
            </div>
        );
    }
}