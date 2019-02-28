import React from 'react';
import ReactDOM from 'react-dom';
import {ProductViewSpecs} from './ProductView.js';

export class ProductList extends React.Component{
    render(){
        const product_list = this.props.products.map(product => 
            <div class='product-entry'>
                <a>This will be a product!</a>
                <ProductViewSpecs class='main-product' product={product}/>
            </div>
            );
        // Maybe use the same Component for displaying specs here? We'll see
        return(
            <div>
                  <div>{product_list}</div>
            </div>
        );
    }
}