import {ProductView} from '../components/ProductView.js';
import {ProductViewSpecs} from '../components/ProductView.js';
import {ProductViewSmartphone} from '../components/ProductView.js';

import HomeHeader from '../components/HomeHeader.js';

const myExampleProduct = {
    device: 'smartphone', name: 'Testphone', screen: '5"', ram: '4GB', rom: '64GB', backCamera: '20Mp', 
    frontCamera: '4Mp', cpuCores: '4', manufacturer: 'Samsung'
};

//const myExampleShops = [
//    {name: 'Λαική αγορά Αιγαλέου', price: '5$', otherData: ''},
//    {name: 'Public Συντάγματος', price: '1000$', otherData: ''}
//]

var content = (
    <div>
	<h1>This is a test page for an item!</h1>
	<h2>This test item is a {myExampleProduct.device}! Here are its specs</h2>
	<ProductViewSpecs product={myExampleProduct} />
    </div>
);
export default () => content;
