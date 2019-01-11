import {ProductView} from '../components/ProductView.js';
import NavBar from '../components/NavBar';


const myExampleProduct = {
    device: 'smartphone', name: 'Testphone 5inch 4GB RAM Black', screen: '5"', ram: '4GB', rom: '64GB', backCamera: '20Mp', 
    frontCamera: '4Mp', cpuCores: '4', manufacturer: 'Samsung'
};

const myExampleShops = [
    {name: 'Λαική αγορά Αιγαλέου', price: '5$', otherData: ''},
    {name: 'Public Συντάγματος', price: '1000$', otherData: ''}
];

//const myExampleShops = [
//    {name: 'Λαική αγορά Αιγαλέου', price: '5$', otherData: ''},
//    {name: 'Public Συντάγματος', price: '1000$', otherData: ''}
//]

var content = (
    <div>
        <head>
            <link href='../static/ProductView.css' type='text/css' rel='stylesheet' />
        </head>
        <div>
            <NavBar />
            <ProductView product={myExampleProduct} shops={myExampleShops} />
        </div>
    </div>
);
export default () => content;
